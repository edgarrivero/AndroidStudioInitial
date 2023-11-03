package com.example.test1.firstApp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.test1.R
import com.example.test1.R.layout.activity_result

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_result)


        val tvResult = findViewById<TextView>(R.id.tvResult)
        val name = intent.extras?.getString("EXTRA_NAME")

        tvResult.text = "Hola $name"
    }
}