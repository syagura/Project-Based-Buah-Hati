package com.modul.buahhati.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.modul.buahhati.data.remote.response.ChildRegisterResponse
import com.modul.buahhati.databinding.RowItemProfileBinding

class ProfileAdapter : ListAdapter<ChildRegisterResponse, ProfileAdapter.profieViewHolder>(DIFF_CALLBACK){

    class profieViewHolder(val binding: RowItemProfileBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(child : ChildRegisterResponse){
            binding.tvItemName.text = child.name
            binding.tvNamaAnak.text = child.birthdate //ini tanggal lahir anak
            binding.tvGolonganDarah.text = child.bloodType
            binding.tvBeratBadan.text = child.bodyWeight.toString()
            binding.tvTinggiBadan.text = child.bodyHeight.toString()
            binding.tvLingkarKepala.text = child.headCircumference.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): profieViewHolder {
        val binding = RowItemProfileBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return profieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: profieViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }

    companion object{
        val DIFF_CALLBACK = object: DiffUtil.ItemCallback<ChildRegisterResponse>(){
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