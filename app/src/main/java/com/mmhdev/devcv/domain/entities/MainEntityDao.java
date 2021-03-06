package com.mmhdev.devcv.domain.entities;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * DAO for table "MAIN_ENTITY".
*/
public class MainEntityDao extends AbstractDao<MainEntity, Long> {

    public static final String TABLENAME = "MAIN_ENTITY";

    /**
     * Properties of entity MainEntity.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Name = new Property(1, String.class, "name", false, "NAME");
        public final static Property Icon = new Property(2, Integer.class, "icon", false, "ICON");
        public final static Property Power = new Property(3, Integer.class, "power", false, "POWER");
        public final static Property Type = new Property(4, Integer.class, "type", false, "TYPE");
        public final static Property Latitude = new Property(5, Double.class, "latitude", false, "LATITUDE");
        public final static Property Longitude = new Property(6, Double.class, "longitude", false, "LONGITUDE");
    };


    public MainEntityDao(DaoConfig config) {
        super(config);
    }
    
    public MainEntityDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"MAIN_ENTITY\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"NAME\" TEXT," + // 1: name
                "\"ICON\" INTEGER," + // 2: icon
                "\"POWER\" INTEGER," + // 3: power
                "\"TYPE\" INTEGER," + // 4: type
                "\"LATITUDE\" REAL," + // 5: latitude
                "\"LONGITUDE\" REAL);"); // 6: longitude
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"MAIN_ENTITY\"";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, MainEntity entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(2, name);
        }
 
        Integer icon = entity.getIcon();
        if (icon != null) {
            stmt.bindLong(3, icon);
        }
 
        Integer power = entity.getPower();
        if (power != null) {
            stmt.bindLong(4, power);
        }
 
        Integer type = entity.getType();
        if (type != null) {
            stmt.bindLong(5, type);
        }
 
        Double latitude = entity.getLatitude();
        if (latitude != null) {
            stmt.bindDouble(6, latitude);
        }
 
        Double longitude = entity.getLongitude();
        if (longitude != null) {
            stmt.bindDouble(7, longitude);
        }
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public MainEntity readEntity(Cursor cursor, int offset) {
        MainEntity entity = new MainEntity( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // name
            cursor.isNull(offset + 2) ? null : cursor.getInt(offset + 2), // icon
            cursor.isNull(offset + 3) ? null : cursor.getInt(offset + 3), // power
            cursor.isNull(offset + 4) ? null : cursor.getInt(offset + 4), // type
            cursor.isNull(offset + 5) ? null : cursor.getDouble(offset + 5), // latitude
            cursor.isNull(offset + 6) ? null : cursor.getDouble(offset + 6) // longitude
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, MainEntity entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setName(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setIcon(cursor.isNull(offset + 2) ? null : cursor.getInt(offset + 2));
        entity.setPower(cursor.isNull(offset + 3) ? null : cursor.getInt(offset + 3));
        entity.setType(cursor.isNull(offset + 4) ? null : cursor.getInt(offset + 4));
        entity.setLatitude(cursor.isNull(offset + 5) ? null : cursor.getDouble(offset + 5));
        entity.setLongitude(cursor.isNull(offset + 6) ? null : cursor.getDouble(offset + 6));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(MainEntity entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(MainEntity entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    /** @inheritdoc */
    @Override    
    protected boolean isEntityUpdateable() {
        return true;
    }
    
}
