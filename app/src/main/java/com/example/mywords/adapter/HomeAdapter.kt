package com.example.mywords.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.ToggleButton
import androidx.recyclerview.widget.RecyclerView
import com.example.mywords.R
import com.example.mywords.db.WordyEntity


class HomeAdapter(val listener: HomeAdapterListener?) :
    RecyclerView.Adapter<HomeAdapter.CompanyViewHolder>() {

    private var items = arrayListOf<WordyEntity>()

    fun setListData(items: List<WordyEntity>) {
        this.items = ArrayList(items)
        notifyDataSetChanged()
    }

    class CompanyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameText = view.findViewById<TextView>(R.id.nameHome)
        val toggle = view.findViewById<ToggleButton>(R.id.toggleButton)
        val delete = view.findViewById<ImageView>(R.id.deleteHome)
        val shareButton = view.findViewById<ImageView>(R.id.share)
        val copyButton = view.findViewById<ImageView>(R.id.copy)

        fun bindItems(item: WordyEntity) {
            nameText.text = item.name
            toggle.isChecked = item.isLiked
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CompanyViewHolder {
        val binding = LayoutInflater.from(parent.context)
        val view = binding.inflate(R.layout.item_home, parent, false)
        return CompanyViewHolder(view)
    }

    override fun onBindViewHolder(holder: CompanyViewHolder, position: Int) {
        holder.bindItems(items.get(position))
        holder.toggle.setOnClickListener {
            if (holder.toggle.isChecked) {
                listener?.addFavorite(items.get(position))
                notifyDataSetChanged()
            }
        }
        holder.delete.setOnClickListener {
            listener?.deleteFavorite(items.removeAt(position),position)
        }
        holder.shareButton.setOnClickListener {
            listener?.shareButton(holder.nameText.getText().toString())
        }

        holder.copyButton.setOnClickListener {
            listener?.copyToClipboard(holder.nameText.getText())
        }
    }

    override fun getItemCount(): Int {
        return items.count()
    }

    interface HomeAdapterListener {
        fun addFavorite(entity: WordyEntity)
        fun deleteFavorite(entity: WordyEntity,position: Int)
        fun shareButton(text: String)
        fun copyToClipboard(text: CharSequence)
    }

}