package com.example.colorchange;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AddPlayer extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_player);



        // שלב ב - קישור לרכיבים במסך

        btnPlay=(Button) findViewById(R.id.btnPlay);
        btnAddBack=(Button) findViewById(R.id.btnAddBack);

        etName1=( EditText) findViewById(R.id.etName1);
        etName2=( EditText) findViewById(R.id.etName2);

        btnPlay.setOnClickListener(this);
    }


    // שלב א - הגדרת הרכיבים שנרצה לתכנת

    private Button btnPlay,      //כפתור
            btnAddBack;          //

    private EditText  etName1,  //
            etName2;            //





   //שלב ג - מעבר בין ADD PLAYER לדף של משחק הזיכרון
    // מעביר את שמות המשתמשים למשחק (כמו תיבת דואר )


    @Override
    public void onClick(View v)
    {
        if(v.getId()==btnPlay.getId())
        {
            Intent intent=new Intent(this, MemoryActivity.class);

            String name1 = etName1.getText().toString();
            String name2 =etName2.getText().toString();

            intent.putExtra("DATA1",name1);
            intent.putExtra("DATA2", name2);

            startActivity(intent);
        }
    }




}