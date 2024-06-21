package com.modul.buahhati.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.modul.buahhati.data.remote.response.ChildRegisterResponse
import com.modul.buahhati.databinding.RowItemProfileBinding

class ProfileAdapter : ListAdapter<ChildRegisterResponse, ProfileAdapter.ProfileViewHolder>(DIFF_CALLBACK) {

    class ProfileViewHolder(val binding: RowItemProfileBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(child: ChildRegisterResponse) {
            binding.tvItemName.text = child.name
            binding.tvNamaAnak.text = "Tanggal Lahir: ${child.birthdate}"
            binding.tvGolonganDarah.text = "Golongan Darah: ${child.bloodType}"
            binding.tvBeratBadan.text = "Berat Badan: ${child.bodyWeight} kg"
            binding.tvTinggiBadan.text = "Tinggi Badan: ${child.bodyHeight} cm"
            binding.tvLingkarKepala.text = "Lingkar Kepala: ${child.headCircumference} cm"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileViewHolder {
        val binding = RowItemProfileBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProfileViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProfileViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ChildRegisterResponse>() {
            override fun areItemsTheSame(
                oldItem: ChildRegisterResponse,
                newItem: ChildRegisterResponse
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: ChildRegisterResponse,
                newItem: ChildRegisterResponse
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}
