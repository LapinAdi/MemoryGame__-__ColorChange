package com.example.colorchange;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class MemoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory);

        Intent in=getIntent();
        if(in!=null)
        {
            Bundle xtras=in.getExtras();
            System.out.println(xtras.getString("DATA1"));
            System.out.println(xtras.getString("DATA2"));
        }




    }


}