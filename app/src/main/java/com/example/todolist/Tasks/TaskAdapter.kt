package com.example.todolist.Tasks

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.R
import java.text.SimpleDateFormat
import java.util.Locale

class TaskAdapter(private val postList: List<Task>) :
    RecyclerView.Adapter<TaskAdapter.PostViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_task_todo, parent, false)
        return PostViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = postList[position]
        holder.tareaTextView.text = post.tarea
        holder.descripcionTextView.text = post.descripcion
        holder.categoriaTextView.text = post.categoria

        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        holder.fechaTextView.text = post.fecha?.let { dateFormat.format(it) } ?: "Sin fecha"
    }

    override fun getItemCount(): Int {
        return postList.size
    }

    inner class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tareaTextView: TextView = itemView.findViewById(R.id.tareaTextView)
        val descripcionTextView: TextView = itemView.findViewById(R.id.descripcionTextView)
        val categoriaTextView: TextView = itemView.findViewById(R.id.categoriaTextView)
        val fechaTextView: TextView = itemView.findViewById(R.id.fechaTextView)
    }
}