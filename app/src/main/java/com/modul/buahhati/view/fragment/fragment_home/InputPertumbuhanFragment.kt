package com.modul.buahhati.view.fragment.fragment_home

import android.app.DatePickerDialog
import android.content.Intent
import android.icu.util.Calendar
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import com.modul.buahhati.R
import com.modul.buahhati.data.remote.LoginPreference
import com.modul.buahhati.databinding.FragmentInputPertumbuhanBinding
import com.modul.buahhati.data.remote.Result
import com.modul.buahhati.data.remote.dataStore
import com.modul.buahhati.view.ViewModelFactory
import com.modul.buahhati.view.view_result.ViewResultActivity

class InputPertumbuhanFragment : Fragment() {

    private lateinit var binding: FragmentInputPertumbuhanBinding
    private lateinit var viewModel: InputViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentInputPertumbuhanBinding.inflate(layoutInflater)

        setupViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentInputPertumbuhanBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.progressBar.visibility = View.GONE
        setDatePicker()
        addAnalisisAnak()
    }

    private fun setupViewModel() {
        val factory: ViewModelFactory = ViewModelFactory.getInstance(
            requireContext(), LoginPreference.getInstance(requireContext().dataStore)
        )

        viewModel = ViewModelProvider(this, factory)[InputViewModel::class.java]
    }

    private fun addAnalisisAnak() {
        binding.btnSaveTumbuh.setOnClickListener {
            val date = binding.etTglTumbuh.text.toString()
            val age = binding.etUmurTumbuh.text.toString().toIntOrNull() ?: 0
            val gender = binding.spGender.selectedItem.toString()
            val weight = binding.etBeratTumbuh.text.toString().toIntOrNull() ?: 0
            val height = binding.etTinggiTumbuh.text.toString().toIntOrNull() ?: 0
            val headCircumference = binding.etLingkarTumbuh.text.toString().toIntOrNull() ?: 0

            if (date.isEmpty() || age == 0 || gender.isEmpty() || weight == 0 || height == 0 || headCircumference == 0) {
                Toast.makeText(requireContext(), "Data tidak boleh kosong", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }

            viewModel.getChildID().observe(viewLifecycleOwner) { childID ->
                if (childID != null) {

                    viewModel.addData(
                        childID, date, age, gender, weight, height, headCircumference
                    ).observe(viewLifecycleOwner) { result ->
                        when (result) {
                            is Result.Loading -> {
                                binding.progressBar.visibility = View.VISIBLE
                            }

                            is Result.Success -> {
                                binding.progressBar.visibility = View.GONE
                                Toast.makeText(
                                    requireContext(),
                                    "Data Pertumbuhan Berhasil Disimpan",
                                    Toast.LENGTH_SHORT
                                ).show()
                                val intent =
                                    Intent(requireContext(), ViewResultActivity::class.java)
                                startActivity(intent)
                            }

                            is Result.Error -> {
                                binding.progressBar.visibility = View.GONE
                                AlertDialog.Builder(requireContext()).apply {
                                    setTitle(getString(R.string.error_message))
                                    setMessage(result.error)
                                    setPositiveButton(getString(R.string.continue_message)) { _, _ -> }
                                    create()
                                    show()
                                }
                            }

                            else -> {
                                binding.progressBar.visibility = View.GONE
                                Toast.makeText(requireContext(), "Unknown error occurred.", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                }
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
            requireContext(),
            { _, year, month, day ->
                val selectedDate = "$year-${month + 1}-$day"
                binding.etTglTumbuh.setText(selectedDate)
            }, year, month, day
        )
        datePickerDialog.show()
    }
}
