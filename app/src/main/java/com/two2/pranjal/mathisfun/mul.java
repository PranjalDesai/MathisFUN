package com.two2.pranjal.mathisfun;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageButton;

/**
 * Created by pranjal on 5/20/15.
 */
public class mul extends FragmentActivity {

    int numb=100;
    int checker;
    private CheckBox checkBoxA, checkBoxB, checkBoxC;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mul);
        checker=getIntent().getExtras().getInt("iorn");
        checkBoxA = (CheckBox) findViewById(R.id.cb1);
        checkBoxB = (CheckBox) findViewById(R.id.cb2);
        checkBoxC = (CheckBox) findViewById(R.id.cb3);

        if(checker==1)
        {
            ImageButton play= (ImageButton)findViewById(R.id.imageButton);
            play.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent start = new Intent(mul.this, mulIndex.class);
                    start.putExtra("mullevel", numb);
                    start.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    start.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(start);
                }
            });
        }
        else
        {
            ImageButton play= (ImageButton)findViewById(R.id.imageButton);
            play.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent start = new Intent(mul.this, mul2.class);
                    start.putExtra("mullevel", numb);
                    start.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    start.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(start);
                }
            });
        }
    }

    public void onCheckboxClicked2(View view) {

        switch(view.getId()) {

            case R.id.cb1:

                checkBoxB.setChecked(false);
                checkBoxC.setChecked(false);
                numb=10;
                break;

            case R.id.cb2:

                checkBoxC.setChecked(false);
                checkBoxA.setChecked(false);
                numb=20;
                break;

            case R.id.cb3:

                checkBoxA.setChecked(false);
                checkBoxB.setChecked(false);
                numb=30;
                break;
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
