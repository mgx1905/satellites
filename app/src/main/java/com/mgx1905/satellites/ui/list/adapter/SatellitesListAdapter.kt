package com.mgx1905.satellites.ui.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mgx1905.satellites.R
import com.mgx1905.satellites.data.Satellite
import com.mgx1905.satellites.databinding.ItemSatellitesBinding

/**
 * Created by mgx1905 on 27.12.2021
 */

class SatellitesListAdapter(private val onRowClick: (item: Satellite) -> Unit) :
    ListAdapter<Satellite, SatellitesListAdapter.SatellitesListViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Satellite>() {
            override fun areItemsTheSame(oldItem: Satellite, newItem: Satellite): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Satellite, newItem: Satellite): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SatellitesListViewHolder {
        val binding = ItemSatellitesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SatellitesListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SatellitesListViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class SatellitesListViewHolder(private val binding: ItemSatellitesBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                if (bindingAdapterPosition != RecyclerView.NO_POSITION) {
                    onRowClick.invoke(getItem(bindingAdapterPosition))
                }
            }
        }

        fun bind(item: Satellite) {
            binding.tvSatellite.text = item.name
            if (item.active) {
                binding.tvStatus.text = "Active"
                binding.ivStatus.setBackgroundResource(R.drawable.ic_status_active)
            } else {
                binding.tvStatus.text = "Passive"
                binding.ivStatus.setBackgroundResource(R.drawable.ic_status_passive)
            }
        }
    }
}