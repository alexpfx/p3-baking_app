package com.github.alexpfx.udacity.nanodegree.android.baking_app.data.pojo;

/**
 * Created by alexandre on 05/06/2017.
 */
public class IdsTO {
    public IdsTO(Integer id, Integer recipeId) {
        this.id = id;
        this.recipeId = recipeId;
    }

    private Integer id;
    private Integer recipeId;

    public Integer getId() {
        return id;
    }

    public Integer getRecipeId() {
        return recipeId;
    }
}
