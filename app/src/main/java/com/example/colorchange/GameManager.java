package com.example.colorchange;

import android.util.Log;

import java.util.Random;

public class GameManager {

    private int [] [] numbers;  //המטריציה שבה המספרים המבולגנים
    private  User [] users ;


    public GameManager(MemoryActivity gameActivity,  String player1, String player2)
    {
        this.numbers=new int[4][5];
        newMatt();
        this.users=new User[2];
        this.users[0] = new User(player1);
        this.users[1] = new User(player2);
    }


    public void newMatt()
    {
        int [] random = {1,1,2,2,3,3,4,4,5,5,6,6,7,7,8,8,9,9,0,0};
       shuffle(random);
        matt(random);
    }
    private void shuffle (int [] arr){
        //מקבלים מערך
        //מחזירים אותו מבולגן
        Random rnd = new Random();
        int ran;
        int temp ;

        for(int i=0;i<arr.length;i++){

            ran = rnd.nextInt(20);      //מגרילים מספר
            temp=arr[i];                       // שמים במשתנה את הערךשל התא במקום האינדקס
            arr[i]=arr[ran];                    // שמים במקום האינדקס את הערך במיקום הרנדומלי
            arr[ran]=temp;                      // שמים במקום הרדומלי את מה שבטמפ

        }


    }

    private  void matt ( int [] rndArr) {

        int j = 0, k = 0;
        int i = 0;
        while (j < 4) {
            while (k < 5) {
                this.numbers[j][k] = rndArr[i];
                System.out.println(i);
                i++;
                k++;

            }
            j++;
            k = 0;
        }


    }

    public void addCard(int card, boolean player) {
        returnPlayer(player).getPics().add(card);
    }

public  void incScore ( boolean player) {

        // if (player) 1 else 0
        //users[player ? 1 : 0].addScore();

    if (player){
        users[1].addScore();
    }
    else {
        users[0].addScore();
    }
}

public  int getScore (boolean player){

        if (player){
            return this.users[1].getScore();
        }
        else
            return  this.users[0].getScore();

}

    public  String getName (boolean player){

        if (player){
            return this.users[1].getName();
        }
        else
            return  this.users[0].getName();

    }

    public User returnPlayer(boolean player){
        if (player){
            return this.users[1];
        }
        else
            return  this.users[0];
    }

    public int getNumbers(int i,int j) {
        return numbers[i][j];
    }
public  boolean isSame (int prevJ ,int prevK,int currJ, int currK){

        return numbers[prevJ][prevK]== numbers[currJ][currK];
}

    public void setNumbers(int j ,int k  ,int num) {
        this.numbers[j][k] = num;
    }

}
