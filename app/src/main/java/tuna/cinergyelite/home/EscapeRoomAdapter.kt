package tuna.cinergyelite.home

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import tuna.cinergyelite.databinding.EscapeRoomsItemsBinding
import tuna.core.data.model.response.EscapeRoomMovie

class EscapeRoomAdapter(
  val listener : (EscapeRoomMovie) -> Unit
) : RecyclerView.Adapter<EscapeRoomAdapter.EscapeRoomsViewHolder>() {

    private val diffUtilCallback = object
        : DiffUtil.ItemCallback<EscapeRoomMovie>() {
        override fun areItemsTheSame(
            oldItem: EscapeRoomMovie,
            newItem: EscapeRoomMovie
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: EscapeRoomMovie,
            newItem: EscapeRoomMovie
        ): Boolean {
            return oldItem == newItem
        }
    }

    val homeDiffer = AsyncListDiffer(this, diffUtilCallback)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EscapeRoomsViewHolder {
        val binding = EscapeRoomsItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EscapeRoomsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EscapeRoomsViewHolder, position: Int) {
        val item = homeDiffer.currentList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return homeDiffer.currentList.size
    }

    inner class EscapeRoomsViewHolder(private val binding: EscapeRoomsItemsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: EscapeRoomMovie) {
            Log.d("EscapeRoomAdapter", "title: ${item.title}")
            binding.title.text = item.title
            Glide.with(binding.root.context)
                .load(item.imageUrl)
                .into(binding.poster)
            binding.root.setOnClickListener {
                listener(item)
            }
        }
    }
}