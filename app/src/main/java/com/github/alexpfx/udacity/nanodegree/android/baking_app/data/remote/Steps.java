package com.github.alexpfx.udacity.nanodegree.android.baking_app.data.local;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import com.github.alexpfx.udacity.nanodegree.android.baking_app.data.local.Recipe;
import com.google.gson.annotations.SerializedName;

/**
 * Created by alexandre on 24/05/2017.
 */


@Entity(tableName = "steps", foreignKeys = @ForeignKey(entity = Recipe.class,
        parentColumns = "id", childColumns = "recipeId"))
class Step {

    @PrimaryKey
    @SerializedName("id")
    private int id;

    @SerializedName("shortDescription")
    private String shortDescription;

    @SerializedName("description")
    private String description;

    @SerializedName("videoURL")
    private String videoURL;

    @SerializedName("thumbnailURL")
    private String thumbnailURL;

    private int recipeId;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVideoURL() {
        return videoURL;
    }

    public void setVideoURL(String videoURL) {
        this.videoURL = videoURL;
    }

    public String getThumbnailURL() {
        return thumbnailURL;
    }

    public void setThumbnailURL(String thumbnailURL) {
        this.thumbnailURL = thumbnailURL;
    }

    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }
}
