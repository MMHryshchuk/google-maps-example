package com.mmhdev.devcv.core.android;

/**
 */
public class Optional<T> {

    T fragment;

    public Optional(T fragment) {
        this.fragment = fragment;
    }

    public boolean isFragment(){
        return fragment != null;
    }

    public T get() {
        return fragment;
    }
}
