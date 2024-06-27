package com.example.todolist.Kanban

import ToDoFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class TaskPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm){
    //devuelve el fragmento correspondiente a la posicion
    override fun getItem(position:Int): Fragment{
        return when(position){
            0->ToDoFragment()
            1->InProgressFragment()
            2->CompletedFragment()
            else->ToDoFragment()
        }
    }
    override fun getCount(): Int{
        //devuelve el numero total de categorias
        return 3
    }
    override fun getPageTitle(position: Int): CharSequence? {
        //devuelve el titulo de la categoria
        return when(position){
            0-> "Por hacer"
            1-> "En curso"
            2-> "Finzalizado"
            else-> ""
        }
    }
}