package com.example.colorchange;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Locale;

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

        //מאתחל  את הטקסט טו ספיצ
        textToSpeech = new TextToSpeech(getApplicationContext(),new TextToSpeech.OnInitListener() {

            @Override
            public void onInit(int i){
                if(i== TextToSpeech.SUCCESS){
                    int lang = textToSpeech.setLanguage(Locale.ENGLISH);
                }
            }
        });

    }


    // שלב א - הגדרת הרכיבים שנרצה לתכנת

    private Button btnPlay,      //כפתור
            btnAddBack;          //

    private EditText  etName1,  //
            etName2;            //


    private TextToSpeech textToSpeech; //כשעוברים למסך הבא  מופעל רמקול שאומר את השמות


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

            if (name1.length() ==0 || name2.length()==0|| name1.equals(name2)){
                new AlertDialog.Builder(this)
                        .setTitle("ERORE")
                        .setMessage("not a good name")
                        .setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        })
                        .setIcon(R.drawable.error)
                        .show();
            }
            else{
                //משמיע טקסט

                textToSpeech.speak("Enjoy and Good Luck! " + name1 + " and " + name2, TextToSpeech.QUEUE_FLUSH, null);


                intent.putExtra("DATA1",name1);
                intent.putExtra("DATA2", name2);

                startActivity(intent);
            }






        }
    }




}