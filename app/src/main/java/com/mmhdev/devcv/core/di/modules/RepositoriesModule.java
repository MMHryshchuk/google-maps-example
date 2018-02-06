package com.mmhdev.devcv.core.di.modules;




import com.mmhdev.devcv.core.android.App;
import com.mmhdev.devcv.domain.entities.DaoSession;
import com.mmhdev.devcv.domain.repository.EntitiesRepository;
import com.mmhdev.devcv.domain.repository.PreferenceRepository;
import com.mmhdev.devcv.domain.repository.impl.EntitiesRepositoryImpl;
import com.mmhdev.devcv.domain.repository.impl.PreferenceRepositoryImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 */
@Module(includes = {
        DataModule.class,
        MappersModule.class
})
public class RepositoriesModule {

    @Provides
    @Singleton
    EntitiesRepository provideEntitiesRepository(DaoSession daoSession){
        return new EntitiesRepositoryImpl(daoSession);
    }

    @Provides
    @Singleton
    PreferenceRepository providePreferenceRepository(App app){
        return new PreferenceRepositoryImpl(app);
    }
}
