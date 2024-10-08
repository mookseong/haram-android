package com.space.bible.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.setPadding
import androidx.recyclerview.widget.RecyclerView
import com.space.bible.databinding.ItemBibleSelectBinding
import com.space.core_ui.util.ParamsItemHandler


internal class SelectAdapter(
    private val item: List<String>,
    private val itemHandler: ParamsItemHandler<String>
) : RecyclerView.Adapter<SelectViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectViewHolder =
        SelectViewHolder.newInstance(parent)

    override fun getItemCount() = item.size

    override fun onBindViewHolder(holder: SelectViewHolder, position: Int) {
        holder.itemBind(itemHandler, item[position])
    }
}

internal class SelectViewHolder(
    private val binding: ItemBibleSelectBinding
) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun newInstance(
            parent: ViewGroup,
        ): SelectViewHolder {
            val binding = ItemBibleSelectBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            return SelectViewHolder(binding)
        }
    }

    fun itemBind(itemHandler: ParamsItemHandler<String>, string: String) {
        binding.headerTitle.text = string
        binding.headerTitle.setOnClickListener {
            itemHandler.onClick(string)
        }
        binding.headerTitle.setPadding(10)
    }

}
