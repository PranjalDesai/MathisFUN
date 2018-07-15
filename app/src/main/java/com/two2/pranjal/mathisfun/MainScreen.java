package com.two2.pranjal.mathisfun;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;


import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;


import butterknife.BindView;
import butterknife.ButterKnife;


public class MainScreen extends AppCompatActivity {
    private GoogleSignInClient mGoogleSignInClient = null;
    private GoogleSignInAccount mGoogleSignInAccount= null;
    private static final int RC_SIGN_IN = 9001;

    @BindView(R.id.sign_in_button)
    SignInButton signInButton;
    @BindView(R.id.sign_out_button)
    Button signOutButton;
    @BindView(R.id.play)
    CardView playCard;
    @BindView(R.id.index)
    CardView indexCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        ButterKnife.bind(this);

        playCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MainScreen.this, ChooseGame.class);
                intent.putExtra(getResources().getString(R.string.game_type), getResources().getString(R.string.play_game));
                startActivity(intent);
            }
        });

        indexCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MainScreen.this, ChooseGame.class);
                intent.putExtra(getResources().getString(R.string.game_type), getResources().getString(R.string.index_card));
                startActivity(intent);
            }
        });

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startSignInIntent();
            }
        });

        signOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startSignOutIntent();
            }
        });
    }

    private void startSignOutIntent(){
        mGoogleSignInClient.signOut();
        signOutButton.setVisibility(View.GONE);
        signInButton.setVisibility(View.VISIBLE);
        Snackbar.make(findViewById(R.id.mainScreen),getResources().getString(R.string.sign_out_success),Snackbar.LENGTH_LONG).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        signInSilently();
    }

    private void signInSilently() {
        mGoogleSignInClient = GoogleSignIn.getClient(this,
                GoogleSignInOptions.DEFAULT_GAMES_SIGN_IN);
        mGoogleSignInClient.silentSignIn().addOnCompleteListener(this,
                new OnCompleteListener<GoogleSignInAccount>() {
                    @Override
                    public void onComplete(@NonNull Task<GoogleSignInAccount> task) {
                        if (task.isSuccessful()) {
                            // The signed in account is stored in the task's result.
                            mGoogleSignInAccount = task.getResult();
                            signOutButton.setVisibility(View.VISIBLE);
                            signInButton.setVisibility(View.GONE);
                            Snackbar.make(findViewById(R.id.mainScreen),getResources().getString(R.string.sign_in_success),Snackbar.LENGTH_LONG).show();
                        } else {
                            // Player will need to sign-in explicitly using via UI
                            signOutButton.setVisibility(View.GONE);
                            signInButton.setVisibility(View.VISIBLE);
                        }
                    }
                });
    }

    private void startSignInIntent() {
        GoogleSignInClient signInClient = GoogleSignIn.getClient(this,
                GoogleSignInOptions.DEFAULT_GAMES_SIGN_IN);
        Intent intent = signInClient.getSignInIntent();
        startActivityForResult(intent, RC_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (result.isSuccess()) {
                mGoogleSignInAccount = result.getSignInAccount();
                signOutButton.setVisibility(View.VISIBLE);
                signInButton.setVisibility(View.GONE);
                Snackbar.make(findViewById(R.id.mainScreen),getResources().getString(R.string.sign_in_success),Snackbar.LENGTH_LONG).show();
            } else {
                String message = result.getStatus().getStatusMessage();
                if (message == null || message.isEmpty()) {
                    message = getResources().getString(R.string.something_went_wrong);
                }
                Snackbar.make(findViewById(R.id.mainScreen),message,Snackbar.LENGTH_LONG).show();
                signOutButton.setVisibility(View.VISIBLE);
            }
        }
    }
}
