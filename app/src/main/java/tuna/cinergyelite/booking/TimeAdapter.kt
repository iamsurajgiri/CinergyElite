package tuna.cinergyelite.booking

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import tuna.cinergyelite.databinding.TimeItemBinding
import tuna.core.data.model.response.Session

class TimeAdapter : RecyclerView.Adapter<TimeAdapter.TimeViewHolder>() {

    private val diffUtilCallback = object
        : DiffUtil.ItemCallback<Session>() {
        override fun areItemsTheSame(
            oldItem: Session,
            newItem: Session
        ): Boolean {
            return oldItem.sessionId == newItem.sessionId
        }

        override fun areContentsTheSame(
            oldItem: Session,
            newItem: Session
        ): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, diffUtilCallback)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimeViewHolder {
        val binding = TimeItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TimeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TimeViewHolder, position: Int) {
        val item = differ.currentList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    inner class TimeViewHolder(private val binding: TimeItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Session) {
            binding.time.text = item.showtime
        }
    }
}