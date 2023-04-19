package com.two2.pranjal.mathisfun
//
//import android.content.Intent
//import android.os.Bundle
//import android.view.View
//import android.widget.Button
//import androidx.appcompat.app.AppCompatActivity
//import androidx.cardview.widget.CardView
//import butterknife.BindView
//import butterknife.ButterKnife
//import com.google.android.gms.auth.api.Auth
//import com.google.android.gms.auth.api.signin.GoogleSignIn
//import com.google.android.gms.auth.api.signin.GoogleSignInAccount
//import com.google.android.gms.auth.api.signin.GoogleSignInClient
//import com.google.android.gms.auth.api.signin.GoogleSignInOptions
//import com.google.android.gms.common.SignInButton
//import com.google.android.material.snackbar.Snackbar
//
//class MainScreen : AppCompatActivity() {
//    private var mGoogleSignInClient: GoogleSignInClient? = null
//    private var mGoogleSignInAccount: GoogleSignInAccount? = null
//
//    @BindView(R.id.sign_in_button)
//    var signInButton: SignInButton? = null
//
//    @BindView(R.id.sign_out_button)
//    var signOutButton: Button? = null
//
//    @BindView(R.id.play)
//    var playCard: CardView? = null
//
//    @BindView(R.id.index)
//    var indexCard: CardView? = null
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main_screen)
//        ButterKnife.bind(this)
//        playCard!!.setOnClickListener {
//            val intent = Intent(this@MainScreen, ChooseGame::class.java)
//            intent.putExtra(resources.getString(R.string.game_type), resources.getString(R.string.play_game))
//            startActivity(intent)
//        }
//        indexCard!!.setOnClickListener {
//            val intent = Intent(this@MainScreen, ChooseGame::class.java)
//            intent.putExtra(resources.getString(R.string.game_type), resources.getString(R.string.index_card))
//            startActivity(intent)
//        }
//        signInButton!!.setOnClickListener { startSignInIntent() }
//        signOutButton!!.setOnClickListener { startSignOutIntent() }
//    }
//
//    private fun startSignOutIntent() {
//        mGoogleSignInClient!!.signOut()
//        signOutButton!!.visibility = View.GONE
//        signInButton!!.visibility = View.VISIBLE
//        Snackbar.make(findViewById(R.id.mainScreen), resources.getString(R.string.sign_out_success), Snackbar.LENGTH_LONG).show()
//    }
//
//    override fun onResume() {
//        super.onResume()
//        signInSilently()
//    }
//
//    private fun signInSilently() {
//        mGoogleSignInClient = GoogleSignIn.getClient(this,
//                GoogleSignInOptions.DEFAULT_GAMES_SIGN_IN)
//        mGoogleSignInClient!!.silentSignIn().addOnCompleteListener(this
//        ) { task ->
//            if (task.isSuccessful) {
//                // The signed in account is stored in the task's result.
//                mGoogleSignInAccount = task.result
//                signOutButton!!.visibility = View.VISIBLE
//                signInButton!!.visibility = View.GONE
//                Snackbar.make(findViewById(R.id.mainScreen), resources.getString(R.string.sign_in_success), Snackbar.LENGTH_LONG).show()
//            } else {
//                // Player will need to sign-in explicitly using via UI
//                signOutButton!!.visibility = View.GONE
//                signInButton!!.visibility = View.VISIBLE
//            }
//        }
//    }
//
//    private fun startSignInIntent() {
//        val signInClient = GoogleSignIn.getClient(this,
//                GoogleSignInOptions.DEFAULT_GAMES_SIGN_IN)
//        val intent = signInClient.signInIntent
//        startActivityForResult(intent, RC_SIGN_IN)
//    }
//
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        if (requestCode == RC_SIGN_IN) {
//            val result = Auth.GoogleSignInApi.getSignInResultFromIntent(data!!)
//            if (result!!.isSuccess) {
//                mGoogleSignInAccount = result.signInAccount
//                signOutButton!!.visibility = View.VISIBLE
//                signInButton!!.visibility = View.GONE
//                Snackbar.make(findViewById(R.id.mainScreen), resources.getString(R.string.sign_in_success), Snackbar.LENGTH_LONG).show()
//            } else {
//                var message = result.status.statusMessage
//                if (message == null || message.isEmpty()) {
//                    message = resources.getString(R.string.something_went_wrong)
//                }
//                Snackbar.make(findViewById(R.id.mainScreen), message, Snackbar.LENGTH_LONG).show()
//                signOutButton!!.visibility = View.VISIBLE
//            }
//        }
//    }
//
//    companion object {
//        private const val RC_SIGN_IN = 9001
//    }
//}