package com.example.colorchange;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        // שלב ב - קישור לרכיבים במסך
        lyOut=(LinearLayout) findViewById(R.id.lyOut);
        btnChange=(Button) findViewById(R.id.btnChange);
        btnChangeRnd=(Button) findViewById(R.id.btnChangeRnd);
        btnChangeImg = (Button) findViewById(R.id.btnChangeImg);
        tvText=(TextView) findViewById(R.id.tvText);
        tvColor1=(TextView) findViewById(R.id.tvColor1);
        tvColor2=(TextView) findViewById(R.id.tvColor2);





        btnChange.setOnClickListener(this);
        btnChangeRnd.setOnClickListener(this);
        btnChangeImg.setOnClickListener(this);


        int img1= R.drawable.sky1;
        int img2= R.drawable.sky2;


    }


    // שלב א - הגדרת הרכיבים שנרצה לתכנת

    private Button btnChange,      //כפתור החלף צבע
            btnChangeRnd,   //כפתור צבע אקראי
            btnChangeImg;          //כפתור רקע

    private TextView tvText,        //תיבת הבקשה(לחץ על הכפתור)
            tvColor1,               //תיבה 1 - צבע אדום או צהוב
            tvColor2;               //תיבה 2 - צבע אקראי

    private LinearLayout lyOut;        //רקע המסך

    private boolean okColor=true,       //אמת אם צבע \ תמונת רקע 1
            okImg=true;             //אמת אם צבע \ תמונת רקע 2

    Random rnd = new Random ();         //שומר מספר אקראי לצבע

    private int red,
            green,
            blue;

    @Override
    public void onClick(View view)
    {

        if (view==btnChange)
        {
            if(okColor) {
                tvColor1.setBackgroundColor(Color.GREEN);
                tvText.setText("ירוק");
                tvText.setTextColor(Color.GREEN);
            } else {
                tvColor1.setBackgroundColor(Color.RED);
                tvText.setText("אדום");
                tvText.setTextColor(Color.RED);
            }
            okColor = !okColor;
        } else if(view == btnChangeImg) {
            if(okImg)
                lyOut.setBackgroundResource(R.drawable.sky2);
            else
                lyOut.setBackgroundResource(R.drawable.sky1);
            okImg = !okImg;
        } else if(view == btnChangeRnd) {
            red = rnd.nextInt(256);
            green = rnd.nextInt(256);
            blue = rnd.nextInt(256);

            tvColor2.setBackgroundColor(Color.rgb(red,green,blue));
        }
    }









}