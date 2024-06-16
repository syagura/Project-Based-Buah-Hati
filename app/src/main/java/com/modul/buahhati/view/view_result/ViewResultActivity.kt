package com.modul.buahhati.view.view_result

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.modul.buahhati.databinding.ActivityViewResultBinding

class ViewResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityViewResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewResultBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }


}