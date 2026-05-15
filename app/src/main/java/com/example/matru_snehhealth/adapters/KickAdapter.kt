package com.example.matru_snehhealth.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.matru_snehhealth.databinding.ItemKickBinding
import com.example.matru_snehhealth.models.KickLog
import java.text.SimpleDateFormat
import java.util.*

class KickAdapter : ListAdapter<KickLog, KickAdapter.KickViewHolder>(KickDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KickViewHolder {
        val binding = ItemKickBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return KickViewHolder(binding)
    }

    override fun onBindViewHolder(holder: KickViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class KickViewHolder(private val binding: ItemKickBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(kickLog: KickLog) {
            val sdf = SimpleDateFormat("hh:mm:ss a", Locale.getDefault())
            binding.tvKickTime.text = sdf.format(Date(kickLog.timestamp))
        }
    }

    class KickDiffCallback : DiffUtil.ItemCallback<KickLog>() {
        override fun areItemsTheSame(oldItem: KickLog, newItem: KickLog) = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: KickLog, newItem: KickLog) = oldItem == newItem
    }
}
