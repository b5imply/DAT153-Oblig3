package com.example.testing.dat153_oblig3.classes;

import android.graphics.Bitmap;

public class ImageItem {
    private Bitmap image;
    private String title;
    private String imgPath;

    public ImageItem(Bitmap image, String imgPath, String title) {
        super();
        this.image = image;
        this.title = title;
        this.imgPath = imgPath;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}