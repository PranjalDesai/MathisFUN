package com.two2.pranjal.mathisfun
//
//import android.os.Parcel
//import android.os.Parcelable
//
//class UserResponse : Parcelable {
//    var question: String?
//    var correctAnswer: String?
//    var userAnswer: String?
//
//    constructor(question: String?, correctAnswer: String?, userAnswer: String?) {
//        this.question = question
//        this.correctAnswer = correctAnswer
//        this.userAnswer = userAnswer
//    }
//
//    protected constructor(`in`: Parcel) {
//        question = `in`.readString()
//        correctAnswer = `in`.readString()
//        userAnswer = `in`.readString()
//    }
//
//    override fun describeContents(): Int {
//        return 0
//    }
//
//    override fun writeToParcel(parcel: Parcel, i: Int) {
//        parcel.writeString(question)
//        parcel.writeString(correctAnswer)
//        parcel.writeString(userAnswer)
//    }
//
//    companion object {
//        val CREATOR: Parcelable.Creator<UserResponse> = object : Parcelable.Creator<UserResponse?> {
//            override fun createFromParcel(`in`: Parcel): UserResponse? {
//                return UserResponse(`in`)
//            }
//
//            override fun newArray(size: Int): Array<UserResponse?> {
//                return arrayOfNulls(size)
//            }
//        }
//    }
//}