package com.example.mywords.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mywords.R
import com.example.mywords.db.WordyEntity

class FavoriteAdapter(val listener: FavoriteListener?):
    RecyclerView.Adapter<FavoriteAdapter.CompanyViewHolder>() {

    private var favoriList = arrayListOf<WordyEntity>()
    fun setDataFavori(favoriList: ArrayList<WordyEntity>) {
        this.favoriList = favoriList
        favoriList.reverse()
        notifyDataSetChanged()
    }

    class CompanyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameText = view.findViewById<TextView>(R.id.nameFavori)
        val delete=view.findViewById<ImageView>(R.id.deleteFavori)

        fun bindItems(item: WordyEntity) {
                nameText.text=item.name
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CompanyViewHolder {
        val binding = LayoutInflater.from(parent.context)
        val view = binding.inflate(R.layout.item_favorite, parent, false)
        return CompanyViewHolder(view)
    }

    override fun onBindViewHolder(holder: CompanyViewHolder, position: Int) {

        holder.bindItems(favoriList.get(position))
            holder.delete.setOnClickListener {
                listener?.favoriteDeleteListener(favoriList.removeAt(position))
        }
    }

    override fun getItemCount(): Int {
        return favoriList.count()
    }
    interface FavoriteListener {
        fun favoriteDeleteListener(entity: WordyEntity)

    }
}