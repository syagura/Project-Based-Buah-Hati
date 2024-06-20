package com.modul.buahhati.view.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.modul.buahhati.R
import com.modul.buahhati.databinding.ActivityMainBinding
import com.modul.buahhati.view.fragment.fragment_home.HomeFragment
import com.modul.buahhati.view.fragment.fragment_home.ProfileFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        replaceFragment(HomeFragment(), "HomeFragment")

        binding.bottomNavigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu_1 -> {
                    replaceFragment(HomeFragment(), "HomeFragment")
                    true
                }
                R.id.menu_3 -> {
                    replaceFragment(ProfileFragment(), "ProfileFragment")
                    true
                }
                else -> false
            }
        }

        supportFragmentManager.addOnBackStackChangedListener {
            val fragment = supportFragmentManager.findFragmentById(R.id.layout_frame)
            if (fragment is ProfileFragment) {
                supportActionBar?.show()
            } else {
                supportActionBar?.hide()
            }
        }

        findViewById<BottomNavigationView>(R.id.bottom_navigation).itemActiveIndicatorColor = getColorStateList(R.color.pink)
    }

    private fun replaceFragment(fragment: Fragment, tag: String) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.layout_frame, fragment, tag)
        fragmentTransaction.addToBackStack(tag)
        fragmentTransaction.commit()
    }
}