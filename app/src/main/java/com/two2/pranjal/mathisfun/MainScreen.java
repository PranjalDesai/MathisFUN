package com.two2.pranjal.mathisfun;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.plus.Plus;
import com.google.example.games.basegameutils.BaseGameActivity;


public class MainScreen extends BaseGameActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        Button play= (Button)findViewById(R.id.play);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent start = new Intent(MainScreen.this, ChooseGame.class);
                start.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                start.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(start);
            }
        });
        Button index= (Button)findViewById(R.id.index);
        index.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent start2 = new Intent(MainScreen.this, ChooseIndex.class);
                start2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                start2.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(start2);
            }
        });
        findViewById(R.id.sign_in_button).setOnClickListener(
                new View.OnClickListener() {
                    @Override public void onClick(View v) {
                        // start the asynchronous sign in flow
                        beginUserInitiatedSignIn();
                    }
                });
        findViewById(R.id.sign_out_button).setOnClickListener(
                new View.OnClickListener() {
                    @Override public void onClick(View v) {
                        // User initiated sign out...
                        signOut();
                        setViewVisibility();
                    }
                });

    }

    public void onClick(View v) {
        Plus.AccountApi.clearDefaultAccount(getApiClient());
        getApiClient().disconnect();
    }

    public void setViewVisibility() {
        if (isSignedIn()) {
            findViewById(R.id.sign_out_button).setVisibility(View.VISIBLE);
            findViewById(R.id.sign_in_button).setVisibility(View.GONE);
        } else {
            findViewById(R.id.sign_out_button).setVisibility(View.GONE);
            findViewById(R.id.sign_in_button).setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onSignInFailed() {
        setViewVisibility();
    }

    @Override
    public void onSignInSucceeded() {
        setViewVisibility();
    }
    public MainScreen() {
        super(CLIENT_GAMES | CLIENT_PLUS);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_screen, menu);
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
