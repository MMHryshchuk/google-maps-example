package com.mmhdev.devcv.domain.repository;

/**
 */
public interface PreferenceRepository {
    boolean isFirstStart();
    void setFirstStart(boolean isFirstStart);
}
