package com.teukkun.marketplace.ui.product.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.inyongtisto.marketplace.databinding.ItemProductTokoBinding
import com.teukkun.marketplace.util.toUrlProduct
import com.inyongtisto.myhelper.extension.*
import com.teukkun.marketplace.core.data.source.model.Product

@SuppressLint("NotifyDataSetChanged")
class ProductTokoAdapter(
    val onClick: (item: Product) -> Unit,
    val onDelete: (item: Product, pos: Int) -> Unit
) : RecyclerView.Adapter<ProductTokoAdapter.ViewHolder>() {

    private var data = ArrayList<Product>()

    inner class ViewHolder(private val itemBinding: ItemProductTokoBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(item: Product, position: Int) {
            itemBinding.apply {
                val context = root.context
                tvName.text = item.name
                tvHarga.text = item.price.toRupiah()
                tvStok.text = item.stock.toString()

                val splitImages = item.images?.split("|")
                val imageProduct = if (splitImages.isNullOrEmpty()) {
                    item.images ?: ""
                } else {
                    splitImages[0]
                }

                imgProduct.setImagePicasso(imageProduct.toUrlProduct())

                btnEdit.setOnClickListener {
                    onClick.invoke(item)
                }

                lyMain.setOnClickListener {
                    onClick.invoke(item)
                }

                btnDelete.setOnClickListener {
                    onDelete.invoke(item, adapterPosition)
                }
            }
        }
    }

    fun removeAt(index: Int) {
        data.removeAt(index)
        notifyItemRemoved(index)
    }

    fun addItems(items: List<Product>) {
        data.clear()
        data.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemProductTokoBinding.inflate(
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