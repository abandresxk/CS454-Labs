package com.example.leviandres.mycalc;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;

public class MainActivity extends ActionBarActivity {

    int count =0;
    boolean clearScreen = true;
    boolean last = false;
    float Operand1 = 0f;
    float Operand2 = 0f;
    float Answer = 0f;
    String Operator = "";
    String value;
    boolean opState = false;
    boolean inState = false;
    boolean power=false;
    int pow=0;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText screen = (EditText) findViewById(R.id.screen);

        Intent intent = getIntent();
        count=intent.getIntExtra("count",1);
        pow=intent.getIntExtra("pow", 0);
        value=intent.getStringExtra("values");

        if(pow==1)
        {
            power=true;

        }
        else
        {

            power=false;
        }

        screen.setText(value);
    }

    public void insert_text(String text) {
        EditText screen = (EditText) findViewById(R.id.screen);
        if (this.clearScreen) {
            screen.setText("");
            this.clearScreen = false;
        }
        this.inState = true;
        this.last = true;
        screen.append(text);


    }

    public void set_operator(String operator) {
        EditText screen = (EditText) findViewById(R.id.screen);
        if (screen.getText().toString().equals(".")) screen.setText("0");
        if (this.inState && this.opState && this.last) {
            calculator();
        }
        if (screen.getText().toString().length() > 0) {
            this.Operand1 = Float.parseFloat(screen.getText().toString());
        }
        this.opState = true;
        this.clearScreen = true;
        this.last = false;
        if (operator.equals("+")) this.Operator = "+";
        else if (operator.equals("-")) this.Operator = "-";
        else if (operator.equals("*")) this.Operator = "*";
        else if (operator.equals("/")) this.Operator = "/";
        else if (operator.equals("√")) {
            this.Answer = (float) Math.sqrt(Float.parseFloat(screen.getText().toString()));
            screen.setText(this.Answer + "");
            this.clearScreen = true;
            this.Operand1 = 0f;
            this.Operand2 = 0f;
            this.Operator = "";
            this.last = true;
            this.opState = false;
        }
    }

    public void calculator() {
        EditText screen = (EditText) findViewById(R.id.screen);
        if (screen.getText().toString().equals(".")) {
            screen.setText("0");
        }
        if (screen.getText().toString().length() > 0) {
            this.Operand2 = Float.parseFloat(screen.getText().toString());
        }
        if (this.Operator.equals("+")) {
            this.Answer = this.Operand1 + this.Operand2;
        } else if (this.Operator.equals("-")) {
            this.Answer = this.Operand1 - this.Operand2;
        } else if (this.Operator.equals("*")) {
            this.Answer = this.Operand1 * this.Operand2;
        } else if (this.Operator.equals("/")) {
            this.Answer = this.Operand1 / this.Operand2;
        }
     // was not able to implement these 2 features
//         else if (power) {
//            this.Answer = (float) Math.pow(this.Operand1, this.Operand2);
//        } else if (this.Operator.equals("%")) {
//            this.Answer = Operand1 % this.Operand2;
//        }
        else {
            this.Answer = Float.parseFloat(screen.getText().toString());
        }

        screen.setText(this.Answer + "");
    }

    public void ButtonClickHandler(View v) {
        EditText screen = (EditText) findViewById(R.id.screen);
        switch (v.getId()) {
            case R.id.button0:
                insert_text("0");
                break;
            case R.id.button1:
                insert_text("1");
                break;
            case R.id.button2:
                insert_text("2");
                break;
            case R.id.button3:
                insert_text("3");
                break;
            case R.id.button4:
                insert_text("4");
                break;
            case R.id.button5:
                insert_text("5");
                break;
            case R.id.button6:
                insert_text("6");
                break;
            case R.id.button7:
                insert_text("7");
                break;
            case R.id.button8:
                insert_text("8");
                break;
            case R.id.button9:
                insert_text("9");
                break;
            case R.id.buttonPoint:
                if (!screen.getText().toString().contains(".") || this.opState) {
                    insert_text(".");
                }
                break;
            case R.id.buttonAdd:
                set_operator("+");
                break;
            case R.id.buttonSub:
                set_operator("-");
                break;
            case R.id.buttonMulti:
                set_operator("*");
                break;
            case R.id.buttonDiv:
                set_operator("/");
                break;

            case R.id.buttonExe:
                if (screen.getText().toString().length() > 0 && this.Operator != "") {
                    calculator();
                    this.clearScreen = true;
                    this.Operand1 = 0f;
                    this.Operand2 = 0f;
                    this.Operator = "";
                    this.opState = false;
                }
                break;
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
        value=screen.getText().toString();
        switch (item.getItemId()) {


                case R.id.advanced:
                    Intent intent = new Intent(MainActivity.this,calcAdv.class);
                    intent.putExtra("count",count);
                    intent.putExtra("gets",value);
                    intent.putExtra("clear",clearScreen);
                    intent.putExtra("opState",opState);
                    intent.putExtra("inState",inState);
                    intent.putExtra("ltClick",last);
                    intent.putExtra("result", Answer);
                    intent.putExtra("Operand1", Operand1);
                    intent.putExtra("Operand2", Operand2);
                    intent.putExtra("operator",Operator);
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