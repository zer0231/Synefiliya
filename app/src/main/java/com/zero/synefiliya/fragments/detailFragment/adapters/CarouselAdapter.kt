package com.zero.synefiliya.fragments.detailFragment.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.zero.synefiliya.databinding.LayoutCarouselBinding

class CarouselAdapter(
    private val context: Context,
    private val carouselData: List<String>
) :
    RecyclerView.Adapter<CarouselAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutCarouselBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = carouselData.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(context).load(carouselData[position]).into(holder.imageView)
    }

    class ViewHolder(itemViewBinding: LayoutCarouselBinding) :
        RecyclerView.ViewHolder(itemViewBinding.root) {
        val imageView = itemViewBinding.carouselIv

    }

}