package com.example.myfacedetectionapp;

import android.graphics.Bitmap;

public class ModelClass {

    String imageID;
    Bitmap image;

    public ModelClass(String imageID, Bitmap image) {
        this.imageID = imageID;
        this.image = image;
    }

    public String getImageID() {
        return imageID;
    }

    public void setImageID(String imageID) {
        this.imageID = imageID;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }
}
