package com.space.notice_space.ui.binding.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.space.notice_space.databinding.ShimmerNoticeSpaceBinding


internal class ShimmerAdapter : RecyclerView.Adapter<ShimmerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShimmerViewHolder =
        ShimmerViewHolder.newInstance(parent)

    override fun getItemCount() = 1

    override fun onBindViewHolder(holder: ShimmerViewHolder, position: Int) =
        holder.binding.shimmerViewContainer.startShimmer()
}

internal class ShimmerViewHolder(
    val binding: ShimmerNoticeSpaceBinding
) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun newInstance(
            parent: ViewGroup,
        ): ShimmerViewHolder {
            val binding = ShimmerNoticeSpaceBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            return ShimmerViewHolder(binding)
        }
    }
}

