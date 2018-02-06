package com.mmhdev.devcv.core.mvp;

/**
 */
public interface Presenter<V> {
    void attachView(V view);
    void detachView();
    V getView();
}
