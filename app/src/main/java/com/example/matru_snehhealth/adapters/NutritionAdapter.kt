package com.example.matru_snehhealth.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.matru_snehhealth.databinding.ItemNutritionBinding
import com.example.matru_snehhealth.models.NutritionEntry

class NutritionAdapter(private val onToggle: (NutritionEntry) -> Unit) :
    ListAdapter<NutritionEntry, NutritionAdapter.NutritionViewHolder>(NutritionDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NutritionViewHolder {
        val binding = ItemNutritionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NutritionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NutritionViewHolder, position: Int) {
        holder.bind(getItem(position), onToggle)
    }

    class NutritionViewHolder(private val binding: ItemNutritionBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(entry: NutritionEntry, onToggle: (NutritionEntry) -> Unit) {
            binding.tvFoodName.text = entry.foodName
            binding.cbCompleted.isChecked = entry.completed
            binding.root.setOnClickListener {
                onToggle(entry)
            }
            binding.cbCompleted.setOnClickListener {
                onToggle(entry)
            }
        }
    }

    class NutritionDiffCallback : DiffUtil.ItemCallback<NutritionEntry>() {
        override fun areItemsTheSame(oldItem: NutritionEntry, newItem: NutritionEntry) = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: NutritionEntry, newItem: NutritionEntry) = oldItem == newItem
    }
}
