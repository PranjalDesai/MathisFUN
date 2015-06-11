package com.two2.pranjal.mathisfun;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.games.Games;
import com.google.example.games.basegameutils.BaseGameActivity;

/**
 * Created by pranjal on 5/21/15.
 */
public class addResult extends BaseGameActivity {
    public static long tots;
    int k=1;
    //    public static double inputAns;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addresult);
        tots=getIntent().getExtras().getInt("correctadd");
        TextView out= (TextView)findViewById(R.id.textView2);
        out.setText(Double.toString(tots));

    }

    public void share(View view){
        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, "I got "+ tots+ " correct in MATH is FUN!! Now its your turn to try!!\n" +
                "https://play.google.com/store/apps/details?id=com.two2.pranjal.mathisfun");
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Math is FUN");
        startActivity(Intent.createChooser(sharingIntent, "Share using"));
    }

    @Override
    public void onSignInFailed()
    {
        k=0;
    }

    @Override
    public void onSignInSucceeded() {
            Games.Leaderboards.submitScore(getApiClient(),
                getResources().getString(R.string.leaderboard_total_correct),
                tots);
        if(tots>=3 && tots<5)
        {
            Games.Achievements.unlock(getApiClient(),
                    getResources().getString(R.string.achievement_3_correct) );
        }
        else if(tots>=5 && tots<10)
        {
            Games.Achievements.unlock(getApiClient(),
                    getResources().getString(R.string.achievement_5_correct) );
        }
        else if(tots>=10 && tots<15)
        {
            Games.Achievements.unlock(getApiClient(),
                    getResources().getString(R.string.achievement_10_correct) );
        }
        else if(tots>=15 && tots<20)
        {
            Games.Achievements.unlock(getApiClient(),
                    getResources().getString(R.string.achievement_15_correct) );
        }
        else if(tots>=20)
        {
            Games.Achievements.unlock(getApiClient(),
                    getResources().getString(R.string.achievement_20_correct) );
        }
    }

    public void onClick2(View view){
        if(k==0)
        {
            Toast.makeText(addResult.this, "User Not Signed-IN", Toast.LENGTH_LONG).show();
        }
        else {
            startActivityForResult(Games.Achievements.getAchievementsIntent(getApiClient()),
                    2);
        }
    }

    public void onClick(View view) {
        if(k==0)
        {
            Toast.makeText(addResult.this, "User Not Signed-IN", Toast.LENGTH_LONG).show();
        }
        else {
            startActivityForResult(Games.Leaderboards.getLeaderboardIntent(
                    getApiClient(), getString(R.string.leaderboard_total_correct)), 2);
        }

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
