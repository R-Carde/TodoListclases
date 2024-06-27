package com.example.todolist.Tasks

import java.util.Date

data class Task(
    val tarea: String,
    val descripcion: String,
    val categoria: String,
    val fecha: Date?
)
