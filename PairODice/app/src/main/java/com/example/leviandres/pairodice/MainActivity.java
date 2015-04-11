package com.example.leviandres.pairodice;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.FrameStats;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;


public class MainActivity extends ActionBarActivity {
    private FrameLayout die1,die2;
    private Button roll, hold;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        die1 = (FrameLayout)findViewById(R.id.die1);
//        die2 = (FrameLayout)findViewById(R.id.die2);

        roll = (Button) findViewById(R.id.button);

        roll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rollDice();

            }
        });
        hold = (Button) findViewById(R.id.hold);

        hold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, player2.class);
                intent.putExtra("score", 99);
                startActivity(intent);

            }
        });

        die1 = (FrameLayout)findViewById(R.id.die1);
        die2 = (FrameLayout)findViewById(R.id.die2);
    }

//get two random numbers, change the dice to have the appropriate image
    public void rollDice()
    {
        int roll1 = 1 + (int)(6*Math.random());
        int roll2 = 1 + (int)(6*Math.random());

        setDie(roll1,die1);
        setDie(roll2,die2);




    }


// set appropriate image to frameview fo an int
    public void setDie(int value,FrameLayout die)
    {
        Drawable pic = null;
        switch (value)
        {
            case 1:
                pic= getResources().getDrawable(R.drawable.die1);
                break;
            case 2:
                pic= getResources().getDrawable(R.drawable.die2);
                break;
            case 3:
                pic= getResources().getDrawable(R.drawable.die3);
                break;
            case 4:
                pic= getResources().getDrawable(R.drawable.die4);
                break;
            case 5:
                pic= getResources().getDrawable(R.drawable.die5);
                break;
            case 6:
                pic= getResources().getDrawable(R.drawable.die6);
                break;

        }
        die.setBackground(pic);
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
