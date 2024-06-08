package com.modul.buahhati.view.regis_anak

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
import com.modul.buahhati.view.main.MainActivity

class RegistrasiAnakActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegistrasiAnakBinding
    private lateinit var preference: LoginPreference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrasiAnakBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.progressBar.visibility = View.GONE

        registrasiAnak()
    }

    fun showDatePicker(view: View) {
        val year: Int
        val month: Int
        val day: Int
        val calendar: Calendar = Calendar.getInstance()

        year = calendar.get(Calendar.YEAR)
        month = calendar.get(Calendar.MONTH)
        day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            this,
            { datePicker, year, month, day ->
                val selectedDate = year.toString() + "-" + (month + 1) + "-" + day
                binding.etDateOfBirth?.setText(selectedDate)
            }, year, month, day
        )
        datePickerDialog.show()
    }

    fun registrasiAnak() {
        val factory: ViewModelFactory = ViewModelFactory.getInstance(
            this, LoginPreference.getInstance(dataStore)
        )

        val viewModel: RegisAnakViewModel =
            ViewModelProvider(this, factory)[RegisAnakViewModel::class.java]

        binding.btnRegisterAnak.setOnClickListener {
        val name = binding.etFullName.text.toString()
        val birthDate = binding.etDateOfBirth.text.toString().trim()
        val gender = when (binding.rgGender.checkedRadioButtonId) {
            R.id.rbMale -> "Laki-laki"
            R.id.rbFemale -> "Perempuan"
            else -> ""
        }
        val blood_type = binding.spAdditionalProfile.selectedItem.toString()
        val body_weight = binding.etWeight.text.toString().toIntOrNull() ?: 0
        val body_height = binding.etHeight.text.toString().toIntOrNull() ?: 0
        val head_circumference = binding.etHeadCircumference.text.toString().toIntOrNull() ?: 0


//            // Validasi input sebelum mengirimkan ke ViewModel
//            if (name.isEmpty() || birthDate.isEmpty() || gender.isEmpty() || blood_type == "Pilih Golongan Darah") {
//                Toast.makeText(this, "Harap isi semua kolom.", Toast.LENGTH_SHORT).show()
//                return@setOnClickListener
//            }
            viewModel.getUserId().observe(this) { userId ->
                if (userId != null) {
                    viewModel.regisAnak(
                        userId, name, birthDate, gender, blood_type, body_weight, body_height, head_circumference
                    ).observe(this) { result ->
                        when (result) {
                            is Result.Loading -> {
                                binding.progressBar.visibility = View.VISIBLE
                            }
                            is Result.Success -> {
                                binding.progressBar.visibility = View.GONE
                                val intent = Intent(this, MainActivity::class.java)
                                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                                startActivity(intent)
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
                        }
                    }
                } else {
                    // Handle error: user_id not found
                    Toast.makeText(this, "User ID not found. Please login again.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}