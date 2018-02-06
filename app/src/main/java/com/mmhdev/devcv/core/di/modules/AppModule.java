package com.mmhdev.devcv.core.di.modules;




import com.mmhdev.devcv.core.android.App;
import com.mmhdev.devcv.core.bus.Bus;
import com.mmhdev.devcv.core.executors.PostExecutionThread;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 */
@Module
public class AppModule {

    private App app;

    public AppModule(App app) {
        this.app = app;
    }

    @Singleton
    @Provides
    public App provideAppContext(){
        return app;
    }

    @Singleton
    @Provides
    public Bus provideBus(PostExecutionThread postExecutionThread){
        return new Bus(new EventBus(), postExecutionThread);
    }
}
