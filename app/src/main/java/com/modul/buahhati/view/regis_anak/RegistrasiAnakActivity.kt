package com.modul.buahhati.view.regis_anak

import android.app.DatePickerDialog
import android.icu.util.Calendar
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.modul.buahhati.databinding.ActivityRegistrasiAnakBinding

class RegistrasiAnakActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegistrasiAnakBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =ActivityRegistrasiAnakBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    fun showDatePicker(view: View) {
        val year : Int
        val month : Int
        val day : Int
        val calendar : Calendar = Calendar.getInstance()

        year = calendar.get(Calendar.YEAR)
        month = calendar.get(Calendar.MONTH)
        day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(this,
            { datePicker, year, month, day -> // Handle tanggal yang dipilih oleh pengguna
                val selectedDate = year.toString() + "-" + (month + 1) + "-" + day
                binding.etDateOfBirth?.setText(selectedDate)
            }, year, month, day
        )
        datePickerDialog.show()
    }
}