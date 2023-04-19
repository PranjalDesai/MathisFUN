package com.two2.pranjal.mathisfun
//
//import android.content.Context
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.TextView
//import androidx.recyclerview.widget.RecyclerView
//import com.two2.pranjal.mathisfun.UserResponseAdaptor.UserViewHolder
//
//class UserResponseAdaptor(private val userResponses: ArrayList<UserResponse>?) : RecyclerView.Adapter<UserViewHolder>() {
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
//        val context = parent.context
//        val layoutID = R.layout.response_list_item
//        val inflater = LayoutInflater.from(context)
//        val view = inflater.inflate(layoutID, parent, false)
//        return UserViewHolder(view, context)
//    }
//
//    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
//        holder.bind(userResponses!![position])
//    }
//
//    override fun getItemCount(): Int {
//        return userResponses!!.size
//    }
//
//    inner class UserViewHolder(itemView: View, context: Context) : RecyclerView.ViewHolder(itemView) {
//        var question: TextView
//        var answer: TextView
//        var user: TextView
//        var mContext: Context
//        fun bind(userResponse: UserResponse) {
//            question.text = userResponse.question
//            answer.text = userResponse.correctAnswer
//            user.text = userResponse.userAnswer
//            if (userResponse.userAnswer == userResponse.correctAnswer) {
//                user.setTextColor(mContext.resources.getColor(R.color.colorGreen))
//            } else {
//                user.setTextColor(mContext.resources.getColor(R.color.colorRed))
//            }
//        }
//
//        init {
//            question = itemView.findViewById(R.id.question)
//            answer = itemView.findViewById(R.id.answer)
//            user = itemView.findViewById(R.id.user)
//            mContext = context
//        }
//    }
//}