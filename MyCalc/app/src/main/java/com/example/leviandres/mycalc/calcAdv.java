package com.example.leviandres.mycalc;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import java.lang.Math;


public class calcAdv extends ActionBarActivity {
    boolean clearScreen = true;
    boolean opState = false;
    float Operand1 = 0f;
    float Operand2 = 0f;
    float Answer = 0f;
    String Operator = "";
    String value="";
    String newValue;
    int mod;
    int pow=0;
    boolean inState = false;
    boolean last = false;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.advcalc);
        EditText screen = (EditText) findViewById(R.id.screen);

        Intent intent = getIntent();
      //  count=intent.getIntExtra("count",1);
        value=intent.getStringExtra("gets");

        screen.setText(value);
        Operand1= Float.parseFloat(value);


    }



    public void set_operator(String operator) {
        EditText screen = (EditText) findViewById(R.id.screen);
        if (screen.getText().toString().equals(".")) screen.setText("0");
        if (this.inState && this.opState && this.last) {
            specialCalc();
        }

        this.opState = true;
        this.clearScreen = true;
        this.last = false;
        if (operator.equals("sin")) {
            this.Answer = (float) Math.sin(Double.parseDouble(value));
            screen.setText(this.Answer + "");
            this.clearScreen = true;
            this.Operand1 = 0f;
            this.Operand2 = 0f;
            this.Operator = "";
            this.last = true;
            this.opState = false;
        }
        else if (operator.equals("cos")) {
            this.Answer = (float) Math.cos(Double.parseDouble(value));
            screen.setText(this.Answer + "");
            this.clearScreen = true;
            this.Operand1 = 0f;
            this.Operand2 = 0f;
            this.Operator = "";
            this.last = true;
            this.opState = false;
        }
        else if (operator.equals("tan")) {
            this.Answer = (float) Math.tan(Double.parseDouble(value));
            screen.setText(this.Answer + "");
            this.clearScreen = true;
            this.Operand1 = 0f;
            this.Operand2 = 0f;
            this.Operator = "";
            this.last = true;
            this.opState = false;
        }
        else if (operator.equals("i")) {

            screen.setText("ERROR");
            this.clearScreen = true;
            this.Operand1 = 0f;
            this.Operand2 = 0f;
            this.Operator = "";
            this.last = true;
            this.opState = false;
        }
        else if (operator.equals("ln")) {
            this.Answer = (float) Math.log(Double.parseDouble(value));
            screen.setText(this.Answer + "");
            this.clearScreen = true;
            this.Operand1 = 0f;
            this.Operand2 = 0f;
            this.Operator = "";
            this.last = true;
            this.opState = false;
        }
        else if (operator.equals("log")) {
            this.Answer = (float) Math.log10(Double.parseDouble(value));
            screen.setText(this.Answer + "");
            this.clearScreen = true;
            this.Operand1 = 0f;
            this.Operand2 = 0f;
            this.Operator = "";
            this.last = true;
            this.opState = false;
        }
        else if (operator.equals("π")) {
            this.Answer = (float)Math.PI;
            screen.setText(this.Answer + "");
            this.clearScreen = true;
            this.Operand1 = 0f;
            this.Operand2 = 0f;
            this.Operator = "";
            this.last = true;
            this.opState = false;
        }
        else if (operator.equals("e")){
            this.Answer = (float) Math.E;
            screen.setText(this.Answer + "");
            this.clearScreen = true;
            this.Operand1 = 0f;
            this.Operand2 = 0f;
            this.Operator = "";
            this.last = true;
            this.opState = false;
        }
        else if (operator.equals("^")) this.Operator = "^";
        else if (operator.equals("%")) this.Operator = "%";
        else if (operator.equals("!")) {
            double t = 1;
            if( Double.parseDouble(value)>0) {


                for (double i = 1; i <= Double.parseDouble(value); i++) {
                    t *= i;
                }
            }
            else
            {
            t=1;
            }
            this.Answer=(float)t;
            screen.setText(this.Answer + "");
            this.clearScreen = true;
            this.Operand1 = 0f;
            this.Operand2 = 0f;
            this.Operator = "";
            this.last = true;
            this.opState = false;
        }
        else if (operator.equals(")")) {
            screen.setText("ERROR)");
            this.clearScreen = true;
            this.Operand1 = 0f;
            this.Operand2 = 0f;
            this.Operator = "";
            this.last = true;
            this.opState = false;
        }
        else if (operator.equals("(")){
            screen.setText("(ERROR");
            this.clearScreen = true;
            this.Operand1 = 0f;
            this.Operand2 = 0f;
            this.Operator = "";
            this.last = true;
            this.opState = false;
        }
        else if (operator.equals("√")) {
            this.Answer = (float) Math.sqrt(Double.parseDouble(value));
            screen.setText(this.Answer + "");
            this.clearScreen = true;
            this.Operand1 = 0f;
            this.Operand2 = 0f;
            this.Operator = "";
            this.last = true;
            this.opState = false;
        }
    }

    public void specialCalc() {
        EditText screen = (EditText) findViewById(R.id.screen);
        screen.setText((int) Operand1);

        if (screen.getText().toString().equals(".")) {
            screen.setText("0");
        }

        if (this.Operator.equals("^")) {
            pow=1;
        } else if (this.Operator.equals("%")) {
            mod=1;
        } else {
            this.Answer = Float.parseFloat(screen.getText().toString());
        }

        screen.setText(this.Answer + "");
    }

    public void ButtonClickHandler(View v) {
        EditText screen = (EditText) findViewById(R.id.screen);

        switch (v.getId()) {

            case R.id.buttonSqr:	set_operator("√"); break;
            case R.id.buttonPow:	set_operator("^"); break;
            case R.id.buttonMod:	set_operator("%"); break;
            case R.id.buttonSin:	set_operator("sin"); break;
            case R.id.buttonCos:	set_operator("cos"); break;
            case R.id.buttonTan:	set_operator("tan"); break;
            case R.id.buttonI:	    set_operator("i"); break;
            case R.id.buttonLn:	    set_operator("ln"); break;
            case R.id.buttonLog:	set_operator("log"); break;
            case R.id.buttonPi:	    set_operator("π"); break;
            case R.id.buttonE:	    set_operator("e"); break;
            case R.id.buttonFact:	set_operator("!"); break;
            case R.id.buttonLeftP:	set_operator("("); break;
            case R.id.buttonRightP:	set_operator(")"); break;


            case R.id.buttonDel:
                if (screen.getText().toString().length() > 1) {
                    String screen_new = screen.getText().toString().substring(0, screen.getText().toString().length() - 1);
                    screen.setText(screen_new);
                    this.clearScreen = false;
                } else {
                    screen.setText("0");
                    this.clearScreen = true;
                }
                break;
            case R.id.buttonClear:
                this.Operand1 = 0f;
                this.Operand2 = 0f;
                this.Answer = 0f;
                this.Operator = "";
                this.opState = false;
                this.inState = false;
                this.last = false;
                this.clearScreen = true;
                screen.setText("0");
                break;
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        EditText screen = (EditText) findViewById(R.id.screen);
        newValue=screen.getText().toString();

        switch (item.getItemId()) {
            case R.id.advanced:
                Intent intent = new Intent(calcAdv.this,MainActivity.class);
                intent.putExtra("pow",pow);
                intent.putExtra("values",newValue);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                this.startActivity(intent);
                return true;
            case R.id.action_settings:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
