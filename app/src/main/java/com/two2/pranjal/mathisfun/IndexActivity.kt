package com.two2.pranjal.mathisfun
//
//import android.content.Intent
//import android.os.Bundle
//import android.widget.TextView
//import androidx.appcompat.app.AppCompatActivity
//import butterknife.BindView
//import butterknife.ButterKnife
//import com.google.android.material.floatingactionbutton.FloatingActionButton
//import com.two2.pranjal.mathisfun.MainScreen
//import com.wajahatkarim3.easyflipview.EasyFlipView
//
//class IndexActivity : AppCompatActivity() {
//    @BindView(R.id.flipView)
//    var flipView: EasyFlipView? = null
//
//    @BindView(R.id.frontQuestion)
//    var frontQuestion: TextView? = null
//
//    @BindView(R.id.backAnswer)
//    var backAnswer: TextView? = null
//
//    @BindView(R.id.skipIndexCard)
//    var skipButton: FloatingActionButton? = null
//
//    @BindView(R.id.flipIndexCard)
//    var flipButton: FloatingActionButton? = null
//    var problemFinder: ProblemFinder? = null
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_index)
//        ButterKnife.bind(this)
//        val intent = intent
//        val type = intent.getStringExtra(resources.getString(R.string.type))
//        val difficulty = intent.getStringExtra(resources.getString(R.string.difficulty))
//        problemFinder = ProblemFinder(type, difficulty, this)
//        val problemObject = problemFinder.getProblem()
//        val question = problemObject.question1 + " " + problemObject.type + " " + problemObject.question2
//        frontQuestion!!.text = question
//        backAnswer.setText(problemObject.answer)
//        skipButton!!.setOnClickListener { nextCard() }
//        flipButton!!.setOnClickListener { flipView!!.flipTheView() }
//    }
//
//    private fun nextCard() {
//        val problemObject = problemFinder.getProblem()
//        val question = problemObject.question1 + " " + problemObject.type + " " + problemObject.question2
//        frontQuestion!!.text = question
//        if (flipView!!.isBackSide) {
//            flipView!!.flipTheView()
//        }
//        backAnswer.setText(problemObject.answer)
//    }
//
//    override fun onBackPressed() {
//        super.onBackPressed()
//        val intent = Intent(this, MainScreen::class.java)
//        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
//        startActivity(intent)
//    }
//}