package com.mmhdev.devcv.core.di.components;

import com.mmhdev.devcv.core.android.App;
import com.mmhdev.devcv.core.android.AuthRetrofit;
import com.mmhdev.devcv.core.bus.Bus;
import com.mmhdev.devcv.core.di.modules.ApiModule;
import com.mmhdev.devcv.core.di.modules.AppModule;
import com.mmhdev.devcv.core.di.modules.DataModule;
import com.mmhdev.devcv.core.di.modules.MappersModule;
import com.mmhdev.devcv.core.di.modules.RepositoriesModule;
import com.mmhdev.devcv.core.di.modules.ThreadExecutorsModule;
import com.mmhdev.devcv.core.di.modules.UseCaseModule;
import com.mmhdev.devcv.core.executors.PostExecutionThread;
import com.mmhdev.devcv.core.executors.ThreadExecutor;
import com.mmhdev.devcv.domain.repository.PreferenceRepository;
import com.mmhdev.devcv.location.FusedLocationProvider;
import com.mmhdev.devcv.ui.di.DirectionComponent;
import com.mmhdev.devcv.ui.di.HomeComponent;
import com.mmhdev.devcv.ui.di.MapComponent;

import javax.inject.Singleton;



import javax.inject.Singleton;

import dagger.Component;

/**
 */
@Singleton
@Component(modules = {
        AppModule.class,
        DataModule.class,
        ThreadExecutorsModule.class,
        ApiModule.class,
        UseCaseModule.class,
        RepositoriesModule.class,
        MappersModule.class
})
public interface AppComponent {
    ServicesComponent plus(ServicesComponent.ServicesModule module);
    MapComponent plus(MapComponent.Module module);
    HomeComponent plus(HomeComponent.Module module);
    DirectionComponent plus(DirectionComponent.Module module);


    App getApp();
    Bus getBus();
    AuthRetrofit getAuthRetorfit();
    PreferenceRepository getPreferenceRepository();
    FusedLocationProvider getFusedLocationProvider();
    ThreadExecutor getJobExcecutor();
    PostExecutionThread getUIThread();
}
