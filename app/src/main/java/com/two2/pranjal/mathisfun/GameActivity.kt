package com.two2.pranjal.mathisfun

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import butterknife.BindView
import butterknife.ButterKnife
import com.google.android.material.button.MaterialButton
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.two2.pranjal.mathisfun.MainScreen
import com.two2.pranjal.mathisfun.ResultActivity
import io.github.krtkush.lineartimer.LinearTimer
import io.github.krtkush.lineartimer.LinearTimerStates
import io.github.krtkush.lineartimer.LinearTimerView

class GameActivity : AppCompatActivity(), LinearTimer.TimerListener {
    var problemFinder: ProblemFinder? = null

    @BindView(R.id.submitBtn)
    var submitBtn: MaterialButton? = null

    @BindView(R.id.answerInputLayout)
    var userAnswerLayout: TextInputLayout? = null

    @BindView(R.id.answerInputEditText)
    var userAnswerEditText: TextInputEditText? = null

    @BindView(R.id.linearTimerView)
    var linearTimerView: LinearTimerView? = null

    @BindView(R.id.countDownTimerView)
    var countDownTimerView: TextView? = null

    @BindView(R.id.time_left_play)
    var timeLeftPlay: TextView? = null

    @BindView(R.id.description_play)
    var descriptionPlay: TextView? = null

    @BindView(R.id.questionText)
    var questionTextView: TextView? = null

    @BindView(R.id.constraintLayoutPlay)
    var playLayout: ConstraintLayout? = null

    @BindView(R.id.constraintLayoutGame)
    var gameLayout: ConstraintLayout? = null

    @BindView(R.id.play_button)
    var playBtn: CardView? = null

    @BindView(R.id.pauseBtn)
    var pauseBtn: FloatingActionButton? = null
    var currentAnswer: String? = null
    var currentQuestion: String? = null
    var userAnswer: String? = null
    var userResponseArrayList = ArrayList<UserResponse>()
    var linearTimer: LinearTimer? = null
    var correctAnswer = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        ButterKnife.bind(this)
        val intent = intent
        val type = intent.getStringExtra(resources.getString(R.string.type))
        val difficulty = intent.getStringExtra(resources.getString(R.string.difficulty))
        problemFinder = ProblemFinder(type, difficulty, this)
        linearTimer = LinearTimer.Builder()
                .linearTimerView(linearTimerView)
                .duration(60000)
                .timerListener(this)
                .getCountUpdate(LinearTimer.COUNT_DOWN_TIMER, 1000)
                .build()
        playBtn!!.setOnClickListener { playGame() }
        userAnswerLayout!!.isFocusableInTouchMode = false
        userAnswerLayout!!.isFocusable = false
        userAnswerLayout!!.isFocusableInTouchMode = true
        userAnswerLayout!!.isFocusable = true
        submitBtn!!.setOnClickListener {
            userAnswer = if (userAnswerEditText!!.text == null) {
                ""
            } else {
                userAnswerEditText!!.text.toString()
            }
            if (userAnswer != currentAnswer) {
                userAnswerLayout!!.isErrorEnabled = true
                userAnswerLayout!!.error = resources.getString(R.string.answer_error)
            } else {
                userAnswerLayout!!.isErrorEnabled = false
                correctAnswer++
            }
            userResponseArrayList.add(UserResponse(currentQuestion, currentAnswer, userAnswer))
            nextQuestion()
            userAnswerEditText!!.setText("")
            userAnswer = ""
        }
        pauseBtn!!.setOnClickListener { pauseGame() }
    }

    private fun pauseGame() {
        linearTimer!!.pauseTimer()
        hideKeyboard()
        gameLayout!!.visibility = View.GONE
        playLayout!!.visibility = View.VISIBLE
        timeLeftPlay!!.text = countDownTimerView!!.text.toString() + " seconds left"
        descriptionPlay!!.text = resources.getString(R.string.continue_game)
    }

    fun hideKeyboard() {
        // Check if no view has focus:
        val view = currentFocus
        if (view != null) {
            val inputManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            inputManager.hideSoftInputFromWindow(view.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
        }
    }

    private fun playGame() {
        userAnswerLayout!!.isFocusableInTouchMode = false
        userAnswerLayout!!.isFocusable = false
        userAnswerLayout!!.isFocusableInTouchMode = true
        userAnswerLayout!!.isFocusable = true
        if (linearTimer!!.state == LinearTimerStates.INITIALIZED) {
            gameLayout!!.visibility = View.VISIBLE
            playLayout!!.visibility = View.GONE
            nextQuestion()
            linearTimer!!.startTimer()
        } else if (linearTimer!!.state == LinearTimerStates.PAUSED) {
            gameLayout!!.visibility = View.VISIBLE
            playLayout!!.visibility = View.GONE
            linearTimer!!.resumeTimer()
        }
    }

    private fun nextQuestion() {
        val `object` = problemFinder.getProblem()
        currentAnswer = `object`.answer
        currentQuestion = `object`.question1 + " " + `object`.type + " " + `object`.question2
        questionTextView!!.text = currentQuestion
    }

    private fun routeScreen() {
        val intent1 = Intent(this, ResultActivity::class.java)
        intent1.putParcelableArrayListExtra(resources.getString(R.string.results), userResponseArrayList)
        intent1.putExtra(resources.getString(R.string.correctAnswers), correctAnswer)
        startActivity(intent1)
    }

    override fun animationComplete() {
        userAnswerLayout!!.isFocusableInTouchMode = false
        userAnswerLayout!!.isFocusable = false
        routeScreen()
    }

    override fun timerTick(tickUpdateInMillis: Long) {
        countDownTimerView!!.text = (tickUpdateInMillis / 1000).toString()
    }

    override fun onTimerReset() {}
    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, MainScreen::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }
}