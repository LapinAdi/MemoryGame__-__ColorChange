package com.example.colorchange;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.inputmethodservice.Keyboard;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MemoryActivity extends AppCompatActivity implements View.OnClickListener {

    // שלב א הגדרת רכיבים
    private String name1,
            name2;

    private TextView tvName1, tvName2,
            tvScore1, tvScore2;

    private Button btnBack,
            btnRestart;

    private int[] pics = {R.drawable.img0, R.drawable.img1, R.drawable.img2, R.drawable.img3, R.drawable.img4, R.drawable.img5, R.drawable.img6, R.drawable.img7, R.drawable.img8, R.drawable.img9};          // מערך של כל התמונות

    private int index,
            whosTurn,   //
            numOfClicks;

    private GameManager gManager;
    private ImageButton[][] cards;    //

    public static final int ROW = 4, COL = 5;       // fix


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory);

        this.gManager = new GameManager(this);

        this.cards = new ImageButton[ROW][COL];

        //this.cards =new ImageButton[4][5];        //

        // שלב ב קישור רכיבים
        tvName1 = (TextView) findViewById(R.id.tvName1);
        tvName2 = (TextView) findViewById(R.id.tvName2);

        btnBack = (Button) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(this);
        btnRestart = (Button) findViewById(R.id. btnRestart);
        btnRestart.setOnClickListener(this);


        // מגדירה את כל הכפתורים במטריצייה

        String str = "";
        int resId;
        for (int j = 0; j < MemoryActivity.ROW; j++) {
            for (int k = 0; k < MemoryActivity.COL; k++) {
                str = "p" + j + k;
                resId = getResources().getIdentifier(str, "id", getPackageName());
                this.cards[j][k] = (ImageButton) findViewById(resId);
                this.cards[j][k].setOnClickListener(this);
            }
        }

        //------------------------------------------------------------------------------------


        //
        Intent in = getIntent();
        if (in != null) {
            Bundle xtras = in.getExtras();

            name1 = xtras.getString("DATA1");
            name2 = xtras.getString("DATA2");

        }
        tvName1.setText(name1);
        tvName2.setText(name2);


    }

    public void onClick(View v) {

        // ---------------------------------------------------כפתור איתחול --------
        if (v.getId() == btnRestart.getId()) {

            // משבץ תמונת אוגר לכל המטריצייה

            for (int j = 0; j < MemoryActivity.ROW ; j++) {
                for (int k = 0; k < MemoryActivity.COL ; k++) {

                        this.cards[j][k].setImageResource(R.drawable.hampster);
                    }
                }
            }

            gManager.newMatt();




        // מחליף לתמונה הרצוייה כאשר לוחצים עליה
        // מראה מיקום של הכפתור בעזרת טוסט

        boolean found = false;
        for (int j = 0; j < MemoryActivity.ROW && !found; j++) {
            for (int k = 0; k < MemoryActivity.COL && !found; k++) {
                if (v.getId() == this.cards[j][k].getId()) {
                    found = true;
                    this.cards[j][k].setImageResource(pics[gManager.getNumbers(j, k)]);
                    Toast.makeText(getApplicationContext(), " " + j + ":" + k, Toast.LENGTH_LONG).show();
                }
            }
        }

        // ---------------------------------------------------כפתור חזרה --------
        if (v.getId() == btnBack.getId()) {
            Intent intent = new Intent(this, AddPlayer.class);

            startActivity(intent);
        }


    }


}