package com.atg.gautam.flickerpic.model;

import com.google.gson.annotations.SerializedName;

/*
 ** Created by Gautam Krishnan {@link https://github.com/GautiKrish}
 */public class Image {

    @SerializedName("id")
    private String id;
    @SerializedName("url_s")
    private String imageUrl;


    public void setId(String id) {
        this.id = id;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getId() {
        return id;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
