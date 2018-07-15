package com.two2.pranjal.mathisfun;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class UserResponseAdaptor extends RecyclerView.Adapter<UserResponseAdaptor.UserViewHolder> {

    private ArrayList<UserResponse> userResponses;

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutID= R.layout.response_list_item;
        LayoutInflater inflater= LayoutInflater.from(context);
        View view= inflater.inflate(layoutID, parent, false);

        return new UserViewHolder(view,context);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        holder.bind(userResponses.get(position));
    }

    @Override
    public int getItemCount() {
        return userResponses.size();
    }

    public UserResponseAdaptor(ArrayList<UserResponse> userResponses){
        this.userResponses= userResponses;
    }

    class UserViewHolder extends  RecyclerView.ViewHolder {

        TextView question;
        TextView answer;
        TextView user;
        Context mContext;

        public UserViewHolder(@NonNull View itemView, Context context) {
            super(itemView);
            question= itemView.findViewById(R.id.question);
            answer= itemView.findViewById(R.id.answer);
            user= itemView.findViewById(R.id.user);
            mContext=context;
        }

        void bind(UserResponse userResponse){
            question.setText(userResponse.getQuestion());
            answer.setText(userResponse.getCorrectAnswer());
            user.setText(userResponse.getUserAnswer());
            if(userResponse.getUserAnswer().equals(userResponse.getCorrectAnswer())){
                user.setTextColor(mContext.getResources().getColor(R.color.colorGreen));
            }else{
                user.setTextColor(mContext.getResources().getColor(R.color.colorRed));
            }
        }
    }
}
