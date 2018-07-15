package com.two2.pranjal.mathisfun;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.chip.Chip;
import android.support.design.chip.ChipGroup;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.CompoundButton;

import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChooseGame extends AppCompatActivity {

    @BindView(R.id.next_choose_game)
    FloatingActionButton nextButton;
    @BindView(R.id.chooseGameTitle)
    TextView screenTitle;
    @BindView(R.id.gameTypeTitle)
    TextView gameTypeTitle;
    @BindView(R.id.mul_chip)
    Chip mulChip;
    @BindView(R.id.div_chip)
    Chip divChip;
    @BindView(R.id.mys_chip)
    Chip mysChip;
    @BindView(R.id.sub_chip)
    Chip subChip;
    @BindView(R.id.easy_chip)
    Chip easyChip;
    @BindView(R.id.med_chip)
    Chip mediumChip;
    @BindView(R.id.hard_chip)
    Chip hardChip;
    @BindView(R.id.difficultyTypeChipGroup)
    ChipGroup difficultyTypeChipGroup;
    @BindView(R.id.gameTypeChipGroup)
    ChipGroup gameTypeChipGroup;
    @BindView(R.id.add_chip)
    Chip addChip;

    int type=-1, difficulty=-1;
    boolean add, sub, div, mul, mys, easy, hard, medium;
    String gameType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choosegame);
        ButterKnife.bind(this);

        Intent intent= getIntent();
        gameType= intent.getStringExtra(getResources().getString(R.string.game_type));
        if(gameType.equals(getResources().getString(R.string.play_game))){
            screenTitle.setText(getResources().getString(R.string.choose_game));
        }else if(gameType.equals(getResources().getString(R.string.index_card))){
            screenTitle.setText(getResources().getString(R.string.choose_index));
        }

        gameTypeChipGroup.setOnCheckedChangeListener(new ChipGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(ChipGroup group, int checkedId) {
                type=group.getCheckedChipId();
            }
        });

        difficultyTypeChipGroup.setOnCheckedChangeListener(new ChipGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(ChipGroup group, int checkedId) {
                difficulty= group.getCheckedChipId();
            }
        });

        addChip.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                add = b;
            }
        });

        subChip.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                sub = b;
            }
        });

        mulChip.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                mul = b;
            }
        });

        divChip.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                div = b;
            }
        });

        mysChip.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                mys = b;
            }
        });

        easyChip.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                easy = b;
            }
        });

        hardChip.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                hard = b;
            }
        });

        mediumChip.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                medium = b;
            }
        });


        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateFields();
            }
        });
    }

    private void validateFields(){
        if(type==-1){
            Snackbar.make(findViewById(R.id.chooseGame),getResources().getString(R.string.type_error), Snackbar.LENGTH_LONG).show();
        }else if (difficulty==-1){
            Snackbar.make(findViewById(R.id.chooseGame),getResources().getString(R.string.difficulty_error), Snackbar.LENGTH_LONG).show();
        }else{
            routeScreen();
        }
    }

    private String gameTypePick(){
        String pick="";
        if(add) {
            pick=getResources().getString(R.string.add);
        }else if (sub){
            pick=getResources().getString(R.string.subtract);
        }else if (mys){
            pick=getResources().getString(R.string.mystery);
        }else if (mul){
            pick=getResources().getString(R.string.multiply);
        }else if (div){
            pick=getResources().getString(R.string.divide);
        }
        return pick;
    }

    private String difficultyTypePick(){
        String pick="";
        if(easy) {
            pick=getResources().getString(R.string.easy);
        }else if (medium){
            pick=getResources().getString(R.string.medium);
        }else if (hard){
            pick=getResources().getString(R.string.hard);
        }
        return pick;
    }

    private void routeScreen(){
        String gType= gameTypePick();
        String dType= difficultyTypePick();

        if(gameType.equals(getResources().getString(R.string.play_game))){
            Intent intent= new Intent(this, GameActivity.class);
            intent.putExtra(getResources().getString(R.string.type), gType);
            intent.putExtra(getResources().getString(R.string.difficulty), dType);
            startActivity(intent);
        }else if(gameType.equals(getResources().getString(R.string.index_card))){
            Intent intent= new Intent(this, IndexActivity.class);
            intent.putExtra(getResources().getString(R.string.type), gType);
            intent.putExtra(getResources().getString(R.string.difficulty), dType);
            startActivity(intent);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(this, MainScreen.class));
    }
}
