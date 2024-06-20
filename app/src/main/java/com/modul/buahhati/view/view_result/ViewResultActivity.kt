package com.modul.buahhati.view.view_result

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import com.modul.buahhati.R
import com.modul.buahhati.data.remote.LoginPreference
import com.modul.buahhati.data.remote.dataStore
import com.modul.buahhati.databinding.ActivityViewResultBinding
import com.modul.buahhati.view.ViewModelFactory
import com.modul.buahhati.data.remote.Result
import com.modul.buahhati.view.fragment.fragment_home.HomeFragment
import com.modul.buahhati.view.main.MainActivity

class ViewResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityViewResultBinding
    private lateinit var viewModel: ResultViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.progressBar.visibility = View.GONE

        setupViewModel()

        val childId = intent.getStringExtra("CHILD_ID")
        val analysisId = intent.getStringExtra("ANALYSIS_ID")

        if (childId != null && analysisId != null) {
            showResult(childId, analysisId)
        }

        binding.btnRegisterAnak.setOnClickListener {
            navigateToMainActivity()
        }
    }

    private fun setupViewModel() {
        val factory: ViewModelFactory = ViewModelFactory.getInstance(
            this, LoginPreference.getInstance(dataStore)
        )

        viewModel = ViewModelProvider(this, factory)[ResultViewModel::class.java]
    }

    private fun showResult(child_id: String ,analysis_id: String) {
        viewModel.getAnalysis(child_id, analysis_id).observe(this) { result ->
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
                        binding.tvRekomendasiDetail.text = "${analysisData.recomendation}"
                    }
                }
                is Result.Error -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(this, result.error, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun navigateToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
        finish()
    }
}
