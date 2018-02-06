package com.mmhdev.devcv.core.di.modules;




import com.mmhdev.devcv.core.executors.PostExecutionThread;
import com.mmhdev.devcv.core.executors.ThreadExecutor;
import com.mmhdev.devcv.domain.executors.JobExecutor;
import com.mmhdev.devcv.domain.executors.UIThread;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 */
@Module
public class ThreadExecutorsModule {

    @Provides
    @Singleton
    ThreadExecutor provideThreadExecuter(){
        return new JobExecutor();
    }

    @Provides
    @Singleton
    PostExecutionThread providePostExecutionThread (){
        return new UIThread();
    }


}
