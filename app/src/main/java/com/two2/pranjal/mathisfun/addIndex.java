package com.two2.pranjal.mathisfun;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewAnimator;

import java.util.Random;

/**
 * Created by pranjal on 5/27/15.
 */
public class addIndex extends FragmentActivity {
    TextView text1, text3;

    ViewAnimator imgFront,imgBack;
    ImageButton btnFlip;
    public static int b;
    boolean isBackVisible = false;
    int ans;
    int w,q;
    long time;
    View view;
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.addindex);
        b=getIntent().getIntExtra("addlevel", 0);
        addIndex(view);
        imgFront = (ViewAnimator)findViewById(R.id.imgFront);
        imgBack = (ViewAnimator)findViewById(R.id.imgBack);
        btnFlip = (ImageButton)findViewById(R.id.btnFlip);

        final AnimatorSet setRightOut = (AnimatorSet) AnimatorInflater.loadAnimator(getApplicationContext(),
                R.animator.card_flip_right_out);

        final AnimatorSet setLeftIn = (AnimatorSet) AnimatorInflater.loadAnimator(getApplicationContext(),
                R.animator.card_flip_left_in);


        btnFlip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isBackVisible){
                    setRightOut.setTarget(imgFront);
                    setLeftIn.setTarget(imgBack);
                    setRightOut.start();
                    setLeftIn.start();
                    isBackVisible = true;
                }
                else{
                    setRightOut.setTarget(imgBack);
                    setLeftIn.setTarget(imgFront);
                    setRightOut.start();
                    setLeftIn.start();
                    isBackVisible = false;
                }
            }
        });
    }

    public void addIndex(View view){
        final AnimatorSet setRightOut = (AnimatorSet) AnimatorInflater.loadAnimator(getApplicationContext(),
                R.animator.card_flip_right_out);

        final AnimatorSet setLeftIn = (AnimatorSet) AnimatorInflater.loadAnimator(getApplicationContext(),
                R.animator.card_flip_left_in);
        if(isBackVisible){
            setRightOut.setTarget(imgBack);
            setLeftIn.setTarget(imgFront);
            setRightOut.start();
            setLeftIn.start();
            isBackVisible = false;
        }
        TextView math = (TextView) findViewById(R.id.math);
        final TextView mathans = (TextView) findViewById(R.id.mathans);
        int n1, n2;
        Random r = new Random();
        n1 = r.nextInt(b);
        n2 = r.nextInt(b);
        math.setText(Integer.toString(n1)+" + "+ Integer.toString(n2));
        ans=n1+n2;
        Handler mHandler = new Handler();
        mHandler.postDelayed(new Runnable() {
            public void run() {
                mathans.setText((Integer.toString(ans)));
            }
        }, 1000);
    }
}
