package com.space.notice.ui.adapter

import android.graphics.text.LineBreaker
import android.os.Build
import android.text.Html
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.text.toSpannable
import androidx.core.text.toSpanned
import androidx.core.view.doOnAttach
import androidx.core.view.doOnDetach
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.lifecycle.lifecycleScope

import androidx.recyclerview.widget.RecyclerView
import com.space.notice.BR
import com.space.notice.databinding.ItemContentDetailBinding
import com.space.shared.data.notice.NoticeDetail

internal class ContentDetailAdapter(
    private val noticeDetail: NoticeDetail
) : RecyclerView.Adapter<ContentDetailViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentDetailViewHolder =
        ContentDetailViewHolder.newInstance(parent)

    override fun getItemCount(): Int = 1

    override fun onBindViewHolder(holder: ContentDetailViewHolder, position: Int) =
        holder.itemBind(noticeDetail)


}

internal class ContentDetailViewHolder(
    private val binding: ItemContentDetailBinding
) : RecyclerView.ViewHolder(binding.root) {

    init {
        itemView.doOnAttach {
            binding.lifecycleOwner = itemView.findViewTreeLifecycleOwner()
        }
        itemView.doOnDetach {
            binding.lifecycleOwner = null
        }
    }

    companion object {
        fun newInstance(
            parent: ViewGroup,
        ): ContentDetailViewHolder {
            val binding =
                ItemContentDetailBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            return ContentDetailViewHolder(binding)
        }
    }

    fun itemBind(noticeDetail: NoticeDetail) {
        binding.content.settings.apply {
            javaScriptEnabled = false
            loadWithOverviewMode = true;
//            useWideViewPort = true;

        }
        binding.content.loadData(noticeDetail.content,"text/html", "UTF-8")
    }
}
