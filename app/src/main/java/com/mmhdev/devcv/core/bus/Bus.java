package com.mmhdev.devcv.core.bus;



import com.mmhdev.devcv.core.executors.PostExecutionThread;

import org.greenrobot.eventbus.EventBus;


/**
 */
public class Bus {
    private EventBus eventBus;
    private PostExecutionThread postExecutionThread;

    public Bus(EventBus eventBus, PostExecutionThread postExecutionThread) {
        this.eventBus = eventBus;
        this.postExecutionThread = postExecutionThread;
    }

    public void post(Object event){
        postExecutionThread.getScheduler().createWorker().schedule(() -> eventBus.post(event));
    }

    public void register(Object o){
        eventBus.register(o);
    }

    public void unRegister(Object o){
        eventBus.unregister(o);
    }
}
