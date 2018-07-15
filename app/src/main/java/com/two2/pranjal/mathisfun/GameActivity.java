package com.two2.pranjal.mathisfun;

import android.content.Context;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.design.button.MaterialButton;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.github.krtkush.lineartimer.LinearTimer;
import io.github.krtkush.lineartimer.LinearTimerView;

import static io.github.krtkush.lineartimer.LinearTimerStates.INITIALIZED;
import static io.github.krtkush.lineartimer.LinearTimerStates.PAUSED;

public class GameActivity extends AppCompatActivity implements LinearTimer.TimerListener {

    ProblemFinder problemFinder;
    @BindView(R.id.submitBtn)
    MaterialButton submitBtn;
    @BindView(R.id.answerInputLayout)
    TextInputLayout userAnswerLayout;
    @BindView(R.id.answerInputEditText)
    TextInputEditText userAnswerEditText;
    @BindView(R.id.linearTimerView)
    LinearTimerView linearTimerView;
    @BindView(R.id.countDownTimerView)
    TextView countDownTimerView;
    @BindView(R.id.time_left_play)
    TextView timeLeftPlay;
    @BindView(R.id.description_play)
    TextView descriptionPlay;
    @BindView(R.id.questionText)
    TextView questionTextView;
    @BindView(R.id.constraintLayoutPlay)
    ConstraintLayout playLayout;
    @BindView(R.id.constraintLayoutGame)
    ConstraintLayout gameLayout;
    @BindView(R.id.play_button)
    CardView playBtn;
    @BindView(R.id.pauseBtn)
    FloatingActionButton pauseBtn;
    String currentAnswer, currentQuestion, userAnswer;
    ArrayList<UserResponse> userResponseArrayList= new ArrayList<>();
    LinearTimer linearTimer;
    int correctAnswer=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        ButterKnife.bind(this);
        Intent intent= getIntent();
        String type= intent.getStringExtra(getResources().getString(R.string.type));
        String difficulty= intent.getStringExtra(getResources().getString(R.string.difficulty));

        problemFinder= new ProblemFinder(type,difficulty, this);

        linearTimer = new LinearTimer.Builder()
                .linearTimerView(linearTimerView)
                .duration(60000)
                .timerListener(this)
                .getCountUpdate(LinearTimer.COUNT_DOWN_TIMER, 1000)
                .build();

        playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playGame();
            }
        });

        userAnswerLayout.setFocusableInTouchMode(false);
        userAnswerLayout.setFocusable(false);
        userAnswerLayout.setFocusableInTouchMode(true);
        userAnswerLayout.setFocusable(true);

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (userAnswerEditText.getText() == null) {
                    userAnswer="";
                }else{
                    userAnswer= userAnswerEditText.getText().toString();
                }
                if(!userAnswer.equals(currentAnswer)){
                    userAnswerLayout.setErrorEnabled(true);
                    userAnswerLayout.setError(getResources().getString(R.string.answer_error));
                }else{
                    userAnswerLayout.setErrorEnabled(false);
                    correctAnswer++;
                }
                userResponseArrayList.add(new UserResponse(currentQuestion,currentAnswer, userAnswer));
                nextQuestion();
                userAnswerEditText.setText("");
                userAnswer="";

            }
        });

        pauseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pauseGame();
            }
        });
    }

    private void pauseGame(){
        linearTimer.pauseTimer();
        hideKeyboard();
        gameLayout.setVisibility(View.GONE);
        playLayout.setVisibility(View.VISIBLE);
        timeLeftPlay.setText(countDownTimerView.getText()+ " seconds left");
        descriptionPlay.setText(getResources().getString(R.string.continue_game));
    }

    public void hideKeyboard() {
        // Check if no view has focus:
        View view = getCurrentFocus();
        if (view != null) {
            InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    private void playGame(){
        userAnswerLayout.setFocusableInTouchMode(false);
        userAnswerLayout.setFocusable(false);
        userAnswerLayout.setFocusableInTouchMode(true);
        userAnswerLayout.setFocusable(true);
        if(linearTimer.getState()==INITIALIZED){
            gameLayout.setVisibility(View.VISIBLE);
            playLayout.setVisibility(View.GONE);
            nextQuestion();
            linearTimer.startTimer();
        }else if (linearTimer.getState()==PAUSED){
            gameLayout.setVisibility(View.VISIBLE);
            playLayout.setVisibility(View.GONE);
            linearTimer.resumeTimer();
        }
    }

    private void nextQuestion(){
        ProblemObject object= problemFinder.getProblem();
        currentAnswer= object.getAnswer();
        currentQuestion= object.getQuestion1() + " "+ object.getType() + " "+ object.getQuestion2();
        questionTextView.setText(currentQuestion);

    }

    private void routeScreen(){
        Intent intent1= new Intent(this, ResultActivity.class);
        intent1.putParcelableArrayListExtra(getResources().getString(R.string.results), userResponseArrayList);
        intent1.putExtra(getResources().getString(R.string.correctAnswers), correctAnswer);
        startActivity(intent1);
    }

    @Override
    public void animationComplete() {
        userAnswerLayout.setFocusableInTouchMode(false);
        userAnswerLayout.setFocusable(false);
        routeScreen();
    }

    @Override
    public void timerTick(long tickUpdateInMillis) {
        countDownTimerView.setText(String.valueOf(tickUpdateInMillis/1000));
    }

    @Override
    public void onTimerReset() {

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent= new Intent(this, MainScreen.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
