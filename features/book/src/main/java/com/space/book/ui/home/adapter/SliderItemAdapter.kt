package com.space.book.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.space.book.databinding.ItemBookSliderImgBinding
import com.space.shared.data.Item


internal class SliderItemAdapter(
    private val item: Item<String, ItemHandler>
) : RecyclerView.Adapter<ItemSliderImgViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemSliderImgViewHolder =
        ItemSliderImgViewHolder.newInstance(parent)

    override fun getItemCount() = item.list.size

    override fun onBindViewHolder(holder: ItemSliderImgViewHolder, position: Int) =
        holder.bindItem(item.list[position])


    interface ItemHandler {
        fun clickSlider(string: String)
    }
}

internal class ItemSliderImgViewHolder(
    private val binding: ItemBookSliderImgBinding
) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun newInstance(
            parent: ViewGroup,
        ): ItemSliderImgViewHolder {
            val binding = ItemBookSliderImgBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            return ItemSliderImgViewHolder(binding)
        }
    }

    fun bindItem(string: String) {
        binding.uri = string
    }

}
