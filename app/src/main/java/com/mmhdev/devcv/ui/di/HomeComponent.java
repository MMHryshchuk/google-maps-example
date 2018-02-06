package com.mmhdev.devcv.ui.di;


import com.mmhdev.devcv.core.android.App;
import com.mmhdev.devcv.core.di.scope.PerActivity;
import com.mmhdev.devcv.domain.use_case.UserUseCase;
import com.mmhdev.devcv.location.FusedLocationProvider;
import com.mmhdev.devcv.ui.screens.home.HomeActivity;
import com.mmhdev.devcv.ui.screens.home.HomePresenter;
import com.mmhdev.devcv.ui.screens.home.HomePresenterImpl;

import javax.inject.Singleton;

import dagger.Provides;
import dagger.Subcomponent;

/**
 */
@Subcomponent(modules = {HomeComponent.Module.class})
@PerActivity
public interface HomeComponent {
    void inject(HomeActivity homeActivity);

    @dagger.Module
    class Module{
        @Provides
        @PerActivity
        public HomePresenter provideHomePresenter(UserUseCase userUseCase){
            return new HomePresenterImpl(userUseCase);
        }

        @Singleton
        @PerActivity
        public FusedLocationProvider providerFusedLocationProvider(App app){
            return new FusedLocationProvider(app);
        }
    }
}
