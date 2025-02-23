package com.hannoobz.cs2weapons

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ListWeaponAdapter(private val listWeaponCard: ArrayList<WeaponCard>) : RecyclerView.Adapter<ListWeaponAdapter.ListViewHolder>(){
    private lateinit var onItemClickCallback: OnItemClickCallback

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)
        val tvName: TextView = itemView.findViewById(R.id.tv_item_name)
        val tvPrice: TextView = itemView.findViewById(R.id.tv_item_price)
        val tvType: TextView = itemView.findViewById(R.id.tv_item_type)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_weapon_row, parent, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listWeaponCard.size
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (_ ,name, price, type, photo) = listWeaponCard[position]
        holder.imgPhoto.setImageResource(photo)
        holder.tvName.text = name
        holder.tvPrice.text = price
        holder.tvType.text = type
        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(listWeaponCard[holder.adapterPosition]) }
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: WeaponCard)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }
}