package com.example.test1.firstApp

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
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
import com.example.test1.firstApp.category.Task
import com.example.test1.firstApp.category.TaskCategory
import com.example.test1.firstApp.category.TaskCategory.*
import com.example.test1.firstApp.category.TasksAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
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
    private lateinit var rvTask : RecyclerView

    private lateinit var categoriesAdapter: CategoriesAdapter
    private lateinit var taskAdapter: TasksAdapter

    private lateinit var fabAddTask:FloatingActionButton

    private var categories = listOf(
        Business,
        Personal,
        Other
    )

    private val tasks = mutableListOf(
        Task("Marcus", Business),
        Task("Michelle", Personal),
        Task("Joseph", Other)
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

        taskAdapter = TasksAdapter(tasks)
        rvTask.layoutManager = LinearLayoutManager(this)
        rvTask.adapter = taskAdapter
    }

    private fun initComponents() {
        viewMale = findViewById(R.id.viewMale)
        viewFemale = findViewById(R.id.viewFemale)
        tvHeight = findViewById(R.id.tvHeight)
        rsHeight = findViewById(R.id.rsHeight)
        rvCategory = findViewById(R.id.rvCategories)
        rvTask = findViewById(R.id.rvTask)
        fabAddTask = findViewById(R.id.fabAddTask)
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
        fabAddTask.setOnClickListener{
            showDialog()
        }
    }

    private fun showDialog(){
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog_task)

        val btnAddTask:Button = dialog.findViewById(R.id.btnAddTask)
        val etTask:EditText = dialog.findViewById(R.id.etTask)
        val rgCategories:RadioGroup = dialog.findViewById(R.id.rgCategories)

        btnAddTask.setOnClickListener {
            val selectedId = rgCategories.checkedRadioButtonId
            val selectedrb:RadioButton = rgCategories.findViewById(selectedId)
            val currentCategory:TaskCategory = when(selectedrb.text){
                "Negocios" -> Business
                "Personal" -> Personal
                else -> Other
            }

            tasks.add(Task(etTask.text.toString(), currentCategory))
            updateTasks()
            dialog.hide()
        }

        dialog.show()
    }

    private fun updateTasks(){
        taskAdapter.notifyDataSetChanged()

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