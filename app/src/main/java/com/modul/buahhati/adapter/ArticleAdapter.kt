package com.modul.buahhati.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.modul.buahhati.data.remote.response.DataItem
import com.modul.buahhati.databinding.RowItemInformasiBinding

class ArticleAdapter(
    private var articles: List<DataItem?>,
    private val listener: (DataItem?) -> Unit
) : RecyclerView.Adapter<ArticleAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            RowItemInformasiBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(articles[position], listener)
    }

    override fun getItemCount(): Int = articles.size

    fun updateData(newArticles: List<DataItem?>) {
        articles = newArticles
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: RowItemInformasiBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(article: DataItem?, listener: (DataItem?) -> Unit) {
            binding.tvInformasi.text = article?.title
            binding.root.setOnClickListener { listener(article) }
        }
    }
}
