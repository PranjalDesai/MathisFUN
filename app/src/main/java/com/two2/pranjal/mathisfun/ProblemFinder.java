package com.two2.pranjal.mathisfun;

import android.content.Context;

import java.util.Random;

public class ProblemFinder {

    String type, difficulty;
    Context mContext;

    public ProblemFinder(String type, String difficulty, Context context){
        this.type=type;
        this.difficulty=difficulty;
        this.mContext=context;
    }

    private ProblemObject getAddProblem(){
        int bound, q1, q2, ans;
        if(difficulty.equals(mContext.getResources().getString(R.string.easy))){
            bound= 50;
        }else if (difficulty.equals(mContext.getResources().getString(R.string.medium))){
            bound= 150;
        }else {
            bound= 300;
        }
        Random random= new Random();
        q1= 1+ random.nextInt(bound);
        q2= 1+ random.nextInt(bound);
        ans=q1+q2;
        return new ProblemObject(Integer.toString(q1),Integer.toString(q2),Integer.toString(ans), "+");
    }

    private ProblemObject getSubProblem(){
        int bound, q1, q2, ans;
        if(difficulty.equals(mContext.getResources().getString(R.string.easy))){
            bound= 50;
        }else if (difficulty.equals(mContext.getResources().getString(R.string.medium))){
            bound= 100;
        }else {
            bound= 200;
        }
        Random random= new Random();
        q1= 1+ random.nextInt(bound);
        q2= 1+ random.nextInt(bound);
        while(q2>q1)
        {
            q1 = 1+ random.nextInt(bound);
        }
        ans=q1-q2;
        return new ProblemObject(Integer.toString(q1),Integer.toString(q2),Integer.toString(ans), "-");
    }

    private ProblemObject getMulProblem(){
        int bound, q1, q2, ans;
        if(difficulty.equals(mContext.getResources().getString(R.string.easy))){
            bound= 10;
        }else if (difficulty.equals(mContext.getResources().getString(R.string.medium))){
            bound= 20;
        }else {
            bound= 30;
        }
        Random random= new Random();
        q1= 1+ random.nextInt(bound);
        q2= 1+ random.nextInt(bound);
        while(q2>q1)
        {
            q2 = 1+ random.nextInt(bound);
        }
        ans=q1*q2;
        return new ProblemObject(Integer.toString(q1),Integer.toString(q2),Integer.toString(ans), "*");
    }

    private ProblemObject getDivProblem(){
        int bound, q1, q2, ans;
        if(difficulty.equals(mContext.getResources().getString(R.string.easy))){
            bound= 10;
        }else if (difficulty.equals(mContext.getResources().getString(R.string.medium))){
            bound= 15;
        }else {
            bound= 20;
        }
        Random random= new Random();
        q1= 1+ random.nextInt(bound);
        q2= 1+ random.nextInt(bound);
        ans=q1;
        q1= q1*q2;
        return new ProblemObject(Integer.toString(q1),Integer.toString(q2),Integer.toString(ans), "/");
    }

    public ProblemObject getProblem(){
        if(type.equals(mContext.getResources().getString(R.string.add))){
            return getAddProblem();
        }else if(type.equals(mContext.getResources().getString(R.string.subtract))){
            return getSubProblem();
        }else if(type.equals(mContext.getResources().getString(R.string.multiply))){
            return getMulProblem();
        }else if(type.equals(mContext.getResources().getString(R.string.divide))){
            return getDivProblem();
        }else{
            Random r= new Random();
            int mysNumber= 1+ r.nextInt(4);
            ProblemObject mysProblem;
            if(mysNumber==1){
                mysProblem= getAddProblem();
            }else if (mysNumber==2){
                mysProblem= getSubProblem();
            }else if (mysNumber==3){
                mysProblem= getMulProblem();
            }else {
                mysProblem= getDivProblem();
            }
            return mysProblem;
        }
    }
}
