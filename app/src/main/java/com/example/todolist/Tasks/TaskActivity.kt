package com.example.todolist.Tasks


import android.app.DatePickerDialog
import android.content.Intent
import android.icu.util.Calendar
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatSpinner
import com.example.todolist.R
import com.example.todolist.menuprincipal.menuprincipal
import com.example.todolist.mostrardatos.MostrarDatosActivity
import com.example.todolist.register.signupActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


class TaskActivity : AppCompatActivity() {
    private lateinit var ettask : AppCompatEditText
    private lateinit var etDescription: AppCompatEditText
    private lateinit var spinnerCategory: AppCompatSpinner
    private lateinit var buttonSave: AppCompatButton
    private lateinit var buttonBack: AppCompatButton
    private lateinit var textViewFechaSeleccionada: TextView
    private lateinit var botonSeleccionarFecha: Button
    val categorias = arrayOf("Por hacer", "En curso", "Terminado")
    private var fechaSeleccionada: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task)

        initComponents()
        initListeners()
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, categorias)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerCategory.adapter = adapter

    }

    private fun initListeners() {
        buttonSave.setOnClickListener {
            saveTask()

        }
        buttonBack.setOnClickListener {
            val intent = Intent(this, menuprincipal::class.java)
            startActivity(intent)
            finish()
        }
        botonSeleccionarFecha.setOnClickListener {
            mostrarSelectorDeFecha()
        }

    }




    private fun saveTask() {
        val tarea = ettask.text.toString()
        val descripcion = etDescription.text.toString()
        val categoria = spinnerCategory.selectedItem.toString()
        val fecha: Date? = fechaSeleccionada?.let  {
            convertirStringADate(it)
        }

        // Verificar que los campos obligatorios no estén vacíos
        if (tarea.isEmpty() || descripcion.isEmpty() || fecha == null) {
            Toast.makeText(this@TaskActivity, "Por favor, complete los campos obligatorios", Toast.LENGTH_SHORT).show()
            return
        }else{
            val intent = Intent(this, menuprincipal::class.java)
            startActivity(intent)
        }

        // Ahora puedes hacer la llamada a la API solo si los campos obligatorios tienen datos
        val retrofit = Retrofit.Builder()
            .baseUrl("https://65bede73dcfcce42a6f2f4ac.mockapi.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService = retrofit.create(ApiService::class.java)

        val task = Task(tarea, descripcion, categoria, fecha)

        val call: Call<TaskResponse> = apiService.saveTask(task)

        call.enqueue(object : Callback<TaskResponse> {
            override fun onResponse(call: Call<TaskResponse>, response: Response<TaskResponse>) {
                if (response.isSuccessful) {
                    Toast.makeText(this@TaskActivity, "Tarea guardada exitosamente", Toast.LENGTH_SHORT).show()


                } else {
                    Toast.makeText(this@TaskActivity, "Error al guardar la tarea", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<TaskResponse>, t: Throwable) {
                Toast.makeText(this@TaskActivity, "Error de red: $t", Toast.LENGTH_SHORT).show()
            }
        })
    }


    private fun mostrarSelectorDeFecha() {
        val calendario = Calendar.getInstance()
        val añoActual = calendario.get(Calendar.YEAR)
        val mesActual = calendario.get(Calendar.MONTH)
        val diaActual = calendario.get(Calendar.DAY_OF_MONTH)

        val dialogoFecha = DatePickerDialog(
            this,
            { _, year, month, day ->
                fechaSeleccionada = "$day/${month + 1}/$year"
                textViewFechaSeleccionada.text = "Fecha seleccionada: $fechaSeleccionada"
            },
            // Usa la fechaSeleccionada si está disponible, de lo contrario, usa la fecha actual
            fechaSeleccionada?.let {
                val fechaSplit = it.split("/")
                fechaSplit[2].toInt()
            } ?: añoActual,
            fechaSeleccionada?.let {
                val fechaSplit = it.split("/")
                fechaSplit[1].toInt() - 1
            } ?: mesActual,
            fechaSeleccionada?.let {
                val fechaSplit = it.split("/")
                fechaSplit[0].toInt()
            } ?: diaActual
        )

        dialogoFecha.show()
    }

    private fun convertirStringADate(fechaString: String?): Date? {
        if (fechaString.isNullOrEmpty()) {
            return null
        }

        val formato = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        try {
            return formato.parse(fechaString)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return null
    }



    private fun initComponents() {
        ettask=findViewById(R.id.ettask)
        etDescription=findViewById(R.id.etDescription)
        spinnerCategory=findViewById(R.id.spinnerCategory)
        buttonSave=findViewById(R.id.buttonSave)
        buttonBack=findViewById(R.id.buttonBack)
        textViewFechaSeleccionada = findViewById(R.id.textViewFechaSeleccionada)
        botonSeleccionarFecha = findViewById(R.id.botonSeleccionarFecha)

    }


}