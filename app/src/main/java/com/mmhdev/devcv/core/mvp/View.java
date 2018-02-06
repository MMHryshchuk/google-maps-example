package com.mmhdev.devcv.core.mvp;

/**
 */
public interface View<Presenter> {
    void attachPresenter(Presenter presenter);
    void detachPresenter();
}
