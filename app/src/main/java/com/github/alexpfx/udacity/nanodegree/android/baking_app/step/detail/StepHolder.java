package com.github.alexpfx.udacity.nanodegree.android.baking_app.step.detail;

import com.github.alexpfx.udacity.nanodegree.android.baking_app.data.pojo.Step;

/**
 * Created by alexandre on 04/06/2017.
 */

public class StepHolder {

    private Step previous;
    private Step actual;
    private Step next;

    public StepHolder(Step previous, Step actual, Step next) {
        this.previous = previous;
        this.actual = actual;
        this.next = next;
    }

    public Step getPrevious() {
        return previous;
    }

    public Step getActual() {
        return actual;
    }

    public Step getNext() {
        return next;
    }
}
