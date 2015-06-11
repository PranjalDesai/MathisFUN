package com.two2.pranjal.mathisfun;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

/**
 * Created by pranjal on 5/20/15.
 */
public class ChooseGame extends FragmentActivity {
    int numb=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choosegame);
        ImageButton add= (ImageButton)findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent start = new Intent(ChooseGame.this, add.class);
                start.putExtra("iorn", numb);
                start.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                start.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(start);
            }
        });
        ImageButton sub= (ImageButton)findViewById(R.id.sub);
        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent start2 = new Intent(ChooseGame.this, sub.class);
                start2.putExtra("iorn", numb);
                start2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                start2.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(start2);
            }
        });
        ImageButton mul= (ImageButton)findViewById(R.id.mul);
        mul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent start3 = new Intent(ChooseGame.this, mul.class);
                start3.putExtra("iorn", numb);
                start3.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                start3.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(start3);
            }
        });
        ImageButton div= (ImageButton)findViewById(R.id.div);
        div.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent start4 = new Intent(ChooseGame.this, div.class);
                start4.putExtra("iorn", numb);
                start4.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                start4.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(start4);
            }
        });
        ImageButton mys= (ImageButton)findViewById(R.id.question);
        mys.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent start5 = new Intent(ChooseGame.this, mystery.class);
                start5.putExtra("iorn", numb);
                start5.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                start5.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(start5);
            }
        });
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
