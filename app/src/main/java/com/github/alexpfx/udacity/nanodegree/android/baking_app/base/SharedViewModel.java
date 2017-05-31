package com.github.alexpfx.udacity.nanodegree.android.baking_app.base;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

/**
 * Created by alexandre on 30/05/2017.
 */

public class BaseSharedViewModel<T> extends ViewModel {

    private final MutableLiveData<T> selected = new MutableLiveData<>();


    public void select(T item) {
        selected.setValue(item);
    }

    public LiveData<T> getSelected() {
        return selected;
    }

}
