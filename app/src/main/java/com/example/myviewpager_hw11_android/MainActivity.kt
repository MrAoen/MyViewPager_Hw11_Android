package com.example.myviewpager_hw11_android

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val nav = findViewById<BottomNavigationView>(R.id.nav)
        val pager = findViewById<ViewPager2>(R.id.pager)
        pager.adapter = MyPagerAdapter(this)
        pager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                nav.selectedItemId = when (position) {
                    0 -> R.id.tab1
                    1 -> R.id.tab2
                    else -> throw IllegalArgumentException("Only 2 tabs")
                }
            }
        })
        nav.setOnNavigationItemSelectedListener {
            return@setOnNavigationItemSelectedListener when (it.itemId) {
                R.id.tab1 -> {
                    pager.currentItem = 0
                    true
                }
                R.id.tab2 -> {
                    pager.currentItem = 1
                    true
                }
                else -> false
            }

        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
            R.id.my_settings -> {
                startActivity(Intent(this, MySettingsActivity::class.java))
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}

class MyPagerAdapter(mainActivity: MainActivity) : FragmentStateAdapter(mainActivity) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> Tab1Fragment()
            1 -> Tab2Fragment()
            else -> throw IllegalArgumentException("strange position $position hen selecting tabs")
        }
    }
}
