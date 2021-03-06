package com.mmhdev.devcv.domain.entities;

import android.database.sqlite.SQLiteDatabase;

import java.util.Map;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.AbstractDaoSession;
import de.greenrobot.dao.identityscope.IdentityScopeType;
import de.greenrobot.dao.internal.DaoConfig;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig mainEntityDaoConfig;

    private final MainEntityDao mainEntityDao;

    public DaoSession(SQLiteDatabase db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        mainEntityDaoConfig = daoConfigMap.get(MainEntityDao.class).clone();
        mainEntityDaoConfig.initIdentityScope(type);

        mainEntityDao = new MainEntityDao(mainEntityDaoConfig, this);

        registerDao(MainEntity.class, mainEntityDao);
    }
    
    public void clear() {
        mainEntityDaoConfig.getIdentityScope().clear();
    }

    public MainEntityDao getMainEntityDao() {
        return mainEntityDao;
    }

}
