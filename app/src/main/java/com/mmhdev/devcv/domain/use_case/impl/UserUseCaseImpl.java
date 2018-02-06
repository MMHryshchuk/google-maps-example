package com.mmhdev.devcv.domain.use_case.impl;


import com.mmhdev.devcv.core.executors.PostExecutionThread;
import com.mmhdev.devcv.core.executors.ThreadExecutor;
import com.mmhdev.devcv.domain.entities.MainEntity;
import com.mmhdev.devcv.domain.repository.EntitiesRepository;
import com.mmhdev.devcv.domain.use_case.UserUseCase;
import com.mmhdev.devcv.ui.dvo.EntityDvo;
import com.mmhdev.devcv.ui.dvo.MapDataDvo;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.schedulers.Schedulers;

/**
 */
public class UserUseCaseImpl implements UserUseCase {

    private EntitiesRepository entitiesRepository;
    private PostExecutionThread postExecutionThread;
    private ThreadExecutor threadExecutor;

    @Inject
    public UserUseCaseImpl(EntitiesRepository entitiesRepository, PostExecutionThread postExecutionThread, ThreadExecutor threadExecutor) {
        this.entitiesRepository = entitiesRepository;
        this.postExecutionThread = postExecutionThread;
        this.threadExecutor = threadExecutor;
    }

    @Override
    public Observable<MapDataDvo> getMapData(int mode, double lat, double lan) {
        return entitiesRepository.getEntities(mode, lat, lan)
                .map(mainEntities -> {
                    List<EntityDvo> entityDvos = new ArrayList<EntityDvo>(mainEntities.size());
                    for (MainEntity mainEntity : mainEntities){
                        entityDvos.add(new EntityDvo(mainEntity));
                    }
                    return new MapDataDvo(entityDvos);
                })
                .subscribeOn(Schedulers.from(threadExecutor))
                .observeOn(postExecutionThread.getScheduler());
    }

    @Override
    public Observable<Boolean> generateEntities(double lat, double lon) {
        return entitiesRepository.generateEntities(lat, lon)
                .subscribeOn(Schedulers.from(threadExecutor))
                .observeOn(postExecutionThread.getScheduler());
    }
}
