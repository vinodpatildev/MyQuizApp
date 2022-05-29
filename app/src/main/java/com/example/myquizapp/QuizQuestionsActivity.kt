package com.example.myquizapp

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.core.content.ContextCompat
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONObject

class QuizQuestionsActivity : AppCompatActivity(), View.OnClickListener {
    private var mSelectedCat:Int = 9
    private var vQueue: RequestQueue? = null
    private var mCurrentPosition:Int = 1
    private var mQuestionList: ArrayList<Question>? = null
    private var mSelectedOption:Int = 0
    private var mUserName:String? = null
    private var mCorrectAnswers:Int = 0

    private var progressBar:ProgressBar? = null
    private var tvProgress: TextView? = null
    private var tvQuestion:TextView? = null
    private var ivImage: ImageView? = null

    private var tvOptionOne: TextView? = null
    private var tvOptionTwo: TextView? = null
    private var tvOptionThree: TextView? = null
    private var tvOptionFour: TextView? = null

    private var btnSubmit: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_questions)
        vQueue = Volley.newRequestQueue(this)

        mUserName = intent.getStringExtra(Constants.USER_NAME)
        mSelectedCat = intent.getStringExtra(Constants.SELECTED_CATEGORY)?.toInt()!!

        progressBar = findViewById<ProgressBar>(R.id.tv_progressBar)
        tvProgress = findViewById<TextView>(R.id.tv_progress)
        tvQuestion = findViewById<TextView>(R.id.tv_question)
        ivImage = findViewById<ImageView>(R.id.iv_image)

        tvOptionOne = findViewById<TextView>(R.id.tv_option_one)
        tvOptionTwo = findViewById<TextView>(R.id.tv_option_two)
        tvOptionThree = findViewById<TextView>(R.id.tv_option_three)
        tvOptionFour = findViewById<TextView>(R.id.tv_option_four)

        btnSubmit = findViewById<Button>(R.id.btn_submit)

        tvOptionOne?.setOnClickListener(this)
        tvOptionTwo?.setOnClickListener(this)
        tvOptionThree?.setOnClickListener(this)
        tvOptionFour?.setOnClickListener(this)
        btnSubmit?.setOnClickListener(this)

        Log.i("Passed ","stage 1")
        loadQuestions()
        Log.i("Passed ","stage 2")

