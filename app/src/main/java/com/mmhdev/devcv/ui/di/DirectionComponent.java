package com.mmhdev.devcv.ui.di;

import com.mmhdev.devcv.core.android.App;
import com.mmhdev.devcv.core.di.scope.PerActivity;
import com.mmhdev.devcv.domain.use_case.DirectionsUseCase;
import com.mmhdev.devcv.ui.screens.direction.DirectionActivity;
import com.mmhdev.devcv.ui.screens.direction.DirectionPresenter;
import com.mmhdev.devcv.ui.screens.direction.DirectionPresenterImpl;

import dagger.Module;
import dagger.Provides;
import dagger.Subcomponent;

/**
 * Created by on 04.12.16.
 */
@Subcomponent(modules = DirectionComponent.Module.class)
@PerActivity
public interface DirectionComponent {

    void inject(DirectionActivity activity);

    @dagger.Module
    class Module{

        @Provides
        @PerActivity
        DirectionPresenter providePresenter(App app, DirectionsUseCase directionsUseCase){
            return new DirectionPresenterImpl(app, directionsUseCase);
        }
    }
}
