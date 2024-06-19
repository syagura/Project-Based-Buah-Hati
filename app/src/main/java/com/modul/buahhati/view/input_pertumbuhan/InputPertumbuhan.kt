package com.modul.buahhati.view.input_pertumbuhan

import android.app.DatePickerDialog
import android.content.Intent
import android.icu.util.Calendar
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.modul.buahhati.R
import com.modul.buahhati.data.remote.LoginPreference
import com.modul.buahhati.data.remote.dataStore
import com.modul.buahhati.databinding.ActivityInputPertumbuhanBinding
import com.modul.buahhati.view.ViewModelFactory
import com.modul.buahhati.data.remote.Result
import com.modul.buahhati.view.fragment.fragment_home.HomeFragment
import com.modul.buahhati.view.login.LoginActivity
import com.modul.buahhati.view.view_result.ViewResultActivity

class InputPertumbuhan : AppCompatActivity() {
    private lateinit var binding: ActivityInputPertumbuhanBinding
    private lateinit var viewModel: InputPertumbuhanViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInputPertumbuhanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.progressBar.visibility = View.GONE

        setupViewModel()
        addDataPertumbuhan()
        setDatePicker()
    }

    private fun setupViewModel() {
        val factory: ViewModelFactory = ViewModelFactory.getInstance(
            this, LoginPreference.getInstance(dataStore)
        )

        viewModel = ViewModelProvider(this, factory)[InputPertumbuhanViewModel::class.java]
    }


    private fun addDataPertumbuhan() {
        binding.btnSaveTumbuh.setOnClickListener {
            val date = binding.etTglTumbuh.text.toString()
            val age = binding.etUmurTumbuh.text.toString().toIntOrNull() ?: 0
            val gender = binding.spGender.selectedItem.toString()
            val weight = binding.etBeratTumbuh.text.toString().toIntOrNull() ?: 0
            val height = binding.etTinggiTumbuh.text.toString().toIntOrNull() ?: 0
            val headCircumference = binding.etLingkarTumbuh.text.toString().toIntOrNull() ?: 0

            if (date.isEmpty() || age == 0 || gender.isEmpty() || weight == 0 || height == 0 || headCircumference == 0) {
                Toast.makeText(this, "Data tidak boleh kosong", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }

            AlertDialog.Builder(this).apply {
                setTitle("Konfirmasi Data")
                setMessage("Pastikan semua data yang anda masukan sudah benar.")
                setPositiveButton("Lanjut") { _, _ ->
                    viewModel.getChildID().observe(this@InputPertumbuhan) { childID ->
                        if (childID != null) {
                            viewModel.addData(
                                childID, date, age, gender, weight, height, headCircumference
                            ).observe(this@InputPertumbuhan) { result ->
                                when (result) {
                                    is Result.Loading -> {
                                        binding.progressBar.visibility = View.VISIBLE
                                    }

                                    is Result.Success -> {
                                        binding.progressBar.visibility = View.GONE
                                        Toast.makeText(
                                            this@InputPertumbuhan,
                                            "Data Pertumbuhan Berhasil Disimpan",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                        val intent =
                                            Intent(this@InputPertumbuhan, ViewResultActivity::class.java)
                                        startActivity(intent)
                                    }

                                    is Result.Error -> {
                                        binding.progressBar.visibility = View.GONE
                                        AlertDialog.Builder(this@InputPertumbuhan).apply {
                                            setTitle(getString(R.string.error_message))
                                            setMessage(result.error)
                                            setPositiveButton(getString(R.string.continue_message)) { _, _ -> }
                                            create()
                                            show()
                                        }
                                    }

                                    else -> {
                                        binding.progressBar.visibility = View.GONE
                                        Toast.makeText(this@InputPertumbuhan, "Unknown error occurred.", Toast.LENGTH_SHORT)
                                            .show()
                                    }
                                }
                            }
                        } else {
                            Toast.makeText(
                                this@InputPertumbuhan,
                                "Child ID not found.",
                                Toast.LENGTH_SHORT
                            ).show()
                            startActivity(Intent(this@InputPertumbuhan, HomeFragment::class.java))
                            finish()
                        }
                    }
                }
                setNegativeButton("Kembali") { dialog, _ ->
                    dialog.dismiss()
                }
                create()
                show()
            }
        }
    }


    private fun setDatePicker() {
        binding.etTglTumbuh.setOnClickListener {
            showDatePicker()
        }
    }

    private fun showDatePicker() {
        val calendar: Calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            this,
            { _, year, month, day ->
                val selectedDate = "$year-${month + 1}-$day"
                binding.etTglTumbuh.setText(selectedDate)
            }, year, month, day
        )
        datePickerDialog.show()
    }
}