//        setQuestions()
    }

    private fun loadQuestions(){
        Log.i("Passed ","loadQuestions() :initiate")
        val questionList = ArrayList<Question>()

        val url = "https://opentdb.com/api.php?amount=10&category=$mSelectedCat&difficulty=easy&type=multiple"
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET,
            url,
            null,
            { response ->
                val jsonQuestions : JSONArray? = response.getJSONArray("results")

                for(item in 0..9){
                    val id = item + 1
                    var questionStatement = filterString((jsonQuestions?.get(item) as JSONObject).getString("question").toString())

                    val correctAnswer = (jsonQuestions?.get(item) as JSONObject).getString("correct_answer").toString()

                    var wans = (jsonQuestions?.get(item) as JSONObject).getString("incorrect_answers")
                    wans = wans.substring(1,wans.length-1)
                    var wrongAnswers = (wans.split(",").toTypedArray()).map{filterString(it)}

                    val correctOption = (1..4).random()

                    when(correctOption){
                        1 -> {questionList.add(Question(id,
                            questionStatement,
                            correctAnswer,
                            wrongAnswers[0],
                            wrongAnswers[1],
                            wrongAnswers[2],
                            1
                        ))}
                        2 -> {questionList.add(Question(id,
                            questionStatement,
                            wrongAnswers[0],
                            correctAnswer,
                            wrongAnswers[1],
                            wrongAnswers[2],
                            2
                        ))}
                        3 -> {questionList.add(Question(id,
                            questionStatement,
                            wrongAnswers[0],
                            wrongAnswers[1],
                            correctAnswer,
                            wrongAnswers[2],
                            3
                        ))}
                        4 -> {questionList.add(Question(id,
                            questionStatement,
                            wrongAnswers[0],
                            wrongAnswers[1],
                            wrongAnswers[2],
                            correctAnswer,
                            4
                        ))}
                    }
                }
                mQuestionList = questionList
                setQuestions()
            },
            { error ->
                Toast.makeText(this,"--failure--",Toast.LENGTH_LONG).show()
            }
        )
        vQueue?.add(jsonObjectRequest)
        Log.i("Passed ","loadQuestions() :terminate")
    }
    private fun filterString(str: String): String {
        return str.replace("\"","", true).replace("&quot;","\"", true).replace("&#039;","'", true).replace("&rsquo;","'", true).replace("&lsquo","'", true)

    }
    private fun setQuestions() {
        Log.i("Passed ","setQuestions() :initiate")
        setDefaultOptionsView()
        val que: Question = mQuestionList!![mCurrentPosition - 1]
        progressBar?.progress = mCurrentPosition
        tvProgress?.text = "$mCurrentPosition/${progressBar?.max}"
        tvQuestion?.text = que.question
//        ivImage?.setImageResource(que.image)

        tvOptionOne?.text = que.optionOne
        tvOptionTwo?.text = que.optionTwo
        tvOptionThree?.text = que.optionThree
        tvOptionFour?.text = que.optionFour

        if(mCurrentPosition == mQuestionList!!.size){
            btnSubmit?.text = "FINISH"
        }else{
            btnSubmit?.text = "SUBMIT"
        }
        Log.i("Passed ","setQuestions() :terminate")
    }
    fun setDefaultOptionsView(){
        Log.i("Passed ","setDefaultOptions() :initiate")
        val options = ArrayList<TextView>()
        tvOptionOne?.let{
            options.add(0,it)
        }
        tvOptionTwo?.let{
            options.add(1,it)
        }
        tvOptionThree?.let{
            options.add(2,it)
        }
        tvOptionFour?.let{
            options.add(3,it)
        }
        for(op in options){
            op.setTextColor(Color.parseColor("#7A8089"))
            op.typeface = Typeface.DEFAULT
            op.background = ContextCompat.getDrawable(
                this,
                R.drawable.default_option_border_bg
            )
        }
        Log.i("Passed ","setDefaultOptionsView() :terminate")
    }
    fun selectedOptionView(tv:TextView, selectedOptionNum: Int){
        Log.i("Passed ","selectedOptionView()")
        setDefaultOptionsView()

        mSelectedOption = selectedOptionNum

        tv.setTextColor(Color.parseColor("#363A43"))
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(
            this,
            R.drawable.selected_option_border_bg
        )
    }
    override fun onClick(view: View?) {
        Log.i("Passed ","onClick()")
        when(view?.id){
            R.id.tv_option_one -> {
                tvOptionOne?.let{
                    Log.i("Passed ","tvOptionOne")
                    selectedOptionView(it,1)
                }
            }
            R.id.tv_option_two -> {
                tvOptionTwo?.let{
                    selectedOptionView(it,2)
                }
            }
            R.id.tv_option_three -> {
                tvOptionThree?.let{
                    selectedOptionView(it,3)
                }
            }
            R.id.tv_option_four -> {
                tvOptionFour?.let{
                    selectedOptionView(it,4)
                }
            }
            R.id.btn_submit ->{
                if(mSelectedOption == 0){
                    mCurrentPosition++

                    when{
                        mCurrentPosition <= mQuestionList!!.size ->{
                            setQuestions()
                        }
                        else ->{
                            val intent = Intent(this, ResultsActivity::class.java)
                            intent.putExtra(Constants.USER_NAME, mUserName)
                            intent.putExtra(Constants.CORRECT_ANSWERS, mCorrectAnswers)
                            intent.putExtra(Constants.TOTAL_QUESTIONS, mQuestionList!!.size)
                            startActivity(intent)
                            finish()
                        }
                    }
                }else{
                    val question = mQuestionList?.get(mCurrentPosition-1)
                    if(question!!.correctAnswer != mSelectedOption){
                        answerView(mSelectedOption, R.drawable.wrong_option_border_bg)
                    }else{
                        mCorrectAnswers++;
                    }
                    answerView(question.correctAnswer,R.drawable.correct_option_border_bg)

                    if(mCurrentPosition == mQuestionList!!.size){
                        btnSubmit?.text = "FINISH"
                    }else{
                        btnSubmit?.text = "GO TO NEXT QUESTION"
                    }
                    mSelectedOption = 0
                }
            }
        }
    }
    fun answerView(answer:Int, drawbleView: Int){
        when(answer){
            1->{
                tvOptionOne?.background = ContextCompat.getDrawable(
                    this,
                    drawbleView
                )
            }
            2->{
                tvOptionTwo?.background = ContextCompat.getDrawable(
                    this,
                    drawbleView
                )
            }
            3->{
                tvOptionThree?.background = ContextCompat.getDrawable(
                    this,
                    drawbleView
                )
            }
            4->{
                tvOptionFour?.background = ContextCompat.getDrawable(
                    this,
                    drawbleView
                )
            }
        }
    }
}











