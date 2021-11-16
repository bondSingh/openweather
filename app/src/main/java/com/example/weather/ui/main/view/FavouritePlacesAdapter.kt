package com.example.weather.ui.main.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.R

class FavouritePlacesAdapter (
    private var updatedList:List<String>,
    private val callback: OnItemClickListener
) :
    RecyclerView.Adapter<FavouritePlacesAdapter.FavViewHolder>() {

    private var favList: List<String> = updatedList

    fun setFavList(listOfPlaces: List<String>){
        updatedList = listOfPlaces
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavouritePlacesAdapter.FavViewHolder {
        return FavViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.fav_list_item, parent, false))
    }

    override fun getItemCount(): Int = favList.size

    override fun onBindViewHolder(holder: FavViewHolder, position: Int) {
        holder.textview.text = favList.elementAt(position)
        holder.cardview.setOnClickListener {
            callback.onItemClickCallback(favList.elementAt(position))
        }
    }


    inner class FavViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val textview: TextView = itemView.findViewById(R.id.fav_place)
            val cardview: CardView = itemView.findViewById(R.id.fav_item_cv)
    }




    interface OnItemClickListener {
        fun onItemClickCallback(repoListItem: String?)
    }
}