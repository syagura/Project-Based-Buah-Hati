package com.modul.buahhati.view.history

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.modul.buahhati.adapter.HistoryAdapter
import com.modul.buahhati.data.remote.LoginPreference
import com.modul.buahhati.data.remote.dataStore
import com.modul.buahhati.databinding.ActivityHistoryBinding
import com.modul.buahhati.view.ViewModelFactory
import com.modul.buahhati.data.remote.Result
import com.modul.buahhati.view.detail_history.DetailHistoryActivity

class HistoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHistoryBinding
    private lateinit var viewModel: HistoryViewModel
    private lateinit var adapter: HistoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViewModel()
        setupRecyclerView()
        getHistory()
    }

    private fun setupViewModel() {
        val factory: ViewModelFactory = ViewModelFactory.getInstance(
            this, LoginPreference.getInstance(dataStore)
        )

        viewModel = ViewModelProvider(this, factory)[HistoryViewModel::class.java]
    }

    private fun setupRecyclerView() {
        adapter = HistoryAdapter { analysis ->
            val intent = Intent(this, DetailHistoryActivity::class.java).apply {
                putExtra(DetailHistoryActivity.EXTRA_ANALYSIS_ID, analysis.data?.analysisId)
            }
            startActivity(intent)
        }

        binding.rvHistory.apply {
            layoutManager = LinearLayoutManager(this@HistoryActivity)
            adapter = this@HistoryActivity.adapter
        }
    }

    private fun getHistory() {
        viewModel.getHistory().observe(this) { result ->
            when (result) {
                is Result.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is Result.Success -> {
                    binding.progressBar.visibility = View.GONE
                    val data = result.data
                    Log.d("HistoryActivity", "Data received: $data") // Tambahkan log
                    adapter.submitList(data)
                }
                is Result.Error -> {
                    binding.progressBar.visibility = View.GONE
                    Log.e("HistoryActivity", "Error: ${result.error}") // Tambahkan log
                }
            }
        }
    }
}

