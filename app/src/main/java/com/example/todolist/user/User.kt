package com.example.todolist.user

data class User(val username: String, val email: String, val password: String) {
    override fun toString(): String {
        return "User(username='$username', email='$email', password='$password')"
    }
}