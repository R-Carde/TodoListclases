package com.example.todolist.Tasks

data class TaskModel (
    val task : String ,
    val date : String,
    val category : String,
    val importantSelected : CharSequence?
) {

}