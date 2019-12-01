package pl.dyrala.szymon.placesdemo.ui.places.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import pl.dyrala.szymon.placesdemo.R
import pl.dyrala.szymon.placesdemo.repositories.models.Place

class PlacesAdapter : RecyclerView.Adapter<PlacesAdapter.ViewHolder>() {

    private val items = mutableListOf<Place>()

    var onClickAction: ((Place) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_place, parent, false)
        )

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val place = items[position]

        holder.bind(place)
    }

    fun updateList(places: List<Place>) {
        if (items.isEmpty()) {
            items.addAll(places)
            notifyDataSetChanged()
        } else {
            val difference = DiffUtil.calculateDiff(PlacesDifferenceCallback(places, items))

            items.clear()
            items.addAll(places)

            difference.dispatchUpdatesTo(this)
        }
    }

    fun getPlaceAt(position: Int): Place? {
        return if (items.size > position) {
            items[position]
        } else {
            Log.e(PlacesAdapter::javaClass.name, "Cannot retrieve place at position $position")
            null
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val name: TextView = itemView.findViewById(R.id.item_place_name)
        private val address: TextView = itemView.findViewById(R.id.item_place_address)

        fun bind(place: Place) {
            name.text = place.name
            address.text = place.address

            itemView.setOnClickListener {
                onClickAction?.invoke(place)
            }
        }
    }

    private class PlacesDifferenceCallback(
        private var newItems: List<Place>,
        private var oldItems: List<Place>
    ) : DiffUtil.Callback() {
        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            oldItems[oldItemPosition].id == newItems[newItemPosition].id

        override fun getOldListSize(): Int = oldItems.count()

        override fun getNewListSize(): Int = newItems.count()

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            oldItems[oldItemPosition] == newItems[newItemPosition]
    }
}