package com.example.hiddengemstouristspots.adapter


import AppViewModel
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.hiddengemstouristspots.model.TouristSpot
import com.example.hiddengemstouristspots.R
import com.example.hiddengemstouristspots.RecyclerViewInterface


/**
 * Adapter to inflate the appropriate list item layout and populate the view with information
 * from the appropriate data source
 */
class DogCardAdapter(
    private val context: Context?,
    private val recyclerViewInterface: RecyclerViewInterface,
    private val viewModel: AppViewModel
): RecyclerView.Adapter<DogCardAdapter.DogCardViewHolder>() {

    // Retrieve list of data from DataSource
    private val data: List<TouristSpot> = viewModel.getCurrentSpots()
    /**
     * Initialize view elements
     */
    class DogCardViewHolder(view: View?): RecyclerView.ViewHolder(view!!) {
        // set up the view elements by finding the correct ids for each
        // component
        val image: ImageView = view!!.findViewById(R.id.spot_image)
        val name: TextView = view!!.findViewById(R.id.dog_name)
        val age: TextView = view!!.findViewById(R.id.dog_age)
        val hobby: TextView = view!!.findViewById(R.id.dog_hobby)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogCardViewHolder {

        //If the grid layout enum is chosen use the grid_list_item
        //otherwise use the vertical_horizontal_list_item
        val adapterLayout = LayoutInflater.from(parent.context)
                    .inflate(R.layout.recyclerview_item, parent, false)


        //pass in the correct inflated layout
        return DogCardViewHolder(adapterLayout)
    }

    //passing in size of dataset
    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: DogCardAdapter.DogCardViewHolder, position: Int) {

        //retrieve and set the image, text about the game type, and the
        //mains in the video game for the current position
        val resources = context?.resources
        val item = data[position]
        holder.itemView.setOnClickListener{
                    recyclerViewInterface.onItemClick(position)
        }
        holder.image.setImageResource(item.imageResourceId)
        holder.name.text = item.name
        //Pass string resource to game type and mains text
        holder.age.text = resources?.getString(R.string.dog_age, item.short_summary)
        holder.hobby.text = resources?.getString(R.string.dog_hobbies, item.rating)
    }
}

