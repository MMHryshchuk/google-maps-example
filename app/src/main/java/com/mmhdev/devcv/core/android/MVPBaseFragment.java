package com.mmhdev.devcv.core.android;


import com.mmhdev.devcv.core.mvp.Presenter;
import com.mmhdev.devcv.core.mvp.View;

/**
 */
public class MVPBaseFragment<F extends Presenter> extends BaseFragment implements View<F> {

    private F presenter;

    @Override
    public void attachPresenter(F f) {
        presenter = f;
    }

    @Override
    public void detachPresenter() {
        if (presenter != null)
            presenter.detachView();
        presenter = null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        detachPresenter();
    }

    public F getPresenter() {
        return presenter;
    }
}
