package com.mmhdev.devcv.core.di.modules;



import com.mmhdev.devcv.core.android.App;
import com.mmhdev.devcv.domain.entities.DaoMaster;
import com.mmhdev.devcv.domain.entities.DaoSession;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 */
@Module
public class DataModule {

    @Provides
    @Singleton
    DaoSession provideDaoSession(App app){
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(app, "base-test", null);
        DaoMaster master = new DaoMaster(helper.getWritableDatabase());
        return master.newSession();
    }
}
