package com.modul.buahhati.view.fragment.fragment_home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import com.modul.buahhati.R
import com.modul.buahhati.databinding.FragmentHomeBinding
import com.modul.buahhati.view.view_chart.ViewChartActivity

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    private lateinit var pageTB: CardView
    private lateinit var pageBB: CardView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
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
    }

    companion object {
        const val tinggiBadan: String = "ViewTB"
        const val beratBadan: String = "ViewBB"
        const val lingkarKepala: String = "ViewLK"
    }

}