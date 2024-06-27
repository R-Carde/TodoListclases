package com.example.todolist

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import com.example.todolist.menuprincipal.menuprincipal
import com.example.todolist.register.signupActivity
import com.example.todolist.user.User

class MainActivity : AppCompatActivity() {

    //seccion private lateinit
    private lateinit var etMail: AppCompatEditText
    private lateinit var etPassword: AppCompatEditText
    private lateinit var btnLogin: AppCompatButton
    private lateinit var btnSignup: AppCompatButton




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //funcion donde iniciaran los componentes
        initComponents()
        // funcion donde iniciaran los listeners
        initListeners()
        val textViewWebLink = findViewById<TextView>(R.id.github)
        textViewWebLink.setOnClickListener {
            abrirPaginaWeb()
        }

    }

    private fun initListeners() {
        btnLogin.setOnClickListener {
            login()
        }

        btnSignup.setOnClickListener {
            // Inicia la actividad RegisterActivity al presionar el botón de registro
            val intent = Intent(this, signupActivity::class.java)
            startActivity(intent)

        }


    }

    private fun abrirPaginaWeb() {
        val url = "https://github.com/AKend0/ToDoList.git"  // Reemplaza con tu URL
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }

    private fun login() {
        val mail = etMail.text.toString()
        val password = etPassword.text.toString()
        val MIN_PASSWORD_LENGTH = 4

        when {
            mail.isEmpty() -> {
                etMail.error = "Este campo no puede estar vacío"
                etMail.requestFocus()
            }

            password.isEmpty() -> {
                etPassword.error = "Este campo no puede estar vacío"
                etPassword.requestFocus()
            }

            password.length < MIN_PASSWORD_LENGTH -> {
                etPassword.error = "La contraseña debe tener al menos $MIN_PASSWORD_LENGTH caracteres"
                etPassword.requestFocus()

            }

            else -> {
                // Todas las validaciones pasaron, no hay errores
                val usuario = User("", mail, password)

                cleanError()

                validar(usuario.email, usuario.password)

            }
        }
    }

    private fun validar(email: String, password: String) {
        when {
            email == "test@testing.com" && password == "testing" || email=="test" && password == "testing" -> {
                Toast.makeText(this, "Inicio correcto", Toast.LENGTH_SHORT).show()
                Toast.makeText(this, "Bienvenido", Toast.LENGTH_SHORT).show()
                Log.d("Login", "$email $password")

                val intentMenuPrincipal = Intent(this, menuprincipal::class.java)
                startActivity(intentMenuPrincipal)
                finish()
            }

            else->{
                etMail.error = "Correo Incorrecto"
                etMail.requestFocus()
                etPassword.error = "Contraseña incorrecta"
                etPassword.requestFocus()
            }
        }
    }

    private fun cleanError() {
        etMail.error = null
        etPassword.error = null

    }

    private fun initComponents() {
        etMail=findViewById(R.id.etUser)
        etPassword=findViewById(R.id.etPassword)
        btnLogin=findViewById(R.id.btnLogin)
        btnSignup=findViewById(R.id.btnSignup)


    }
}