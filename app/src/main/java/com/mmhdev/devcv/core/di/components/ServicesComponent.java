package com.mmhdev.devcv.core.di.components;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Subcomponent;

/**
 */
@Subcomponent(modules = {ServicesComponent.ServicesModule.class})
@Singleton
public interface ServicesComponent {
    @Module
    class ServicesModule {
    }
}
