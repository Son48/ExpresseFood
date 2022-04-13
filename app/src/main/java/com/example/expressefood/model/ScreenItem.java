package com.example.expressefood.model;

public class ScreenItem {
    String Title,Description;
    int url_img;

    public ScreenItem() {
    }

    public ScreenItem(String title, String description, int url_img) {
        Title = title;
        Description = description;
        this.url_img = url_img;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public int getUrl_img() {
        return url_img;
    }

    public void setUrl_img(int url_img) {
        this.url_img = url_img;
    }
}
