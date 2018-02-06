package com.mmhdev.devcv.core.rx;

import android.util.Log;

/**
 */
public class SimpleSubscriber<T> extends rx.Subscriber<T> {
    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        Log.e("error", "", e);
        e.printStackTrace();
    }


    @Override
    public void onNext(T item) {

    }
}
