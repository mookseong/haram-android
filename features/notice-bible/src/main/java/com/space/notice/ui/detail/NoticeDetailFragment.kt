package com.space.notice.ui.detail

import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ConcatAdapter
import com.space.core_ui.R
import com.space.core_ui.base.ContainerFragment
import com.space.core_ui.binding.adapter.PaddingItemDecoration
import com.space.core_ui.extension.extraNotNull
import com.space.core_ui.extension.logEvent
import com.space.core_ui.extension.map
import com.space.notice.ui.adapter.ContentDetailAdapter
import com.space.notice.ui.adapter.HeaderDetailAdapter
import com.space.notice.ui.adapter.ShimmerDetailAdapter
import com.space.shared.data.notice_bible.Notice
import com.space.shared.data.notice_bible.NoticeDetail
import com.space.shared.data.notice_bible.NoticeType
import com.space.shared.decodeFromString
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NoticeDetailFragment : ContainerFragment<NoticeDetail>() {

    companion object {
        fun newInstance() = NoticeDetailFragment()
    }

    override val viewTitle: String = "공지사항"
    override val viewModel: NoticeDetailViewModel by viewModels()

    private val type by extraNotNull<String>("type")
        .map { it.decodeFromString<NoticeType>() }

    private val detail by extraNotNull<String>("detail")
        .map { it.decodeFromString<Notice>() }

    override fun init() {
        viewModel.getNoticeDetail(detail, type)
        firebaseAnalytics.logEvent("bible_notice") {
            param("notice_detail", "${type.key}_${detail.path}")
        }
    }

    override fun initView() {
        super.initView()
        binding.recyclerView.adapter = ShimmerDetailAdapter()
        binding.recyclerView.addItemDecoration(
            PaddingItemDecoration(
                requireContext(),
                resources.getDimensionPixelSize(R.dimen.margin_20dp),
            )
        )
    }

    override fun beforeSuccessListener() {
        super.beforeSuccessListener()
        val data = viewModel.view.value?.data ?: return
        val adapter = ConcatAdapter(
            HeaderDetailAdapter(data),
            ContentDetailAdapter(data)
        )
        binding.recyclerView.adapter = adapter
    }

}