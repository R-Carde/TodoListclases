package com.example.todolist.Kanban

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.appcompat.widget.AppCompatImageButton
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.example.todolist.R
import com.google.android.material.tabs.TabLayout

class KanbanFragment : Fragment() {
    private lateinit var viewPager: ViewPager
    private lateinit var tabLayout: TabLayout
    private lateinit var btnLeftArrow: AppCompatImageButton
    private lateinit var btnRightArrow: AppCompatImageButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.activity_kanban, container, false)

        viewPager = rootView.findViewById(R.id.viewPager)
        tabLayout = rootView.findViewById(R.id.tabLayout)
        btnLeftArrow = rootView.findViewById(R.id.btnLeftArrow)
        btnRightArrow = rootView.findViewById(R.id.btnRightArrow)

        val adapter = TaskPagerAdapter(childFragmentManager)
        viewPager.adapter = adapter
        tabLayout.setupWithViewPager(viewPager)

        btnLeftArrow.setOnClickListener {
            if (viewPager.currentItem > 0) {
                viewPager.currentItem --
            }
        }

        btnRightArrow.setOnClickListener {
            if (viewPager.currentItem < adapter.count - 1) {
                viewPager.currentItem ++
            }
        }

        return rootView
    }
}