package tuna.cinergyelite.login

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import tuna.cinergyelite.databinding.DescriptionItemsBinding

class DescriptionAdapter : RecyclerView.Adapter<DescriptionAdapter.DescriptionViewHolder>() {

    private val diffUtilCallback = object
        : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(
            oldItem: String,
            newItem: String
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: String,
            newItem: String
        ): Boolean {
            return oldItem == newItem
        }
    }

    val descriptionDiffer = AsyncListDiffer(this, diffUtilCallback)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DescriptionViewHolder {
        val binding = DescriptionItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DescriptionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DescriptionViewHolder, position: Int) {
        val description = descriptionDiffer.currentList[position]
        holder.bind(description)
    }

    override fun getItemCount(): Int {
        return descriptionDiffer.currentList.size
    }

    inner class DescriptionViewHolder(private val binding: DescriptionItemsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(description: String) {
            binding.description.text = description
        }
    }
}