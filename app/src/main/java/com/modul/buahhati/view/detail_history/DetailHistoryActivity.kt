package com.modul.buahhati.view.detail_history

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.modul.buahhati.data.remote.LoginPreference
import com.modul.buahhati.data.remote.dataStore
import com.modul.buahhati.databinding.ActivityDetailHistoryBinding
import com.modul.buahhati.view.ViewModelFactory
import com.modul.buahhati.data.remote.Result

class DetailHistoryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailHistoryBinding
    private lateinit var viewModel: DetailHistoryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val analysisId = intent.getStringExtra(EXTRA_ANALYSIS_ID)
        if (analysisId != null) {
            setupViewModel()
            getDetailHistory(analysisId)
        }
    }

    private fun setupViewModel() {
        val factory: ViewModelFactory = ViewModelFactory.getInstance(
            this, LoginPreference.getInstance(dataStore)
        )

        viewModel = ViewModelProvider(this, factory)[DetailHistoryViewModel::class.java]
    }

    private fun getDetailHistory(analysisId: String) {
        viewModel.getAnalysisDetail(analysisId).observe(this) { result ->
            when (result) {
                is Result.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is Result.Success -> {
                    binding.progressBar.visibility = View.GONE
                    val analysis = result.data.data
                    binding.tvTglPertumbuhan.text = analysis?.date
                    binding.tvBeratBadanPertumbuhan.text = analysis?.weightAge
                    binding.tvTinggiBadanPertumbuhan.text = analysis?.heightAge
                    binding.tvLingkarKepalaPertumbuhan.text = analysis?.headCircumferenceAgeGender
                    binding.tvGiziPertumbuhan.text = analysis?.weightHeight
                    binding.tvRekomendasi.text = analysis?.recomendation
                }
                is Result.Error -> {
                    binding.progressBar.visibility = View.GONE
                }
            }
        }
    }

    companion object {
        const val EXTRA_ANALYSIS_ID = "extra_analysis_id"
    }
}