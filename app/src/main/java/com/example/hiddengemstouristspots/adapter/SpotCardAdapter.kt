package com.example.hiddengemstouristspots.adapter


import android.graphics.BitmapFactory
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.hiddengemstouristspots.R
import com.example.hiddengemstouristspots.RecyclerViewInterface
import com.example.hiddengemstouristspots.data.Spot

class SpotCardAdapter(private val recyclerViewInterface: RecyclerViewInterface) : ListAdapter<Spot, SpotCardAdapter.SpotViewHolder>(SPOTS_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpotViewHolder {
        return SpotViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: SpotViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current, position, recyclerViewInterface)
    }

    class SpotViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val image: ImageView = itemView.findViewById(R.id.spot_image)
        private val name: TextView = itemView.findViewById(R.id.spot_name)
        private val summary: TextView = itemView.findViewById(R.id.spot_summary)
        private val rating: TextView = itemView.findViewById(R.id.spot_rating)

        fun bind(spot: Spot?, position: Int, recyclerViewInterface: RecyclerViewInterface) {
            if(spot!!.imageResourceId > 0)
                image.setImageResource(spot.imageResourceId)
            else
                image.setImageURI(Uri.parse(spot.imageUri))
            name.text = spot.name
            summary.text = spot.short_summary
            rating.text = spot.rating
            itemView.setOnClickListener{recyclerViewInterface.onItemClick(position)}
        }

        companion object {
            fun create(parent: ViewGroup): SpotViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.recyclerview_item, parent, false)
                return SpotViewHolder(view)
            }
        }
    }

    companion object {
        private val SPOTS_COMPARATOR = object : DiffUtil.ItemCallback<Spot>() {
            override fun areItemsTheSame(oldItem: Spot, newItem: Spot): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: Spot, newItem: Spot): Boolean {
                return oldItem.name == newItem.name && oldItem.short_summary == newItem.short_summary &&
                        oldItem.rating == newItem.rating && oldItem.long_summary == newItem.long_summary &&
                        oldItem.city == newItem.city
            }
        }
    }
}
