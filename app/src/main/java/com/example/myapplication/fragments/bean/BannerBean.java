package com.example.myapplication.fragments.bean;

/**
 * Create By shaodong on 2021/8/3 9:46
 */
public class BannerBean {
    private String imageUrl;

    public BannerBean() {
    }

    public BannerBean(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
