package com.two2.pranjal.mathisfun
//
//import android.content.Intent
//import android.os.Bundle
//import android.widget.TextView
//import androidx.appcompat.app.AppCompatActivity
//import butterknife.BindView
//import butterknife.ButterKnife
//import com.google.android.material.chip.Chip
//import com.google.android.material.chip.ChipGroup
//import com.google.android.material.floatingactionbutton.FloatingActionButton
//import com.google.android.material.snackbar.Snackbar
//import com.two2.pranjal.mathisfun.MainScreen
//
//class ChooseGame : AppCompatActivity() {
//    @BindView(R.id.next_choose_game)
//    var nextButton: FloatingActionButton? = null
//
//    @BindView(R.id.chooseGameTitle)
//    var screenTitle: TextView? = null
//
//    @BindView(R.id.gameTypeTitle)
//    var gameTypeTitle: TextView? = null
//
//    @BindView(R.id.mul_chip)
//    var mulChip: Chip? = null
//
//    @BindView(R.id.div_chip)
//    var divChip: Chip? = null
//
//    @BindView(R.id.mys_chip)
//    var mysChip: Chip? = null
//
//    @BindView(R.id.sub_chip)
//    var subChip: Chip? = null
//
//    @BindView(R.id.easy_chip)
//    var easyChip: Chip? = null
//
//    @BindView(R.id.med_chip)
//    var mediumChip: Chip? = null
//
//    @BindView(R.id.hard_chip)
//    var hardChip: Chip? = null
//
//    @BindView(R.id.difficultyTypeChipGroup)
//    var difficultyTypeChipGroup: ChipGroup? = null
//
//    @BindView(R.id.gameTypeChipGroup)
//    var gameTypeChipGroup: ChipGroup? = null
//
//    @BindView(R.id.add_chip)
//    var addChip: Chip? = null
//    var type = -1
//    var difficulty = -1
//    var add = false
//    var sub = false
//    var div = false
//    var mul = false
//    var mys = false
//    var easy = false
//    var hard = false
//    var medium = false
//    var gameType: String? = null
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.choosegame)
//        ButterKnife.bind(this)
//        val intent = intent
//        gameType = intent.getStringExtra(resources.getString(R.string.game_type))
//        if (gameType == resources.getString(R.string.play_game)) {
//            screenTitle!!.text = resources.getString(R.string.choose_game)
//        } else if (gameType == resources.getString(R.string.index_card)) {
//            screenTitle!!.text = resources.getString(R.string.choose_index)
//        }
//        gameTypeChipGroup!!.setOnCheckedChangeListener { group, checkedId -> type = group.checkedChipId }
//        difficultyTypeChipGroup!!.setOnCheckedChangeListener { group, checkedId -> difficulty = group.checkedChipId }
//        addChip!!.setOnCheckedChangeListener { compoundButton, b -> add = b }
//        subChip!!.setOnCheckedChangeListener { compoundButton, b -> sub = b }
//        mulChip!!.setOnCheckedChangeListener { compoundButton, b -> mul = b }
//        divChip!!.setOnCheckedChangeListener { compoundButton, b -> div = b }
//        mysChip!!.setOnCheckedChangeListener { compoundButton, b -> mys = b }
//        easyChip!!.setOnCheckedChangeListener { compoundButton, b -> easy = b }
//        hardChip!!.setOnCheckedChangeListener { compoundButton, b -> hard = b }
//        mediumChip!!.setOnCheckedChangeListener { compoundButton, b -> medium = b }
//        nextButton!!.setOnClickListener { validateFields() }
//    }
//
//    private fun validateFields() {
//        if (type == -1) {
//            Snackbar.make(findViewById(R.id.chooseGame), resources.getString(R.string.type_error), Snackbar.LENGTH_LONG).show()
//        } else if (difficulty == -1) {
//            Snackbar.make(findViewById(R.id.chooseGame), resources.getString(R.string.difficulty_error), Snackbar.LENGTH_LONG).show()
//        } else {
//            routeScreen()
//        }
//    }
//
//    private fun gameTypePick(): String {
//        var pick = ""
//        if (add) {
//            pick = resources.getString(R.string.add)
//        } else if (sub) {
//            pick = resources.getString(R.string.subtract)
//        } else if (mys) {
//            pick = resources.getString(R.string.mystery)
//        } else if (mul) {
//            pick = resources.getString(R.string.multiply)
//        } else if (div) {
//            pick = resources.getString(R.string.divide)
//        }
//        return pick
//    }
//
//    private fun difficultyTypePick(): String {
//        var pick = ""
//        if (easy) {
//            pick = resources.getString(R.string.easy)
//        } else if (medium) {
//            pick = resources.getString(R.string.medium)
//        } else if (hard) {
//            pick = resources.getString(R.string.hard)
//        }
//        return pick
//    }
//
//    private fun routeScreen() {
//        val gType = gameTypePick()
//        val dType = difficultyTypePick()
//        if (gameType == resources.getString(R.string.play_game)) {
//            val intent = Intent(this, GameActivity::class.java)
//            intent.putExtra(resources.getString(R.string.type), gType)
//            intent.putExtra(resources.getString(R.string.difficulty), dType)
//            startActivity(intent)
//        } else if (gameType == resources.getString(R.string.index_card)) {
//            val intent = Intent(this, IndexActivity::class.java)
//            intent.putExtra(resources.getString(R.string.type), gType)
//            intent.putExtra(resources.getString(R.string.difficulty), dType)
//            startActivity(intent)
//        }
//    }
//
//    override fun onBackPressed() {
//        super.onBackPressed()
//        startActivity(Intent(this, MainScreen::class.java))
//    }
//}