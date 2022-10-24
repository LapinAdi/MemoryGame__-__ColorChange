package com.example.colorchange;

import android.media.Image;

import java.util.ArrayList;

public class User {


    private  String name;
    private  int score;
    private ArrayList<Integer> pics;       //  קלפים שנמצאו
    private int size=10;

    public User(String name)
    {
        this.name=name;
        this.score=0;
        this.pics = new ArrayList<>();
    }

    public void addScore (){
        this.score++;
    }

    public  int  getScore(){

        return this.score;

    }

    public ArrayList<Integer> getPics() {
        return pics;
    }

    public String getName() {
        return name;
    }
}
