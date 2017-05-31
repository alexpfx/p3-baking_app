package com.github.alexpfx.udacity.nanodegree.android.baking_app.step.detail;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.alexpfx.udacity.nanodegree.android.baking_app.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class StepViewFragment extends Fragment {


    public StepViewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_step_view, container, false);
    }

}
