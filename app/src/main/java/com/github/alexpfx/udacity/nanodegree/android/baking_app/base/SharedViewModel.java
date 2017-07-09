package com.github.alexpfx.udacity.nanodegree.android.baking_app.base;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

/**
 * Created by alexandre on 30/05/2017.
 */

public class SharedViewModel<T> extends ViewModel {

    private final MutableLiveData<T> selected = new MutableLiveData<>();
    private OnSelectListener<T> listener = item -> {};

    public SharedViewModel() {
    }

    public interface OnSelectListener <T> {
        void selected (T item);
    }


    public void setListener(OnSelectListener<T> listener) {
        this.listener = listener;
    }

    public void select(T item) {
        selected.setValue(item);
        listener.selected(item);
    }

    public LiveData<T> getSelected() {
        return selected;
    }



}
