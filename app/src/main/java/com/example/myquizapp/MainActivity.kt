package com.example.myquizapp

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private var btnStart: Button? = null
    private var etName: EditText? = null
    private var cat_spinner: Spinner? = null

    private var selected_cat :Int = 9

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnStart = findViewById(R.id.btn_start)
        etName = findViewById(R.id.et_name)
        cat_spinner = findViewById(R.id.cat_spinner)

        btnStart?.setOnClickListener{view ->
            if(etName?.text!!.isEmpty()){
                val alertBuilder= AlertDialog.Builder(this)
                alertBuilder.setTitle("ALERT")
                alertBuilder.setMessage("Your need to enter your name to start the quiz.")
                alertBuilder.setIcon(android.R.drawable.alert_light_frame)
                alertBuilder.setPositiveButton("OK"){
                    dialogInterface, which ->
                    dialogInterface.dismiss()
                }
                alertBuilder.setCancelable(false)
                alertBuilder.create().show()
            }else{
                val intent = Intent(this, QuizQuestionsActivity::class.java)
                intent.putExtra(Constants.USER_NAME,etName?.text.toString())
                intent.putExtra(Constants.SELECTED_CATEGORY,selected_cat.toString())
                startActivity(intent)
                finish()
            }
        }
        val categories = resources.getStringArray(R.array.categories)

        if (cat_spinner != null) {
            val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, categories)
            cat_spinner?.adapter = adapter

            cat_spinner?.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>,view: View, position: Int, id: Long) {
                    selected_cat = position + 9
                }
                override fun onNothingSelected(parent: AdapterView<*>) {
                    Toast.makeText(this@MainActivity,"Nothing selected",Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}