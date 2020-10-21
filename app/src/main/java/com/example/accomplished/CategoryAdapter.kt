package com.example.accomplished

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.example.accomplished.database.Category
import kotlinx.android.synthetic.main.list_item.view.*


class CategoryAdapter(var categories: ArrayList<Category>, var clickListener: OnCategoryItemClickListener) : RecyclerView.Adapter<CategoryViewHolder>() {
    lateinit var context: Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        context = parent.context
        return CategoryViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item,parent,false))
    }

    override fun getItemCount(): Int {
        return categories.size
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        /*holder.categoryTitle?.text = categories.get(position).title
        holder.categorySubtitle?.text = categories.get(position).subtitle
        holder.categoryLogo.setImageResource(categories.get(position).logo)*/

        holder.initialize(categories.get(position),clickListener, context)
    }



}

class CategoryViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView){
    var categoryTitle = itemView.title
    var categorySubtitle = itemView.subtitle
    var image = itemView.imageView

    val deleteBtn = itemView.findViewById<Button>(R.id.deleteButton)
    val editBtn = itemView.findViewById<Button>(R.id.editButton)

    fun initialize(item: Category, action:OnCategoryItemClickListener, context: Context) {
        categoryTitle.text = item.catName
        categorySubtitle.text = item.catDesc

        val imageArray = context.resources.obtainTypedArray(R.array.image_ids)
        imageArray.getResourceId(imageArray.getIndex(0),-1)
        val rnds = (0 until 5).random()
        image.setImageResource(imageArray.getResourceId(imageArray.getIndex(rnds-1),-1))
        imageArray.recycle()
        itemView.setOnClickListener {
            action.onItemClick(item, adapterPosition)
        }

        deleteBtn.setOnClickListener {
            action.removeItem(item, adapterPosition)
        }

        editBtn.setOnClickListener {
            action.editItem(item, adapterPosition)
        }
    }
}

interface OnCategoryItemClickListener{
    fun onItemClick(item: Category, position: Int)
    fun removeItem(item: Category, position: Int)
    fun editItem(item: Category,position: Int)
}