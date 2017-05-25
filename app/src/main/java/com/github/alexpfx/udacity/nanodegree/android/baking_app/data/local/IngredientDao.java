package com.github.alexpfx.udacity.nanodegree.android.baking_app.data;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by alexandre on 24/05/2017.
 */

@Dao
public interface IngredientDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Ingredient ingredient);


    @Query("select * from ingredients")
    List<Ingredient> getAll ();

}
