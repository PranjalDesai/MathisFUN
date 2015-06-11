package com.two2.pranjal.mathisfun;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Random;

/**
 * Created by pranjal on 5/28/15.
 */
public class mystery extends FragmentActivity {
    TextView text1, text3;

    private static final String FORMAT = "%02d:%02d:%02d";

    int seconds , minutes;
    int q,w;
    int x=0;
    boolean k=true;
    int b=100;
    long time;
    int ans;
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mystery);
        text1=(TextView)findViewById(R.id.textView1);
        mysDisplay();
        ImageButton pause= (ImageButton)findViewById(R.id.pause);
        pause.setVisibility(View.VISIBLE);
        ImageButton play= (ImageButton)findViewById(R.id.play);
        play.setVisibility((View.INVISIBLE));
    }
    CountDownTimer timer= new CountDownTimer(60000, 1000) {

        public void onTick(long millisUntilFinished) {
            time= millisUntilFinished;
            text1.setText("Time Remaining: " + millisUntilFinished / 1000);
        }

        public void onFinish() {
            text1.setText("TIME UP");
            k=false;
            Intent start = new Intent(mystery.this, mysResult.class);
            start.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            start.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            start.putExtra("correctmys", x);
            startActivity(start);
        }

    }.start();


    @Override
    public void onBackPressed(){
        timer.cancel();
        super.onBackPressed();
    }

    public void pause(View view){
        timer.cancel();
        ImageButton pause= (ImageButton)findViewById(R.id.pause);
        pause.setVisibility(View.INVISIBLE);
        ImageButton play= (ImageButton)findViewById(R.id.play);
        play.setVisibility((View.VISIBLE));
        TextView number1 = (TextView) findViewById(R.id.num1);
        TextView number2 = (TextView) findViewById(R.id.num2);
        TextView add = (TextView) findViewById(R.id.plus);
        number1.setText("");
        number2.setText("");
        add.setText("");
        EditText input= (EditText)findViewById(R.id.input);
        input.setEnabled(false);
        text1.setText("Game Paused");
        TextView outcome= (TextView) findViewById(R.id.outcome);
        outcome.setText("Game Paused");
    }

    public void play(View view){
        ImageButton pause= (ImageButton)findViewById(R.id.pause);
        pause.setVisibility(View.VISIBLE);
        ImageButton play= (ImageButton)findViewById(R.id.play);
        play.setVisibility((View.INVISIBLE));
        TextView outcome = (TextView) findViewById(R.id.outcome);
        outcome.setText("");
        EditText input= (EditText)findViewById(R.id.input);
        input.setEnabled(true);
        timer= new CountDownTimer(time, 1000) {

            public void onTick(long millisUntilFinished) {
                time= millisUntilFinished;
                text1.setTextColor(Color.parseColor("#FFFFFF"));
                text1.setText("Time Remaining: " + millisUntilFinished / 1000);
            }

            public void onFinish() {
                text1.setText("TIME UP");
                k=false;
                Intent start = new Intent(mystery.this, mysResult.class);
                start.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                start.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                start.putExtra("correctmys", x);
                startActivity(start);
            }

        }.start();
        mysDisplay();
    }

    public void mysDisplay(){
        int n1,n2,n3,n4;
        Random r = new Random();
        n1 = r.nextInt(4)+1;
        if(n1==1)
        {
            TextView number1 = (TextView) findViewById(R.id.num1);
            TextView number2 = (TextView) findViewById(R.id.num2);
            n2=r.nextInt(b);
            n3=r.nextInt(b);
            number1.setText(Integer.toString(n2));
            number2.setText(Integer.toString(n3));
            TextView add = (TextView) findViewById(R.id.plus);
            add.setText("+");
            ans=n2+n3;

        }
        else if(n1==2)
        {
            TextView number1 = (TextView) findViewById(R.id.num1);
            TextView number2 = (TextView) findViewById(R.id.num2);
            n2 = r.nextInt(b);
            n3 = r.nextInt(b);
            while(n3>n2)
            {
                n2 = r.nextInt(b);
            }
            number1.setText(Integer.toString(n2));
            number2.setText(Integer.toString(n3));
            TextView sub = (TextView) findViewById(R.id.plus);
            sub.setText("-");
            ans=n2-n3;
        }
        else if(n1==3)
        {
            TextView number1 = (TextView) findViewById(R.id.num1);
            TextView number2 = (TextView) findViewById(R.id.num2);
            n2=r.nextInt(15);
            n3=r.nextInt(15);
            number1.setText(Integer.toString(n2));
            number2.setText(Integer.toString(n3));
            TextView mul = (TextView) findViewById(R.id.plus);
            mul.setText("*");
            ans=n2*n3;
        }
        else if(n1==4)
        {
            TextView number1 = (TextView) findViewById(R.id.num1);
            TextView number2 = (TextView) findViewById(R.id.num2);
            n2 = r.nextInt(15);
            n3 = r.nextInt(15);
            if(n2==0 || n3==0)
            {
                n2++;
                n3++;
            }
            n4 = n2 * n3;
            number1.setText(Integer.toString(n4));
            number2.setText(Integer.toString(n2));
            TextView div = (TextView) findViewById(R.id.plus);
            div.setText("/");
            ans=n4/n2;
        }
    }

    public void mist(View view) {
        EditText input= (EditText)findViewById(R.id.input);
        long inputAns;
        String check=input.getText().toString();
        if (check.matches("")) {
            inputAns=0;
        }
        else
        {
            inputAns = Integer.parseInt(check);
        }
        if(ans==inputAns)
        {
            TextView outcome= (TextView) findViewById(R.id.outcome);
            outcome.setText("Correct");
            x++;
            input.setText("");
            if(k)
            {

                mysDisplay();
            }
        }
        else
        {
            input.setText("");
            TextView outcome= (TextView) findViewById(R.id.outcome);
            outcome.setText("Incorrect");
            if(k)
            {
                mysDisplay();
            }
        }
    }
}
