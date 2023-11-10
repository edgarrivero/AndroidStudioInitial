package com.example.test1.firstApp.category

import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.test1.R

class CategoriesViewHolder(view:View) : RecyclerView.ViewHolder(view)  {

    private val tvCategoryName:TextView = view.findViewById(R.id.rvCategoryName)
    private val divider:View = view.findViewById(R.id.divider)
    fun render(taskCategory: TaskCategory){
        when(taskCategory){
            TaskCategory.Business -> {
                tvCategoryName.text = "NEGOCIOS"
                divider.setBackgroundColor(ContextCompat.getColor(divider.context, R.color.purple_700))
            }
            TaskCategory.Other -> {
                tvCategoryName.text = "OTROS"
                divider.setBackgroundColor(ContextCompat.getColor(divider.context, R.color.purple_500))
            }
            TaskCategory.Personal -> {
                tvCategoryName.text = "PERSONAL"
                divider.setBackgroundColor(ContextCompat.getColor(divider.context, R.color.teal_200))
            }
        }
    }
}