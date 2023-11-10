package com.example.test1.firstApp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.test1.R
import com.example.test1.R.layout.activity_first_app
import com.example.test1.firstApp.category.CategoriesAdapter
import com.example.test1.firstApp.category.TaskCategory
import com.google.android.material.slider.RangeSlider
import java.text.DecimalFormat

class FirstAppActivity : AppCompatActivity() {

    private var isMaleSelected = true
    private var isFemaleSelected = false

    private lateinit var viewMale:CardView
    private lateinit var viewFemale:CardView

    private lateinit var tvHeight: TextView
    private lateinit var rsHeight: RangeSlider

    private lateinit var rvCategory : RecyclerView

    private lateinit var categoriesAdapter: CategoriesAdapter

    private var categories = listOf(
        TaskCategory.Business,
        TaskCategory.Personal,
        TaskCategory.Other
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_first_app)

        initComponents()
        initListeners()
        initUi()

        val btnStart = findViewById<AppCompatButton>(R.id.btnStart)
        val etName = findViewById<AppCompatEditText>(R.id.etName)

        btnStart.setOnClickListener {
            val name = etName.text.toString()

            if (name.isNotEmpty()){
                val intent = Intent(this, ResultActivity::class.java)
                intent.putExtra("EXTRA_NAME", name)
                startActivity(intent)
            }

        }
    }

    private fun initUi() {
        setGenderColor()
        categoriesAdapter = CategoriesAdapter(categories)
        rvCategory.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rvCategory.adapter = categoriesAdapter
    }

    private fun initComponents() {
        viewMale = findViewById(R.id.viewMale)
        viewFemale = findViewById(R.id.viewFemale)
        tvHeight = findViewById(R.id.tvHeight)
        rsHeight = findViewById(R.id.rsHeight)
        rvCategory = findViewById(R.id.rvCategories)
    }

    private fun initListeners() {
        viewMale.setOnClickListener {
            changeGenderColor()
            setGenderColor()
        }
        viewFemale.setOnClickListener {
            changeGenderColor()
            setGenderColor()
        }
        rsHeight.addOnChangeListener { slider, value, fromUser ->
            val df = DecimalFormat("#.##")
            val result = df.format(value)
            tvHeight.text = "$result cm"
        }
    }

    private fun changeGenderColor() {
        isMaleSelected = !isMaleSelected
        isFemaleSelected = !isFemaleSelected
    }

    private fun setGenderColor() {
        viewMale.setCardBackgroundColor(getBackgroundColor(isMaleSelected))
        viewFemale.setCardBackgroundColor(getBackgroundColor(isFemaleSelected))
    }

    private fun getBackgroundColor(isSelectedComponent:Boolean): Int {

        val color = if (isSelectedComponent){
            R.color.purple_500
        }else{
            R.color.purple_200
        }

        return ContextCompat.getColor(this, color)
    }
}