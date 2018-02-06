package com.mmhdev.devcv.ui.di;


import com.mmhdev.devcv.core.android.App;
import com.mmhdev.devcv.core.di.scope.PerActivity;
import com.mmhdev.devcv.domain.use_case.MapUseCase;
import com.mmhdev.devcv.domain.use_case.UserUseCase;
import com.mmhdev.devcv.location.FusedLocationProvider;
import com.mmhdev.devcv.ui.screens.map.MapActivity;
import com.mmhdev.devcv.ui.screens.map.MapPresenter;
import com.mmhdev.devcv.ui.screens.map.MapPresenterImpl;

import javax.inject.Singleton;

import dagger.Provides;
import dagger.Subcomponent;

/**
 */
@Subcomponent(modules = MapComponent.Module.class)
@PerActivity
public interface MapComponent {
    void inject(MapActivity mapActivity);

    @dagger.Module
    class Module{
        private final int type;

        public Module(int type) {
            this.type = type;
        }

        @Provides
        @PerActivity
        MapPresenter provideMapPresenter(App app, UserUseCase userUseCase, MapUseCase mapUseCase){
            return new MapPresenterImpl(type, app, userUseCase,mapUseCase);
        }

        @Singleton
        @PerActivity
        public FusedLocationProvider providerFusedLocationProvider(App app){
            return new FusedLocationProvider(app);
        }
    }
}
