package com.github.alexpfx.udacity.nanodegree.android.baking_app.data.local;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.github.alexpfx.udacity.nanodegree.android.baking_app.data.pojo.Ingredient;

import java.util.List;

/**
 * Created by alexandre on 24/05/2017.
 */

@Dao
public interface IngredientDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Ingredient ingredient);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(Ingredient ingredient);

    @Delete
    void delete(Ingredient ingredient);

    @Query("select * from ingredients")
    LiveData<List<Ingredient>> getAll();

    @Query("select * from ingredients where id = :id and recipeId= :recipeId")
    LiveData<Ingredient> get(int id, int recipeId);

    @Query("select * from ingredients where recipeId = :recipeId")
    LiveData<List<Ingredient>> getAllByRecipeId(int recipeId);


}
