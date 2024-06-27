package com.example.todolist.Tasks


import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @POST("tarea")  // Aquí se especifica el endpoint relativo a la URL base
    fun saveTask(@Body task: com.example.todolist.Tasks.Task): Call<TaskResponse>

    @GET("tarea")  // Endpoint para obtener la lista de tareas
    fun getTasks(): Call<List<Task>>
}