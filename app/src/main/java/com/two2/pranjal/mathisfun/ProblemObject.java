package com.two2.pranjal.mathisfun;

public class ProblemObject {

    private String question1;
    private String question2;
    private String answer;
    private String type;


    public ProblemObject(String q1, String q2, String ans, String type){
        this.answer=ans;
        this.question1=q1;
        this.question2= q2;
        this.type= type;
    }

    public String getQuestion1(){
        return question1;
    }

    public void setQuestion1(String question1) {
        this.question1 = question1;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getQuestion2() {
        return question2;
    }

    public void setQuestion2(String question2) {
        this.question2 = question2;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}

