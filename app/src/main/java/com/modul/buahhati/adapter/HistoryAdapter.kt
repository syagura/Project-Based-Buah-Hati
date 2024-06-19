package com.modul.buahhati.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.modul.buahhati.data.remote.response.AnalysisResultResponse
import com.modul.buahhati.databinding.RowItemHistoryBinding

class HistoryAdapter(private val onItemClick: (AnalysisResultResponse) -> Unit) : ListAdapter<AnalysisResultResponse, HistoryAdapter.HistoryViewHolder>(
    DIFF_CALLBACK
) {

    class HistoryViewHolder(
        val binding: RowItemHistoryBinding,
        val onItemClick: (AnalysisResultResponse) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(analysis: AnalysisResultResponse) {
            binding.tvTglPertumbuhan.text = analysis.data?.date
            binding.tvBeratBadanPertumbuhan.text = analysis.data?.weightAge
            binding.tvTinggiBadanPertumbuhan.text = analysis.data?.heightAge
            binding.tvLingkarKepalaPertumbuhan.text = analysis.data?.headCircumferenceAgeGender
            binding.tvGiziPertumbuhan.text = analysis.data?.weightHeight

            binding.root.setOnClickListener {
                onItemClick(analysis)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val binding = RowItemHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HistoryViewHolder(binding, onItemClick)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<AnalysisResultResponse>() {
            override fun areItemsTheSame(
                oldItem: AnalysisResultResponse,
                newItem: AnalysisResultResponse
            ): Boolean {
                return oldItem.data?.id == newItem.data?.id
            }

            override fun areContentsTheSame(
                oldItem: AnalysisResultResponse,
                newItem: AnalysisResultResponse
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}
