package com.example.myquizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import org.w3c.dom.Text

class ResultsActivity : AppCompatActivity() {
    private var mUserName:String? = null
    private var mCorrectAnswers:Int? = null
    private var mTotalQuestions:Int? = null

    private var tvUserName: TextView? = null
    private var tvScore:TextView? = null
    private var btnPlayAgain: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_results)

        mUserName = intent.getStringExtra(Constants.USER_NAME)
        mCorrectAnswers = intent.getIntExtra(Constants.CORRECT_ANSWERS,0)
        mTotalQuestions = intent.getIntExtra(Constants.TOTAL_QUESTIONS,10)

        tvUserName = findViewById(R.id.tv_username)
        tvScore = findViewById(R.id.tv_score)
        btnPlayAgain = findViewById(R.id.btn_play_again)

        tvUserName?.text = mUserName
        tvScore?.text = "You scored $mCorrectAnswers/$mTotalQuestions points"

        btnPlayAgain?.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}



