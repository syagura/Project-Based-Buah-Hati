package com.modul.buahhati.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.modul.buahhati.data.remote.response.ResultData
import com.modul.buahhati.databinding.RowItemHistoryBinding

class HistoryAdapter(private val onItemClick: (ResultData) -> Unit) :
    ListAdapter<ResultData, HistoryAdapter.HistoryViewHolder>(
        DIFF_CALLBACK
    ) {

    class HistoryViewHolder(
        val binding: RowItemHistoryBinding,
        val onItemClick: (ResultData) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(analysis: ResultData) {
            binding.tvTglPertumbuhan.text = "Tanggal Pertumbuhan : ${analysis.date}"
            binding.tvBeratBadanPertumbuhan.text = "Analisis Berat Badan : ${analysis.weightAge}"
            binding.tvTinggiBadanPertumbuhan.text = "Analisis Tinggi Badan : ${analysis.heightAge}"
            binding.tvLingkarKepalaPertumbuhan.text =
                "Analisis Lingkar Kepala : ${analysis.headCircumferenceAgeGender}"
            binding.tvGiziPertumbuhan.text = "Analisis Gizi : ${analysis.weightHeight}"
            binding.tvHasilAnalisis.text = "Rekomendasi : ${analysis.recomendation}"

            binding.root.setOnClickListener {
                onItemClick(analysis)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val binding =
            RowItemHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HistoryViewHolder(binding, onItemClick)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ResultData>() {
            override fun areItemsTheSame(
                oldItem: ResultData,
                newItem: ResultData
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: ResultData,
                newItem: ResultData
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}
