package com.two2.pranjal.mathisfun;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.wajahatkarim3.easyflipview.EasyFlipView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class IndexActivity extends AppCompatActivity{

    @BindView(R.id.flipView)
    EasyFlipView flipView;
    @BindView(R.id.frontQuestion)
    TextView frontQuestion;
    @BindView(R.id.backAnswer)
    TextView backAnswer;
    @BindView(R.id.skipIndexCard)
    FloatingActionButton skipButton;
    @BindView(R.id.flipIndexCard)
    FloatingActionButton flipButton;
    ProblemFinder problemFinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        ButterKnife.bind(this);
        Intent intent= getIntent();
        String type= intent.getStringExtra(getResources().getString(R.string.type));
        String difficulty= intent.getStringExtra(getResources().getString(R.string.difficulty));
        problemFinder= new ProblemFinder(type,difficulty,this);
        ProblemObject problemObject= problemFinder.getProblem();
        String question= problemObject.getQuestion1()+" "+problemObject.getType()+" "+problemObject.getQuestion2();
        frontQuestion.setText(question);
        backAnswer.setText(problemObject.getAnswer());

        skipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextCard();
            }
        });

        flipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flipView.flipTheView();
            }
        });
    }

    private void nextCard(){
        ProblemObject problemObject= problemFinder.getProblem();
        String question= problemObject.getQuestion1()+" "+problemObject.getType()+" "+problemObject.getQuestion2();
        frontQuestion.setText(question);
        if(flipView.isBackSide()){
            flipView.flipTheView();
        }
        backAnswer.setText(problemObject.getAnswer());
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent= new Intent(this, MainScreen.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
