package com.modul.buahhati.view.regis_anak

import android.app.Activity
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
import com.modul.buahhati.databinding.ActivityRegistrasiAnakBinding
import com.modul.buahhati.view.ViewModelFactory
import com.modul.buahhati.data.remote.Result
import com.modul.buahhati.view.login.LoginActivity

class RegistrasiAnakActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegistrasiAnakBinding
    private lateinit var preference: LoginPreference
    private lateinit var viewModel: RegisAnakViewModel
    private lateinit var sharedViewModel: SharedViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrasiAnakBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        binding.progressBar.visibility = View.GONE

        setupViewModel()
        registrasiAnak()
    }

    private fun setupViewModel() {
        val factory: ViewModelFactory = ViewModelFactory.getInstance(
            this, LoginPreference.getInstance(dataStore)
        )

        viewModel = ViewModelProvider(this, factory)[RegisAnakViewModel::class.java]
        sharedViewModel = ViewModelProvider(this, factory).get(SharedViewModel::class.java)
    }

    fun showDatePicker(view: View) {
        val calendar: Calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            this,
            { _, year, month, day ->
                val selectedDate = "$year-${month + 1}-$day"
                binding.etDateOfBirth.setText(selectedDate)
            }, year, month, day
        )
        datePickerDialog.show()
    }

    fun registrasiAnak() {
        binding.btnRegisterAnak.setOnClickListener {
            val name = binding.etFullName.text.toString()
            val birthDate = binding.etDateOfBirth.text.toString().trim()
            val gender = when (binding.rgGender.checkedRadioButtonId) {
                R.id.rbMale -> "Laki-laki"
                R.id.rbFemale -> "Perempuan"
                else -> ""
            }
            val bloodType = binding.spAdditionalProfile.selectedItem.toString()
            val bodyWeight = binding.etWeight.text.toString().toIntOrNull() ?: 0
            val bodyHeight = binding.etHeight.text.toString().toIntOrNull() ?: 0
            val headCircumference = binding.etHeadCircumference.text.toString().toIntOrNull() ?: 0

            // Validasi input sebelum mengirimkan ke ViewModel
            if (name.isEmpty() || birthDate.isEmpty() || gender.isEmpty() || bloodType == "Pilih Golongan Darah") {
                Toast.makeText(this, "Harap isi semua kolom.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            viewModel.getUserId().observe(this) { userId ->
                if (userId != null) {
                    viewModel.regisAnak(
                        userId, name, birthDate, gender, bloodType, bodyWeight, bodyHeight, headCircumference
                    ).observe(this) { result ->
                        when (result) {
                            is Result.Loading -> {
                                binding.progressBar.visibility = View.VISIBLE
                            }
                            is Result.Success -> {
                                binding.progressBar.visibility = View.GONE
                                val returnIntent = Intent()
                                returnIntent.putExtra("isUpdated", true)
                                setResult(Activity.RESULT_OK, returnIntent)
                                finish()
                            }
                            is Result.Error -> {
                                binding.progressBar.visibility = View.GONE
                                AlertDialog.Builder(this).apply {
                                    setTitle(getString(R.string.error_message))
                                    setMessage(result.error)
                                    setPositiveButton(getString(R.string.continue_message)) { _, _ -> }
                                    create()
                                    show()
                                }
                            }
                            else -> {
                                binding.progressBar.visibility = View.GONE
                                Toast.makeText(this, "Unknown error occurred.", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                } else {
                    Toast.makeText(this, "User ID not found. Please login again.", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, LoginActivity::class.java))
                    finish()
                }
            }
        }
    }
}
