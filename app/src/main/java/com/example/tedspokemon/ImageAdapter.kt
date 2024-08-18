package com.example.tedspokemon

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.data.module.ImageResource
import com.example.tedspokemon.databinding.HolderImageBinding

class ImageAdapter : RecyclerView.Adapter<ImageAdapter.ImageHolder>() {

    class ImageHolder(val binding: HolderImageBinding) : RecyclerView.ViewHolder(binding.root)

    private val data = mutableListOf<ImageResource>()

    @SuppressLint("NotifyDataSetChanged")
    fun appendData(newData: List<ImageResource>) {
        data.addAll(newData)
        notifyItemRangeChanged(data.lastIndex - 30, 30)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageHolder =
        ImageHolder(HolderImageBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ImageHolder, position: Int) {
        holder.apply {
            val uiData = data[position]
            Glide.with(itemView).load(uiData.url).into(binding.pic)
            binding.name.text = uiData.name
        }
    }
}