package com.space.book.ui.detail.adapter.holder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.space.book.databinding.ItemBookDetailRentalBinding
import com.space.book.ui.detail.adapter.RentalItemAdapter
import com.space.core_ui.R
import com.space.core_ui.binding.adapter.DividerGrayLineDecoration
import com.space.shared.data.book.Rental

internal class RentalViewHolder(
    private val binding: ItemBookDetailRentalBinding
) : RecyclerView.ViewHolder(binding.root) {
    companion object {
        fun newInstance(parent: ViewGroup): RentalViewHolder {
            val binding = ItemBookDetailRentalBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            return RentalViewHolder(binding)
        }
    }

    fun itemBind(rental: List<Rental>) {
        binding.recyclerView.adapter = RentalItemAdapter(rental)
        binding.recyclerView.isNestedScrollingEnabled = false
        binding.recyclerView.addItemDecoration(
            DividerGrayLineDecoration(
                itemView.context,
                itemView.context.resources.getDimensionPixelSize(R.dimen.margin_10dp),
                false
            )
        )
    }
}