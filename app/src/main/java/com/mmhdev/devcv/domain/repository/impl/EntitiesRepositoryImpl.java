package com.mmhdev.devcv.domain.repository.impl;

import android.database.Cursor;

import com.google.android.gms.maps.model.LatLng;
import com.mmhdev.devcv.R;
import com.mmhdev.devcv.core.utils.CoordinateUtils;
import com.mmhdev.devcv.domain.entities.DaoMaster;
import com.mmhdev.devcv.domain.entities.DaoSession;
import com.mmhdev.devcv.domain.entities.MainEntity;
import com.mmhdev.devcv.domain.entities.MainEntityDao;
import com.mmhdev.devcv.domain.repository.EntitiesRepository;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import rx.Observable;

/**
 */
public class EntitiesRepositoryImpl implements EntitiesRepository {


    private final static String SELECT_DATA =
            String.format("SELECT * FROM %s WHERE %s= ? AND %s > ? AND %s < ? AND %s < ? AND %s > ?",
                    MainEntityDao.TABLENAME,
                    MainEntityDao.Properties.Type.columnName,
                    MainEntityDao.Properties.Latitude.columnName,
                    MainEntityDao.Properties.Latitude.columnName,
                    MainEntityDao.Properties.Longitude.columnName,
                    MainEntityDao.Properties.Longitude.columnName);



    private DaoSession daoSession;

    public EntitiesRepositoryImpl(DaoSession daoSession) {
        this.daoSession = daoSession;
    }

    @Override
    public Observable<List<MainEntity>> getEntities(int type, double lat, double lon) {


        return Observable.create(subscriber -> {

            final double mult = 1; // mult = 1.1; is more reliable
            LatLng center = new LatLng(lat, lon);
            LatLng p1 = CoordinateUtils.calculateDerivedPosition(center, mult * USER_RADIUS, 0);
            LatLng p2 = CoordinateUtils.calculateDerivedPosition(center, mult * USER_RADIUS, 90);
            LatLng p3 = CoordinateUtils.calculateDerivedPosition(center, mult * USER_RADIUS, 180);
            LatLng p4 = CoordinateUtils.calculateDerivedPosition(center, mult * USER_RADIUS, 270);

            Cursor cursor = daoSession.getDatabase().rawQuery(SELECT_DATA, new String[]{
                    String.valueOf(type),
                    String.valueOf(p3.latitude),
                    String.valueOf(p1.latitude),
                    String.valueOf(p2.longitude),
                    String.valueOf(p4.longitude),
            });
            List<MainEntity> mainEntities = new LinkedList<MainEntity>();
            if (cursor.moveToFirst()){
                do {
                    MainEntity mainEntity = daoSession.getMainEntityDao().readEntity(cursor, 0);
                    if (CoordinateUtils.pointIsInCircle(lat, lon, mainEntity.getLatitude(), mainEntity.getLongitude(),  USER_RADIUS)){
                        mainEntities.add(mainEntity);
                    }
                } while (cursor.moveToNext());
            }
            cursor.close();
            subscriber.onNext(mainEntities);
            subscriber.onCompleted();
        });
    }

    @Override
    public Observable<Boolean> generateEntities(double myLat, double myLon) {
        return Observable.create(subscriber -> {
            DaoMaster.dropAllTables(daoSession.getDatabase(), true);
            DaoMaster.createAllTables(daoSession.getDatabase(), true);

            List<MainEntity> entityDvos = new ArrayList<>(2000);
            int i = 0;
            while (entityDvos.size() != 2000){
                MainEntity mainEntity = new MainEntity();
                mainEntity.setName("Bus");
                mainEntity.setPower((int)(Math.random()*10));
                LatLng randomLocation = CoordinateUtils.getRandomLocation(new LatLng(myLat, myLon), MAX_RADIUS);
                mainEntity.setLatitude(randomLocation.latitude);
                mainEntity.setLongitude(randomLocation.longitude);

                entityDvos.add(mainEntity);
            }

            daoSession.getMainEntityDao().insertInTx(entityDvos);
            subscriber.onNext(true);
            subscriber.onCompleted();
        });
    }

}
