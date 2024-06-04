package com.modul.buahhati.view.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.modul.buahhati.R
import com.modul.buahhati.databinding.ActivityMainBinding
import com.modul.buahhati.view.fragment.fragment_home.HomeFragment
import com.modul.buahhati.view.fragment.fragment_home.InputPertumbuhanFragment
import com.modul.buahhati.view.fragment.fragment_home.ProfileFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        replaceFragment(HomeFragment())

        binding.bottomNavigation.setOnClickListener{
            when(it.id){
                R.id.menu_1 -> replaceFragment(HomeFragment())
                R.id.menu_2 -> replaceFragment(InputPertumbuhanFragment())
                R.id.menu_3 -> replaceFragment(ProfileFragment())
                else -> {

                }
            }
            true
        }

        findViewById<BottomNavigationView>(R.id.bottom_navigation).itemActiveIndicatorColor = getColorStateList(R.color.pink)
    }

    private fun replaceFragment(fragment: Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.layout_frame, fragment)
        fragmentTransaction.commit()
    }
}