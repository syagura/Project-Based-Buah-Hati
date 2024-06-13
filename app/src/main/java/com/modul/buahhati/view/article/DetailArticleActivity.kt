package com.modul.buahhati.view.article

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.modul.buahhati.R
import com.modul.buahhati.data.remote.Result
import com.modul.buahhati.databinding.ActivityDetailArticleBinding
import com.modul.buahhati.di.Injection
import com.modul.buahhati.view.ViewModelFactory

class DetailArticleActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailArticleBinding
    private lateinit var viewModel: DetailArticleViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailArticleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        enableEdgeToEdge()

        val articleId = intent.getStringExtra("article_id") ?: return

        // Get LoginPreference
        val loginPreference = Injection.provideLoginPreference(this)

        val factory = ViewModelFactory.getInstance(this, loginPreference)
        viewModel = ViewModelProvider(this, factory).get(DetailArticleViewModel::class.java)

        viewModel.getArticleById(articleId).observe(this, Observer { result ->
            when (result) {
                is Result.Loading -> {
                    // Show loading
                }
                is Result.Success -> {
                    val article = result.data
                    binding.tvDetailTitle.text = article.title
                    binding.tvDetailDesc.text = article.desc
                }
                is Result.Error -> {
                    // Show error message
                }
            }
        })

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}
