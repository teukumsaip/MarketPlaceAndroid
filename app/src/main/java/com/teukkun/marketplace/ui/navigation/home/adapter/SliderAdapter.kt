package com.teukkun.marketplace.ui.navigation.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.inyongtisto.marketplace.databinding.ItemHomeSliderBinding
import com.teukkun.marketplace.util.toUrlSlider
import com.inyongtisto.myhelper.extension.setImagePicasso
import com.teukkun.marketplace.core.data.source.model.Slider

class SliderAdapter : PagerAdapter() {
    private val data: ArrayList<Slider> = ArrayList()

    override fun getCount() = data.size

    override fun isViewFromObject(view: View, obj: Any): Boolean {
        return view == obj
    }

    fun addItems(items: List<Slider>) {
        data.addAll(items)
        notifyDataSetChanged()
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val binding =
            ItemHomeSliderBinding.inflate(LayoutInflater.from(container.context), container, false)
        val item = data[position]

        binding.apply {
            imageView.setImagePicasso(item.image.toUrlSlider())
        }

        container.addView(binding.root)
        return binding.root
    }

    override fun destroyItem(container: ViewGroup, position: Int, obj: Any) {
        container.removeView(obj as View)
    }
}