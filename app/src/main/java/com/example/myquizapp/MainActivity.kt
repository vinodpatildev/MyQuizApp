package com.example.myquizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*

class MainActivity : AppCompatActivity() {
    private var selected_cat :Int = 9
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnStart : Button = findViewById<Button>(R.id.btn_start)
        val etName : EditText = findViewById<EditText>(R.id.et_name)
        btnStart.setOnClickListener{
            if(etName.text.isEmpty()){
                Toast.makeText(this,"Please Enter Your Name", Toast.LENGTH_LONG).show()
            }else{


                val intent = Intent(this, QuizQuestionsActivity::class.java)
                intent.putExtra(Constants.USER_NAME,etName.text.toString())
                intent.putExtra(Constants.SELECTED_CATEGORY,selected_cat.toString())
                startActivity(intent)
                finish()
            }
        }
        val categories = resources.getStringArray(R.array.categories)

        // access the spinner
        val cat_spinner = findViewById<Spinner>(R.id.cat_spinner)
        if (cat_spinner != null) {
            val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, categories)
            cat_spinner.adapter = adapter

            cat_spinner.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>,view: View, position: Int, id: Long) {
                    selected_cat = position + 9
                }
                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }
        }
    }
}