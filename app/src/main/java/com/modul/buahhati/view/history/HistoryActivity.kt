package com.modul.buahhati.view.history

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.modul.buahhati.adapter.HistoryAdapter
import com.modul.buahhati.data.remote.LoginPreference
import com.modul.buahhati.data.remote.dataStore
import com.modul.buahhati.databinding.ActivityHistoryBinding
import com.modul.buahhati.view.ViewModelFactory
import com.modul.buahhati.data.remote.Result

class HistoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHistoryBinding
    private lateinit var viewModel: HistoryViewModel
    private lateinit var adapter: HistoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.progressBar.visibility = View.GONE

        setupViewModel()
    }

    private fun setupViewModel() {
        val factory: ViewModelFactory = ViewModelFactory.getInstance(
            this, LoginPreference.getInstance(dataStore)
        )

        viewModel = ViewModelProvider(this, factory)[HistoryViewModel::class.java]
    }

    private fun getHistory(){
        viewModel.getHistory().observe(this){result ->
            when(result){
                is Result.Loading ->{
                    binding.progressBar.visibility = View.VISIBLE
                }
                is Result.Success ->{
                    binding.progressBar.visibility = View.GONE
                    val history = result.data
                }
                is Result.Error->{
                    binding.progressBar.visibility = View.GONE
                }
            }
        }
    }
}