package com.mmhdev.devcv.core.di.modules;


import com.mmhdev.devcv.core.android.AuthRetrofit;
import com.mmhdev.devcv.core.executors.PostExecutionThread;
import com.mmhdev.devcv.core.executors.ThreadExecutor;
import com.mmhdev.devcv.domain.repository.EntitiesRepository;
import com.mmhdev.devcv.domain.use_case.DirectionsUseCase;
import com.mmhdev.devcv.domain.use_case.MapUseCase;
import com.mmhdev.devcv.domain.use_case.UserUseCase;
import com.mmhdev.devcv.domain.use_case.impl.DirectionsUseCaseImpl;
import com.mmhdev.devcv.domain.use_case.impl.MapUseCaseImpl;
import com.mmhdev.devcv.domain.use_case.impl.UserUseCaseImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 */
@Module(includes = {
        RepositoriesModule.class,
        ThreadExecutorsModule.class,
        ApiModule.class
})
public class UseCaseModule {

    @Provides
    @Singleton
    UserUseCase provideUserUserCase(EntitiesRepository entitiesRepository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        return new UserUseCaseImpl(entitiesRepository, postExecutionThread, threadExecutor);
    }

    @Provides
    @Singleton
    DirectionsUseCase provideDirectionsUseCase(ThreadExecutor threadExecutor,
                                               PostExecutionThread postExecutionThread,
                                               AuthRetrofit authRetrofit) {
        return new DirectionsUseCaseImpl(threadExecutor, postExecutionThread, authRetrofit);
    }

    @Provides
    @Singleton
    MapUseCase provideMapUseCase(ThreadExecutor threadExecutor,
                                 PostExecutionThread postExecutionThread,
                                 Retrofit retrofit,
                                 AuthRetrofit authRetrofit) {
        return new MapUseCaseImpl(threadExecutor, postExecutionThread, retrofit, authRetrofit);
    }


}
