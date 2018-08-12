package com.atg.gautam.flickerpic.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/*
 ** Created by Gautam Krishnan {@link https://github.com/GautiKrish}
 */public class FlickrResponse {
    @SerializedName("photos")
    @Expose
    private Images images;
    @SerializedName("stat")
    @Expose
    private String stat;

    public Images getImages() {
        return images;
    }

    public void setImages(Images images) {
        this.images = images;
    }

    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }
}
