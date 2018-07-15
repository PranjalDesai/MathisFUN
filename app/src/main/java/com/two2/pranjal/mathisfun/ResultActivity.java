package com.two2.pranjal.mathisfun;

import android.content.Intent;
import android.support.design.button.MaterialButton;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.games.Games;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ResultActivity extends AppCompatActivity {

    @BindView(R.id.responseView)
    RecyclerView mRecyclerView;
    @BindView(R.id.scoreText)
    TextView scoreTextView;
    @BindView(R.id.achievementButton)
    MaterialButton achievementButton;
    @BindView(R.id.leaderboardButton)
    MaterialButton leaderboardButton;
    @BindView(R.id.shareGame)
    FloatingActionButton shareButton;
    @BindView(R.id.linearGoogleGames)
    LinearLayout linearLayout;
    String total;
    private int totalInt;

    private static final int RC_ACHIEVEMENT_UI = 9003;
    private static final int RC_LEADERBOARD_UI = 9004;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        ButterKnife.bind(this);
        Intent intent= getIntent();
        ArrayList<UserResponse> userResponseArrayList;
        userResponseArrayList= intent.getParcelableArrayListExtra(getResources().getString(R.string.results));
        totalInt=intent.getIntExtra((getResources().getString(R.string.correctAnswers)),0);
        total= String.valueOf(totalInt);
        scoreTextView.setText(total);
        UserResponseAdaptor userResponseAdaptor = new UserResponseAdaptor(userResponseArrayList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(userResponseAdaptor);

        boolean signIn = isSignedIn();
        if(!signIn){
            linearLayout.setVisibility(View.GONE);
        }else{
            submitScore();
            unlockAchievements();
        }

        achievementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAchievements();
            }
        });

        leaderboardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showLeaderboard();
            }
        });

        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                share();
            }
        });

    }

    private void unlockAchievements(){
        if(totalInt>=3 && totalInt<5)
        {
            Games.getAchievementsClient(this, GoogleSignIn.getLastSignedInAccount(this))
                .unlock(getString(R.string.achievement_3_correct));
        }
        else if(totalInt>=5 && totalInt<10)
        {
            Games.getAchievementsClient(this, GoogleSignIn.getLastSignedInAccount(this))
                    .unlock(getString(R.string.achievement_5_correct));
        }
        else if(totalInt>=10 && totalInt<15)
        {
            Games.getAchievementsClient(this, GoogleSignIn.getLastSignedInAccount(this))
                    .unlock(getString(R.string.achievement_10_correct));
        }
        else if(totalInt>=15 && totalInt<20)
        {
            Games.getAchievementsClient(this, GoogleSignIn.getLastSignedInAccount(this))
                    .unlock(getString(R.string.achievement_15_correct));
        }
        else if(totalInt>=20)
        {
            Games.getAchievementsClient(this, GoogleSignIn.getLastSignedInAccount(this))
                    .unlock(getString(R.string.achievement_20_correct));
        }
    }

    private void submitScore(){
        Games.getLeaderboardsClient(this, GoogleSignIn.getLastSignedInAccount(this))
                .submitScore(getString(R.string.leaderboard_total_correct), totalInt);
    }

    private void showLeaderboard() {
        Games.getLeaderboardsClient(this, GoogleSignIn.getLastSignedInAccount(this))
                .getLeaderboardIntent(getString(R.string.leaderboard_total_correct))
                .addOnSuccessListener(new OnSuccessListener<Intent>() {
                    @Override
                    public void onSuccess(Intent intent) {
                        startActivityForResult(intent, RC_LEADERBOARD_UI);
                    }
                });
    }


    private void showAchievements() {
        Games.getAchievementsClient(this, GoogleSignIn.getLastSignedInAccount(this))
                .getAchievementsIntent()
                .addOnSuccessListener(new OnSuccessListener<Intent>() {
                    @Override
                    public void onSuccess(Intent intent) {
                        startActivityForResult(intent, RC_ACHIEVEMENT_UI);
                    }
                });
    }

    private void share(){
        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, "I got "+ total+ " correct in MATH is FUN!! Now it's your turn to try!!\n" +
                "https://play.google.com/store/apps/details?id=com.two2.pranjal.mathisfun");
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, getResources().getString(R.string.app_name));
        startActivity(Intent.createChooser(sharingIntent, getResources().getString(R.string.share)));
    }

    private boolean isSignedIn() {
        return GoogleSignIn.getLastSignedInAccount(this) != null;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent= new Intent(this, MainScreen.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
