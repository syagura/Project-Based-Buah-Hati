package com.modul.buahhati.view.fragment.fragment_home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.lifecycle.ViewModelProvider
import com.modul.buahhati.R
import com.modul.buahhati.data.remote.LoginPreference
import com.modul.buahhati.data.remote.dataStore
import com.modul.buahhati.databinding.FragmentHomeBinding
import com.modul.buahhati.di.Injection
import com.modul.buahhati.view.ViewModelFactory
import com.modul.buahhati.view.view_chart.ViewChartActivity

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var profileViewModel: ProfileViewModel
    private lateinit var loginPreference: LoginPreference
    private lateinit var pageTB: CardView
    private lateinit var pageBB: CardView
    private lateinit var tvGreeting: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        loginPreference = LoginPreference.getInstance(requireContext().dataStore)
        val factory = ViewModelFactory(Injection.provideRepository(requireContext()), loginPreference)
        profileViewModel = ViewModelProvider(this, factory).get(ProfileViewModel::class.java)

        pageTB = requireView().findViewById(R.id.btnTB)
        pageBB = requireView().findViewById(R.id.btnBB)

        pageBB.setOnClickListener{
            val viewBB = Intent(requireContext().applicationContext, ViewChartActivity::class.java)
            viewBB.putExtra(beratBadan, true)
            startActivity(viewBB)
        }

        pageTB.setOnClickListener{
            val viewTB = Intent(requireContext().applicationContext, ViewChartActivity::class.java)
            viewTB.putExtra(tinggiBadan, true)
            startActivity(viewTB)
        }

        tvGreeting = binding.greeting

        profileViewModel.getUserName().observe(viewLifecycleOwner) { userName ->
            userName?.let {
                tvGreeting.text = "Hello, $it"
            }
        }
    }

    companion object {
        const val tinggiBadan: String = "ViewTB"
        const val beratBadan: String = "ViewBB"
        const val lingkarKepala: String = "ViewLK"
    }

}