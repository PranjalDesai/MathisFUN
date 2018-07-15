package com.two2.pranjal.mathisfun;

import android.os.Parcel;
import android.os.Parcelable;

public class UserResponse implements Parcelable {

    private String question;
    private String correctAnswer;
    private String userAnswer;

    public UserResponse(String question, String correctAnswer, String userAnswer){
        this.question= question;
        this.correctAnswer= correctAnswer;
        this.userAnswer= userAnswer;
    }

    protected UserResponse(Parcel in) {
        this.question= in.readString();
        this.correctAnswer= in.readString();
        this.userAnswer= in.readString();
    }

    public static final Creator<UserResponse> CREATOR = new Creator<UserResponse>() {
        @Override
        public UserResponse createFromParcel(Parcel in) {
            return new UserResponse(in);
        }

        @Override
        public UserResponse[] newArray(int size) {
            return new UserResponse[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(question);
        parcel.writeString(correctAnswer);
        parcel.writeString(userAnswer);
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public String getUserAnswer() {
        return userAnswer;
    }

    public void setUserAnswer(String userAnswer) {
        this.userAnswer = userAnswer;
    }
}
