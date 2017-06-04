package com.github.alexpfx.udacity.nanodegree.android.baking_app.recipe;

import android.arch.persistence.room.Room;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.github.alexpfx.udacity.nanodegree.android.baking_app.R;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.data.Cache;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.data.CacheImpl;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.data.local.BakingAppDatabase;
import com.github.alexpfx.udacity.nanodegree.android.baking_app.data.remote.RecipeService;

public class RecipeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        BakingAppDatabase database =
                Room.databaseBuilder(getApplicationContext(), BakingAppDatabase.class, "baking_database").build();
        Cache cache = new CacheImpl(new RecipeService(), database);
        cache.update();


    }

}
