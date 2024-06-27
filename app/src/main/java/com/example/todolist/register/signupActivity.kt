package com.example.todolist.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import com.example.todolist.MainActivity
import com.example.todolist.R
import com.example.todolist.user.User



class signupActivity : AppCompatActivity() {
    private lateinit var etMail:AppCompatEditText
    private lateinit var etUserSignUp : AppCompatEditText
    private lateinit var etPasswordSignUp : AppCompatEditText
    private lateinit var etConfirmPasswordSignup:AppCompatEditText
    private lateinit var btnRegister: AppCompatButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        initComponents()

        btnRegister.setOnClickListener{
            signUp()
        }

    }

    private fun signUp() {

        val mail = etMail.text.toString()
        val user = etUserSignUp.text.toString()
        val password = etPasswordSignUp.text.toString()
        val confirmPassword = etConfirmPasswordSignup.text.toString()
        val MIN_PASSWORD_LENGTH = 4

        when{
            user.isEmpty() -> {
                etUserSignUp.error = "Este campo no puede estar vacío"
                etUserSignUp.requestFocus()
            }
            mail.isEmpty()->{
                etMail.error = "Este campo no puede estar vacío"
                etMail.requestFocus()
            }

            password.isEmpty() -> {
                etPasswordSignUp.error = "Este campo no puede estar vacío"
                etPasswordSignUp.requestFocus()
            }

            confirmPassword.isEmpty() -> {
                etConfirmPasswordSignup.error = "Este campo no puede estar vacío"
                etConfirmPasswordSignup.requestFocus()
            }

            password!= confirmPassword -> {
                etConfirmPasswordSignup.error = "Las contraseñas no coinciden"
                etConfirmPasswordSignup.requestFocus()
            }
            password.length < MIN_PASSWORD_LENGTH -> {
                etPasswordSignUp.error = "La contraseña debe tener al menos $MIN_PASSWORD_LENGTH caracteres"
                etPasswordSignUp.requestFocus()

            }
            else -> {
                // Todas las validaciones pasaron, no hay errores
                val usuario = User(user, mail, password)
                cleanError()

                Toast.makeText(this, "Registro correcto", Toast.LENGTH_SHORT).show()
                Toast.makeText(this, "Bienvenido ${user}", Toast.LENGTH_SHORT).show()

                Log.d("Signup", usuario.toString())

                val intentLogin = Intent(this, MainActivity::class.java)
                startActivity(intentLogin)
                finish()
            }
        }
    }

    private fun cleanError() {
        etUserSignUp.error = null
        etPasswordSignUp.error = null
        etConfirmPasswordSignup.error = null
    }

    private fun initComponents() {
        etUserSignUp=findViewById(R.id.etUserSignUp)
        etPasswordSignUp=findViewById(R.id.etPasswordSignup)
        etConfirmPasswordSignup=findViewById(R.id.etConfirmPasswordSignup)
        btnRegister=findViewById(R.id.btnRegister)
        etMail=findViewById(R.id.etMail)
    }

}