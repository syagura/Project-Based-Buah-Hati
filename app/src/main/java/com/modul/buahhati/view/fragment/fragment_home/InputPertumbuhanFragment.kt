package com.modul.buahhati.view.fragment.fragment_home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import com.modul.buahhati.R
import com.modul.buahhati.databinding.FragmentInputPertumbuhanBinding

class InputPertumbuhanFragment : Fragment() {

    private lateinit var binding: FragmentInputPertumbuhanBinding
    private lateinit var btnSaveTumbuh: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_input_pertumbuhan, container, false)
    }

    private fun buttonAddData(){
        btnSaveTumbuh = binding.root.findViewById(R.id.btnSave_tumbuh)
        btnSaveTumbuh.setOnClickListener {
            // Handle button click to replace fragment

        }

    }

}