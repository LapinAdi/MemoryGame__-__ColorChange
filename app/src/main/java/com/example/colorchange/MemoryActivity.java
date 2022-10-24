package com.example.colorchange;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.inputmethodservice.Keyboard;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
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
            btnRestart,btnMyCards;

    private int[] pics = {R.drawable.img0, R.drawable.img1, R.drawable.img2, R.drawable.img3, R.drawable.img4, R.drawable.img5, R.drawable.img6, R.drawable.img7, R.drawable.img8, R.drawable.img9};          // מערך של כל התמונות

    private int index,
            whosTurn,   //
            numOfClicks,
            currJ,
            currK,
            prevJ,
            prevK;

    private boolean turn = false;
    private GameManager gManager;
    private ImageButton[][] cards;    //

    public static final int ROW = 4, COL = 5;       // fix

    private final Handler handler = new Handler();

    //---הגדרת דיאלוג ותכונותיו---

    private Dialog dialog; //עבור החלון הקופץ
    private TextView tvDname ; //שם השחקן לו משוייך הדיאלוג
    private  Button btnDback,getBtnMyCards; // כפתור חזרה למשחק
    private ImageButton [] selfButtons;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory);


        this.cards = new ImageButton[ROW][COL];

        //this.cards =new ImageButton[4][5];        //

        // שלב ב קישור רכיבים
        tvName1 = (TextView) findViewById(R.id.tvName1);
        tvName2 = (TextView) findViewById(R.id.tvName2);

        // ------------------------------------------
        Intent in = getIntent();
        if (in != null) {
            Bundle xtras = in.getExtras();

            name1 = xtras.getString("DATA1");
            name2 = xtras.getString("DATA2");

        }
        tvName1.setText(name1);
        tvName2.setText(name2);
        //----------------------------------------

        this.gManager = new GameManager(this, name1, name2);

        tvScore1 = findViewById(R.id.tvScore1);
        tvScore2 = findViewById(R.id.tvScore2);

        tvScore1.setText("0");
        tvScore2.setText("0");

        btnBack = (Button) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(this);
        btnRestart = (Button) findViewById(R.id.btnRestart);
        btnRestart.setOnClickListener(this);

        btnMyCards=(Button)findViewById(R.id.btnMyCards) ;
        btnMyCards.setOnClickListener(this);

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

        // -----אתחול מערך התמונות של הדיאלוג ------
        selfButtons = new ImageButton[10];



    }

    public void onClick(View v) {

        System.out.println("YAWDADAW");
        // ---------------------------------------------------כפתור איתחול --------
        if (v.getId() == btnRestart.getId()) {

            // משבץ תמונת אוגר לכל המטריצייה

            for (int j = 0; j < MemoryActivity.ROW; j++) {
                for (int k = 0; k < MemoryActivity.COL; k++) {

                    this.cards[j][k].setImageResource(R.drawable.hampster);
                    this.cards[j][k].setVisibility(View.VISIBLE);
                    this.cards[j][k].setClickable(true);

                }
            }
            gManager.newMatt();
        }


        // מחליף לתמונה הרצוייה כאשר לוחצים עליה
        // מראה מיקום של הכפתור בעזרת טוסט

        boolean found = false;
        for (int j = 0; j < MemoryActivity.ROW && !found; j++) {
            for (int k = 0; k < MemoryActivity.COL && !found; k++) {
                if (v.getId() == this.cards[j][k].getId()) {
                    found = true;
                    this.cards[j][k].setImageResource(pics[gManager.getNumbers(j, k)]);
                    // Toast.makeText(getApplicationContext(), " " + j + ":" + k, Toast.LENGTH_LONG).show();
                    Toast.makeText(getApplicationContext(), " " + gManager.getNumbers(j, k) + " ", Toast.LENGTH_LONG).show();
                    //helps to check first or seconde card
                    numOfClicks++;

                    if (numOfClicks == 1) {
                        prevJ = j;
                        prevK = k;
                        this.cards[prevJ][prevK].setClickable(false); //disable button
                    } else {
                        currJ = j;
                        currK = k;
                        this.cards[currJ][currK].setClickable(false);
                        numOfClicks = 0;  //  end of the turn

                        if (gManager.isSame(prevJ, prevK, currJ, currK)) {

                            handler.postDelayed(() -> {
                                this.cards[currJ][currK].setVisibility(View.INVISIBLE);
                                this.cards[prevJ][prevK].setVisibility(View.INVISIBLE);
                            }, 500);

                            //score
                            gManager.incScore(turn);
                            gManager.addCard(gManager.getNumbers(currJ, currK), turn);
                            updateScore();

                            //puts -1 insted of the num picture
                            //gManager.setNumbers(prevJ,prevK,-1);
                            //gManager.setNumbers(currJ,currK,-1);
                        } else {
                            this.cards[prevJ][prevK].setClickable(true);
                            this.cards[currJ][currK].setClickable(true);
                            //turns the cards back
                            handler.postDelayed(() -> {
                                this.cards[prevJ][prevK].setImageResource(R.drawable.hampster);
                                this.cards[currJ][currK].setImageResource(R.drawable.hampster);
                            }, 500);


                        }
                        turn=!turn;
                    }

                }
            }
        }

        // ---------------------------------------------------כפתור חזרה --------
        if (v.getId() == btnBack.getId()) {
            Intent intent = new Intent(this, AddPlayer.class);

            startActivity(intent);
        }

        //הדיאלוג נפתח במהלך המשחק על ידי לחיצה  כל שחקן יוכל להציג בתורו את מצב הקלפים שבידיו
        if (v.getId()== btnMyCards.getId()){
            createDilog();
        }


    }


    public  void  updateScore (){
        // udates  score every turn the func is called
        tvScore1.setText("" + gManager.getScore(false)+"");
        tvScore2.setText(""+gManager.getScore(true)+"");

    }

    //---יצירת הדיאלוג ---

    public void createDilog (){
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.custom_layout);
        dialog.setTitle("choose a card ");
        dialog.setCancelable(true);


        tvDname=(TextView) dialog.findViewById(R.id.tvDname);
        tvDname.setText(""+gManager.getName(turn)) ;    //משיג את שם בשחקן לפי התור

        btnDback = (Button) dialog.findViewById(R.id.btnDback);
        btnDback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {        //בלחיצה סגור את חלון הדיאלוג
                dialog.cancel();
            }
        });

        String str = "";
        int resId;

        //-בניית רשימת התמונות שבידי השחקן -
        for(int i=0;i<10;i++){
            str="imgCard"+i;
            resId= getResources().getIdentifier(str,"id",getPackageName());
            selfButtons[i]=dialog.findViewById(resId);
            selfButtons[i].setVisibility(View.VISIBLE);
        }
        playerHand(); // הצגת הקלפים שבידי השקחן הנוכחי
        dialog.show();
    }

    //--פעולה המציגה ויזואלית את הקלפים שבידי השחקן --

    public void playerHand(){
        Log.d("****",'\n'+ gManager.returnPlayer(turn).toString());

        //-הצגת הקלפים שעל ידי השחקן -
        for(int i=0;i< gManager.returnPlayer(turn).getPics().size() ;i++) {
           System.out.println(" "+i +" player: "+this.turn + "size: " + gManager.returnPlayer(turn).getPics().size());
            selfButtons[i].setImageResource(pics[gManager.returnPlayer(turn).getPics().get(i)]);
        }
        //הסתרת הכפתורים החסרים
        for(int i = gManager.returnPlayer(turn).getPics().size(); i < 10; i++) {
            selfButtons[i].setVisibility(View.INVISIBLE);
        }
    }

}