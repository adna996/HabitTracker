package com.example.accomplished

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.accomplished.database.Activity
import kotlinx.android.synthetic.main.list_item.view.*

class ActivityAdapter(var activities: ArrayList<Activity>, var clickListener: ActivityFragment) : RecyclerView.Adapter<ActivityViewHolder>() {
    lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActivityViewHolder {
        context = parent.context
        return ActivityViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item,parent,false))
    }

    override fun getItemCount(): Int {
        return activities.size
    }

    override fun onBindViewHolder(holder: ActivityViewHolder, position: Int) {
        /*holder.categoryTitle?.text = categories.get(position).title
        holder.categorySubtitle?.text = categories.get(position).subtitle
        holder.categoryLogo.setImageResource(categories.get(position).logo)*/
        holder.initialize(activities.get(position),clickListener, context)
    }



}

class ActivityViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView){
    var categoryTitle = itemView.title
    var categorySubtitle = itemView.subtitle
    var edit = itemView.editButton
    var delete = itemView.deleteButton
    var image = itemView.imageView

    @SuppressLint("SetTextI18n")
    fun initialize(item: Activity, action: ActivityFragment, context: Context){
        categoryTitle.text = item.actName
        categorySubtitle.text = "Value: " + item.value.toString() + " "+ item.actAttribute.toString()
        val imageArray = context.resources.obtainTypedArray(R.array.image_ids)
        imageArray.getResourceId(imageArray.getIndex(0),-1)
        val rnds = (0 until 5).random()
        image.setImageResource(imageArray.getResourceId(imageArray.getIndex(rnds-1),-1))
        imageArray.recycle()

        itemView.setOnClickListener{
            action.onItemClick(item, adapterPosition)
        }
        delete.setOnClickListener {
            action.removeItem(item,adapterPosition)
        }
        edit.setOnClickListener{
            action.editItem(item, adapterPosition)
        }
    }
}

interface OnActivityItemClickListener{
    fun onItemClick(item: Activity, position: Int)
    fun removeItem(item: Activity, position: Int)
    fun editItem(item: Activity, position: Int)

}
