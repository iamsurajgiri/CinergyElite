package tuna.cinergyelite.booking

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import tuna.cinergyelite.R
import tuna.cinergyelite.databinding.DateItemBinding
import tuna.cinergyelite.utils.DateUtils

class DateAdapter(
    private val onDateClick: (String) -> Unit
) : RecyclerView.Adapter<DateAdapter.DateViewHolder>() {

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

    val differ = AsyncListDiffer(this, diffUtilCallback)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DateViewHolder {
        val binding = DateItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DateViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DateViewHolder, position: Int) {
        val item = differ.currentList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    inner class DateViewHolder(private val binding: DateItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(date: String) {
            val dayDate = DateUtils.convertDateFormat(date)
            binding.day.text = dayDate.day
            binding.date.text =
                binding.root.context.getString(R.string.day_date, dayDate.month, dayDate.date)
            binding.root.setOnClickListener {
                onDateClick(date)
                binding.root.setBackgroundColor(Color.parseColor("#f2f2f2"))
            }
        }
    }
}