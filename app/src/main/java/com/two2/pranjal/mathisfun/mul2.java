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
 * Created by pranjal on 5/20/15.
 */
public class mul2 extends FragmentActivity {
    TextView text1, text3;

    private static final String FORMAT = "%02d:%02d:%02d";

    int seconds , minutes;
    int q,w;
    int x=0;
    boolean k=true;
    public static int b;
    long time;
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.mul2);
        b=getIntent().getIntExtra("mullevel",0);
        text1=(TextView)findViewById(R.id.textView1);
        mulDisplay();


    }

    CountDownTimer timer=new CountDownTimer(60000, 1000) {

        public void onTick(long millisUntilFinished) {
            time=millisUntilFinished;
            text1.setText("Time Remaining: " + millisUntilFinished / 1000);
        }

    public void onFinish() {
        text1.setText("TIME UP");
        k=false;
        EditText input= (EditText)findViewById(R.id.input);
        input.setEnabled(false);
        Intent start = new Intent(mul2.this, mulResult.class);
        start.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        start.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        start.putExtra("correctmul", x);
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
        TextView mul = (TextView) findViewById(R.id.plus);
        number1.setText("");
        number2.setText("");
        mul.setText("");
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
        TextView mul = (TextView) findViewById(R.id.plus);
        mul.setText("*");
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
                EditText input= (EditText)findViewById(R.id.input);
                input.setEnabled(false);
                Intent start = new Intent(mul2.this, mulResult.class);
                start.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                start.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                start.putExtra("correctmul", x);
                startActivity(start);
            }

        }.start();
        mulDisplay();

    }

    public void mulDisplay(){

        TextView number1 = (TextView) findViewById(R.id.num1);
        TextView number2 = (TextView) findViewById(R.id.num2);
        int n1, n2;
        Random r = new Random();
        n1 = r.nextInt(b);
        n2 = r.nextInt(b);
        number1.setText(Integer.toString(n1));
        number2.setText(Integer.toString(n2));
        q=n1;
        w=n2;
    }
    public void multiplication(View view) {
        EditText input= (EditText)findViewById(R.id.input);
        int ans=q*w;
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
                mulDisplay();
            }
        }
        else
        {
            input.setText("");
            TextView outcome= (TextView) findViewById(R.id.outcome);
            outcome.setText("Incorrect");
            if(k)
            {
                mulDisplay();
            }
        }
    }
}
