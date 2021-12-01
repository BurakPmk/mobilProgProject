package com.example.test03;

import android.graphics.Bitmap;

public class Person {
    private String name;
    private String number;
    private Bitmap photo = null;

    public Person(String name, String number, Bitmap photo) {
        this.name = name;
        this.number = number;
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Bitmap getPhoto() {
        return photo;
    }

    public void setPhoto(Bitmap photo) {
        this.photo = photo;
    }
}
