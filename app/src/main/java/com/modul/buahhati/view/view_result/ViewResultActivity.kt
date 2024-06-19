package com.modul.buahhati.view.view_result

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.modul.buahhati.data.remote.LoginPreference
import com.modul.buahhati.data.remote.dataStore
import com.modul.buahhati.databinding.ActivityViewResultBinding
import com.modul.buahhati.view.ViewModelFactory
import com.modul.buahhati.data.remote.Result

class ViewResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityViewResultBinding
    private lateinit var viewModel: ResultViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.progressBar.visibility = View.GONE

        setupViewModel()

        val analysisId = intent.getStringExtra("ANALYSIS_ID")
        if (analysisId != null) {
            showResult(analysisId)
        }
    }

    private fun setupViewModel() {
        val factory: ViewModelFactory = ViewModelFactory.getInstance(
            this, LoginPreference.getInstance(dataStore)
        )

        viewModel = ViewModelProvider(this, factory)[ResultViewModel::class.java]
    }

    private fun showResult(analysis_id: String) {
        viewModel.getAnalysis(analysis_id).observe(this) { result ->
            when (result) {
                is Result.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is Result.Success -> {
                    binding.progressBar.visibility = View.GONE
                    val analysisData = result.data?.data
                    if (analysisData != null) {
                        binding.tvLingkarKepalaStatus.text = "Lingkar Kepala : ${analysisData.headCircumferenceAgeGender}"
                        binding.tvBeratBadanStatus.text = "Berat Badan : ${analysisData.weightAge}"
                        binding.tvTinggiBadanStatus.text = "Tinggi Badan : ${analysisData.heightAge}"
                        binding.tvGiziStatus.text = "Gizi : ${analysisData.weightHeight}"
                    }
                }
                is Result.Error -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(this, result.error, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
