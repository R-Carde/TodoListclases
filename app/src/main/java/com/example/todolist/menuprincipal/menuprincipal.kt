package com.example.todolist.menuprincipal

import ToDoFragment
import android.content.Intent

import android.os.Bundle

import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels

import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.todolist.Kanban.KanbanFragment


import com.example.todolist.MainActivity
import com.example.todolist.R
import com.example.todolist.Tasks.TaskActivity

import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import androidx.appcompat.app.AppCompatActivity
import com.example.todolist.mostrardatos.MostrarDatosActivity


class menuprincipal : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var drawer: DrawerLayout
    private lateinit var toogle:ActionBarDrawerToggle
    private lateinit var  btn_add: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menuprincipal)

        InitComponents()
        InitListeners()
        // Cargar el fragmento en el contenedor
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()

        // Reemplazar el contenido actual en el contenedor con el nuevo Fragment
        fragmentTransaction.replace(R.id.container, KanbanFragment())

        // Añadir la transacción al back stack
        fragmentTransaction.addToBackStack(null)

        // Commit de la transacción
        fragmentTransaction.commit()
        actualizarFragmento()

        val toolbar:androidx.appcompat.widget.Toolbar = findViewById(R.id.toolbar_main)
        setSupportActionBar(toolbar)
        drawer = findViewById(R.id.drawer_layout)

        toogle = ActionBarDrawerToggle(this,drawer,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close)
        drawer.addDrawerListener(toogle)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        val navView:NavigationView = findViewById(R.id.nav_view)
        navView.setNavigationItemSelectedListener(this)
    }
    fun actualizarFragmento() {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()

        // Crear una nueva instancia del fragmento (puedes necesitar adaptar esto según tu lógica)
        val nuevoFragmento = KanbanFragment()

        // Reemplazar el contenido actual en el contenedor con el nuevo Fragment
        fragmentTransaction.replace(R.id.container, KanbanFragment())

        // Añadir la transacción al back stack
        fragmentTransaction.addToBackStack(null)

        // Commit de la transacción
        fragmentTransaction.commit()
    }
    private fun InitListeners() {
        btn_add.setOnClickListener {
            val intent = Intent(this, TaskActivity::class.java)
            startActivity(intent)

        }

    }

    private fun InitComponents() {
        btn_add=findViewById(R.id.btn_add)

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
       when(item.itemId) {
           R.id.nav_item_Logout -> {
               val intent = Intent(this, MainActivity::class.java)
               startActivity(intent)
               finish()
           }
           R.id.nav_item_one -> {
               Toast.makeText(this, "Proximamente ", Toast.LENGTH_SHORT).show()
           }
           R.id.nav_item_two -> {
               Toast.makeText(this, "Proximamente ", Toast.LENGTH_SHORT).show()
           }

       }
        drawer.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        toogle.syncState()
    }

    override fun onConfigurationChanged(newConfig: android.content.res.Configuration) {
        super.onConfigurationChanged(newConfig)
        toogle.onConfigurationChanged(newConfig)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toogle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }


}