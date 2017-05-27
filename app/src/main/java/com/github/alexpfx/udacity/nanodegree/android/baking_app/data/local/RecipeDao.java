package com.github.alexpfx.udacity.nanodegree.android.baking_app.data.local;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.github.alexpfx.udacity.nanodegree.android.baking_app.data.pojo.Recipe;

import java.util.List;

/**
 * Created by alexandre on 24/05/2017.
 */

@Dao
public interface RecipeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert (Recipe recipe);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update (Recipe recipe);

    @Delete
    void delete (Recipe recipe);

    @Query("select * from Recipes")
    LiveData<List<Recipe>> getAll ();

    @Query("select * from recipes where id = :id")
    LiveData<Recipe> getById (int id);



}
