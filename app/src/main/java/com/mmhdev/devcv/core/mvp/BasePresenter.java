package com.mmhdev.devcv.core.mvp;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 */
public abstract class BasePresenter<V> implements Presenter<V> {

    private V mView;
    private CompositeSubscription mCompositeSubscription;

    @Override
    public final void attachView(V view) {
        mView = view;
        mCompositeSubscription = new CompositeSubscription();
        onViewAttached();
    }

    @Override
    public final void detachView() {
        if (mCompositeSubscription != null)
            mCompositeSubscription.unsubscribe();
        mView = null;
        onViewDetached();
    }

    protected void onViewAttached(){

    }

    protected void onViewDetached(){

    }

    protected void addSubscription(Subscription subscriber){
        mCompositeSubscription.add(subscriber);
    }

    public final V getView() {
        return mView;
    }
}
