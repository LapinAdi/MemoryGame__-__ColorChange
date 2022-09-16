package com.example.colorchange;

import android.media.Image;

public class User {


    private  String name;
    private  int score;
    private int  [] pics;       //  קלפים שנמצאו
    private int size=10;

    public User(String name)
    {
        this.name=name;
        this.score=0;
        this.pics=new int[size];
    }

}
