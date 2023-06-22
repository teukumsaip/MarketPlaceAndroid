package com.teukkun.marketplace.ui.navigation.home.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.inyongtisto.marketplace.databinding.ItemHomeCategoryBinding
import com.teukkun.marketplace.util.toUrlCategory
import com.inyongtisto.myhelper.extension.setImagePicasso
import com.teukkun.marketplace.core.data.source.model.Category

@SuppressLint("NotifyDataSetChanged")
class CategoryAdapter : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    private var data = ArrayList<Category>()

    inner class ViewHolder(private val itemBinding: ItemHomeCategoryBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(item: Category, position: Int) {
            itemBinding.apply {
                tvName.text = item.name
                imageView.setImagePicasso(item.image?.toUrlCategory())
            }
        }
    }

    fun addItems(items: List<Category>) {
        data.clear()
        data.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemHomeCategoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position], position)
    }

    override fun getItemCount(): Int {
        return data.size
    }

}