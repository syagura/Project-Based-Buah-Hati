package com.modul.buahhati.view.fragment.fragment_home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.modul.buahhati.R
import com.modul.buahhati.adapter.ArticleAdapter
import com.modul.buahhati.data.remote.LoginPreference
import com.modul.buahhati.data.remote.dataStore
import com.modul.buahhati.data.remote.Result
import com.modul.buahhati.databinding.FragmentHomeBinding
import com.modul.buahhati.di.Injection
import com.modul.buahhati.view.ViewModelFactory
import com.modul.buahhati.view.article.DetailArticleActivity
import com.modul.buahhati.view.view_chart.ViewChartActivity

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var profileViewModel: ProfileViewModel
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var loginPreference: LoginPreference
    private lateinit var pageTB: CardView
    private lateinit var pageBB: CardView
    private lateinit var tvGreeting: TextView
    private lateinit var adapter: ArticleAdapter

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
        homeViewModel = ViewModelProvider(this, factory).get(HomeViewModel::class.java)

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

        loginPreference.getUserName().asLiveData().observe(viewLifecycleOwner) { userName ->
            tvGreeting.text = "Hello, $userName!"
        }

        // Initialize RecyclerView
        adapter = ArticleAdapter(listOf()) { article ->
            article?.let {
                val intent = Intent(requireContext(), DetailArticleActivity::class.java)
                intent.putExtra("article_id", it.articleId)
                startActivity(intent)
            }
        }

        binding.recyclerViewInformasi.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerViewInformasi.adapter = adapter

        homeViewModel.getArticles().observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is Result.Loading -> {
                    // Show loading
                }
                is Result.Success -> {
                    val articles = result.data
                    Log.d("HomeFragment", "Articles received: $articles")
                    adapter.updateData(articles)
                }
                is Result.Error -> {
                    Log.e("HomeFragment", "Error: ${result.error}")
                }
            }
        })
    }

    companion object {
        const val tinggiBadan: String = "ViewTB"
        const val beratBadan: String = "ViewBB"
        const val lingkarKepala: String = "ViewLK"
    }

}
