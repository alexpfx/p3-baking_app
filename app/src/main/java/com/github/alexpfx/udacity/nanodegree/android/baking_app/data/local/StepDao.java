package com.github.alexpfx.udacity.nanodegree.android.baking_app.data.local;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.github.alexpfx.udacity.nanodegree.android.baking_app.data.pojo.Step;

import java.util.List;

/**
 * Created by alexandre on 24/05/2017.
 */

@Dao
public interface StepDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Step step);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(Step step);

    @Delete
    void delete(Step step);

    @Query("select * from steps")
    LiveData<List<Step>> getAll();

    @Query("select * from steps where id = :id and recipeId = :recipeId")
    LiveData<Step> get(int id, int recipeId);

    @Query("select * from steps where recipeId = :recipeId")
    LiveData<List<Step>> getAllByRecipe(int recipeId);


}
