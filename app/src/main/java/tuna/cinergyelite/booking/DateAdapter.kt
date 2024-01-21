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
    private val onDateClick: (DateItem) -> Unit
) : RecyclerView.Adapter<DateAdapter.DateViewHolder>() {

    private val diffUtilCallback = object
        : DiffUtil.ItemCallback<DateItem>() {
        override fun areItemsTheSame(
            oldItem: DateItem,
            newItem: DateItem
        ): Boolean {
            return oldItem.date == newItem.date
        }

        override fun areContentsTheSame(
            oldItem: DateItem,
            newItem: DateItem
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

        fun bind(item: DateItem) {
            val dayDate = DateUtils.convertDateFormat(item.date)
            if (item.isSelected) {
                binding.parent.setBackgroundColor(Color.parseColor("#ffffff"))
            }else{
                binding.parent.setBackgroundColor(Color.parseColor("#D1D1D1"))
            }
            binding.day.text = dayDate.day
            binding.date.text =
                binding.root.context.getString(R.string.day_date, dayDate.month, dayDate.date)
            binding.root.setOnClickListener {
                onDateClick(item)
            }
        }
    }
}