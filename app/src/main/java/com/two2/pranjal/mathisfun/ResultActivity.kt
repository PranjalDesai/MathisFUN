package com.two2.pranjal.mathisfun

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.games.Games
import com.google.android.material.button.MaterialButton
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.two2.pranjal.mathisfun.MainScreen

class ResultActivity : AppCompatActivity() {
    @BindView(R.id.responseView)
    var mRecyclerView: RecyclerView? = null

    @BindView(R.id.scoreText)
    var scoreTextView: TextView? = null

    @BindView(R.id.achievementButton)
    var achievementButton: MaterialButton? = null

    @BindView(R.id.leaderboardButton)
    var leaderboardButton: MaterialButton? = null

    @BindView(R.id.shareGame)
    var shareButton: FloatingActionButton? = null

    @BindView(R.id.linearGoogleGames)
    var linearLayout: LinearLayout? = null
    var total: String? = null
    private var totalInt = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        ButterKnife.bind(this)
        val intent = intent
        val userResponseArrayList: ArrayList<UserResponse>?
        userResponseArrayList = intent.getParcelableArrayListExtra(resources.getString(R.string.results))
        totalInt = intent.getIntExtra(resources.getString(R.string.correctAnswers), 0)
        total = totalInt.toString()
        scoreTextView!!.text = total
        val userResponseAdaptor = UserResponseAdaptor(userResponseArrayList)
        mRecyclerView!!.layoutManager = LinearLayoutManager(this)
        mRecyclerView!!.adapter = userResponseAdaptor
        val signIn = isSignedIn
        if (!signIn) {
            linearLayout!!.visibility = View.GONE
        } else {
            submitScore()
            unlockAchievements()
        }
        achievementButton!!.setOnClickListener { showAchievements() }
        leaderboardButton!!.setOnClickListener { showLeaderboard() }
        shareButton!!.setOnClickListener { share() }
    }

    private fun unlockAchievements() {
        if (totalInt >= 3 && totalInt < 5) {
            Games.getAchievementsClient(this, GoogleSignIn.getLastSignedInAccount(this)!!)
                    .unlock(getString(R.string.achievement_3_correct))
        } else if (totalInt >= 5 && totalInt < 10) {
            Games.getAchievementsClient(this, GoogleSignIn.getLastSignedInAccount(this)!!)
                    .unlock(getString(R.string.achievement_5_correct))
        } else if (totalInt >= 10 && totalInt < 15) {
            Games.getAchievementsClient(this, GoogleSignIn.getLastSignedInAccount(this)!!)
                    .unlock(getString(R.string.achievement_10_correct))
        } else if (totalInt >= 15 && totalInt < 20) {
            Games.getAchievementsClient(this, GoogleSignIn.getLastSignedInAccount(this)!!)
                    .unlock(getString(R.string.achievement_15_correct))
        } else if (totalInt >= 20) {
            Games.getAchievementsClient(this, GoogleSignIn.getLastSignedInAccount(this)!!)
                    .unlock(getString(R.string.achievement_20_correct))
        }
    }

    private fun submitScore() {
        Games.getLeaderboardsClient(this, GoogleSignIn.getLastSignedInAccount(this)!!)
                .submitScore(getString(R.string.leaderboard_total_correct), totalInt.toLong())
    }

    private fun showLeaderboard() {
        Games.getLeaderboardsClient(this, GoogleSignIn.getLastSignedInAccount(this)!!)
                .getLeaderboardIntent(getString(R.string.leaderboard_total_correct))
                .addOnSuccessListener { intent -> startActivityForResult(intent!!, RC_LEADERBOARD_UI) }
    }

    private fun showAchievements() {
        Games.getAchievementsClient(this, GoogleSignIn.getLastSignedInAccount(this)!!)
                .achievementsIntent
                .addOnSuccessListener { intent -> startActivityForResult(intent!!, RC_ACHIEVEMENT_UI) }
    }

    private fun share() {
        val sharingIntent = Intent(Intent.ACTION_SEND)
        sharingIntent.type = "text/plain"
        sharingIntent.putExtra(Intent.EXTRA_TEXT, """I got $total correct in MATH is FUN!! Now it's your turn to try!!
https://play.google.com/store/apps/details?id=com.two2.pranjal.mathisfun""")
        sharingIntent.putExtra(Intent.EXTRA_SUBJECT, resources.getString(R.string.app_name))
        startActivity(Intent.createChooser(sharingIntent, resources.getString(R.string.share)))
    }

    private val isSignedIn: Boolean
        private get() = GoogleSignIn.getLastSignedInAccount(this) != null

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, MainScreen::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }

    companion object {
        private const val RC_ACHIEVEMENT_UI = 9003
        private const val RC_LEADERBOARD_UI = 9004
    }
}