package com.example.colorchange;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MemoryActivity extends AppCompatActivity implements View.OnClickListener{

    // שלב א הגדרת רכיבים
    private String name1,
                    name2;

    private TextView tvName1,tvName2;

    private Button btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory);

        // שלב ב קישור רכיבים
        tvName1= (TextView) findViewById(R.id.tvName1);
        tvName2= (TextView) findViewById(R.id.tvName2);

        btnBack =(Button) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(this);
        Intent in=getIntent();
        if(in!=null)
        {
            Bundle xtras=in.getExtras();

            name1=xtras.getString("DATA1");
            name2=xtras.getString("DATA2");

        }
        tvName1.setText(name1);
        tvName2.setText(name2);




    }

    public void onClick(View v)
    {
        if(v.getId()==btnBack.getId())
        {
            Intent intent=new Intent(this, AddPlayer.class);



            startActivity(intent);
        }
    }


}