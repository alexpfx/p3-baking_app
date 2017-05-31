package com.github.alexpfx.udacity.nanodegree.android.baking_app.base;

import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;

/**
 * Created by alexandre on 28/05/2017.
 */

public abstract class BaseViewModel<T, P> extends ViewModel {
    private T mRepository;

    public BaseViewModel(@NonNull T repository) {
        mRepository = repository;
    }

    protected T getRepository() {
        return mRepository;
    }

    protected abstract void initialize (P ... params);


}
