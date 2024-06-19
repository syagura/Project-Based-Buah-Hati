package com.modul.buahhati.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.modul.buahhati.data.remote.response.AnalysisResultResponse
import com.modul.buahhati.databinding.RowItemHistoryBinding

class HistoryAdapter : ListAdapter<AnalysisResultResponse, HistoryAdapter.historyViewHolder>(
    HistoryAdapter.DIFF_CALLBACK
) {

    class historyViewHolder(
        val binding: RowItemHistoryBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(analysis: AnalysisResultResponse) {
            binding.tvTglPertumbuhan.text = analysis.data?.date
            binding.tvBeratBadanPertumbuhan.text = analysis.data?.weightAge
            binding.tvTinggiBadanPertumbuhan.text = analysis.data?.heightAge
            binding.tvLingkarKepalaPertumbuhan.text = analysis.data?.headCircumferenceAgeGender
            binding.tvGiziPertumbuhan.text = analysis.data?.weightHeight
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): historyViewHolder {
        val binding =
            RowItemHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return historyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: historyViewHolder, position: Int) {
        val current_item = getItem(position)
        holder.bind(current_item)
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<AnalysisResultResponse>() {
            override fun areItemsTheSame(
                oldItem: AnalysisResultResponse,
                newItem: AnalysisResultResponse
            ): Boolean {
                return oldItem == newItem
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