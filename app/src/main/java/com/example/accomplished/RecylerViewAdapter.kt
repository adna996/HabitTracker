package com.example.accomplished

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_item.view.*

class RecylerViewAdapter
    //: RecyclerView.Adapter<RecylerViewAdapter.ViewHolder>(){
    /*private var items: List<Item> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecylerViewAdapter.ViewHolder {
        return ViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when(holder){
            is ViewHolder -> {
                holder.bind(items.get(position))
            }
        }    }

    fun submitList(itemList: List<Item>){
        items = itemList
    }

    class ViewHolder constructor(itemView: View): RecyclerView.ViewHolder(itemView){

    }




    /*override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(arrayList[position])
    }

    class ViewHolder (itemView: View): RecyclerView.ViewHolder(itemView) {

        fun bind(item: Item){
            itemView.imageIV.setImageResource(item.image)
        }
    }*/*/

