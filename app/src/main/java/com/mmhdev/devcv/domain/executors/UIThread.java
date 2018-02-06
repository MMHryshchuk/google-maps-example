package com.mmhdev.devcv.domain.executors;



import com.mmhdev.devcv.core.executors.PostExecutionThread;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;

/**
 */
@Singleton
public class UIThread implements PostExecutionThread {

    @Inject
    public UIThread() {
    }

    @Override
    public Scheduler getScheduler() {
        return AndroidSchedulers.mainThread();
    }
}
