package com.example.colorchange;

import android.util.Log;

import java.util.Random;

public class GameManager {

    private int [] [] numbers;  //המטריציה שבה המספרים המבולגנים
    private  User [] users ;


    public GameManager(MemoryActivity gameActivity)
    {
        this.numbers=new int[4][5];
        newMatt();
        this.users=new User[4];
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


    public int getNumbers(int i,int j) {
        return numbers[i][j];
    }
public  boolean isSame (int prevJ ,int prevK,int currJ, int currK){

        return numbers[prevJ][prevK]== numbers[currJ][currK];
}

    public void setNumbers(int[][] numbers) {
        this.numbers = numbers;
    }

    public User getUser( int i) { //
        return users[i];
    }

    public void setUsers(User[] users) {    //
        this.users = users;
    }
}
