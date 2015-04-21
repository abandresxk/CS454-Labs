package com.example.leviandres.pairodice;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.leviandres.pairodice.R;

import org.w3c.dom.Text;


public class MainActivity extends ActionBarActivity {
    private FrameLayout die1, die2;
    private Button roll, hold;
    private int score=0;
    private int temp=0;
    private int myScore=0;
    private int rnd=0;
    TextView p2txt;

    TextView p1;
    TextView round;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        myScore = intent.getIntExtra("score", 0);
        score=intent.getIntExtra("p1",0);
        rnd=intent.getIntExtra("round",0);

        round=(TextView) findViewById(R.id.round);
        round.setText("Round: "+rnd);
        p2txt=(TextView) findViewById(R.id.p2);
        p2txt.setText("P2: "+myScore);


        p1=(TextView)findViewById(R.id.p1);
        p1.setText("P1: "+score);
        roll = (Button) findViewById(R.id.button);
        roll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rollDice();
                p1.setText("P1: "+score);
                if(score>=100) {
                    AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                    alertDialog.setTitle("Winner!");
                    alertDialog.setMessage("You WIN!!!");
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                    Intent intent = new Intent(MainActivity.this,player2.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                                    startActivity(intent);
                                }
                            });

                    alertDialog.show();
                }
            }
        });

        hold = (Button)findViewById(R.id.hold);
        hold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*The commented out code here was the code we used
                in class to send an integer to the next activty.
                It was replaced by an alert dialog to be used to indicate
                a winner (for demonstration purposes).
                Use the alert dialog code in your program where appropriate*/
                Intent intent = new Intent(MainActivity.this,player2.class);
                intent.putExtra("round",rnd);
                intent.putExtra("score", score);
                intent.putExtra("p2",myScore);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(intent);
            }
        });


        die1 = (FrameLayout) findViewById(R.id.die1);
        die2 = (FrameLayout) findViewById(R.id.die2);

    }

    //get two random ints between 1 and 6 inclusive
    public void rollDice() {

        int val1 = 1 + (int) (6 * Math.random());
        int val2 = 1 + (int) (6 * Math.random());
        if(val1==1 || val2==1)
        {

            AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                alertDialog.setTitle("Rolled a 1");
                alertDialog.setMessage("You Rolled a 1, Points this round will not add to your score");
                score-=temp;
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                Intent intent = new Intent(MainActivity.this,player2.class);
                                intent.putExtra("round",rnd);
                                intent.putExtra("score", score);
                                intent.putExtra("p2",myScore);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                                startActivity(intent);
                            }
                        });
                alertDialog.show();

        }
        else
        {

            setDie(val1, die1);
            setDie(val2, die2);

        }

    }

    //set the appropriate picture for each die per int
    public void setDie(int value, FrameLayout layout) {
        Drawable pic = null;

        switch (value) {
            case 1:
                pic = getResources().getDrawable(R.drawable.die1);


                break;
            case 2:
                pic = getResources().getDrawable(R.drawable.die2);
                score+=2;
                temp+=2;
                break;
            case 3:
                pic = getResources().getDrawable(R.drawable.die3);
                score+=3;
                temp+=3;
                break;
            case 4:
                pic = getResources().getDrawable(R.drawable.die4);
                score+=4;
                temp+=4;
                break;
            case 5:
                pic = getResources().getDrawable(R.drawable.die5);
                score+=5;
                temp+=5;
                break;
            case 6:
                pic = getResources().getDrawable(R.drawable.die6);
                score+=6;
                temp+=6;
                break;
        }
        layout.setBackground(pic);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}