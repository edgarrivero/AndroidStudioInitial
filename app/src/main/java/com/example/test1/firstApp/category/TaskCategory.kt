package com.example.test1.firstApp.category

sealed class TaskCategory {
    object Personal : TaskCategory()
    object Business : TaskCategory()
    object Other : TaskCategory()
}