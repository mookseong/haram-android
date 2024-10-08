package com.space.notice_space.ui

import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ConcatAdapter
import com.space.core_ui.R
import com.space.core_ui.base.ContainerFragment
import com.space.core_ui.binding.adapter.PaddingItemDecoration
import com.space.core_ui.extension.extraNotNull
import com.space.core_ui.extension.map
import com.space.notice_space.ui.binding.adapter.NoticeContentAdapter
import com.space.notice_space.ui.binding.adapter.NoticeHeaderAdapter
import com.space.notice_space.ui.binding.adapter.ShimmerAdapter
import com.space.shared.UiStatusType
import com.space.shared.data.notice_space.NoticeSpace
import com.space.shared.decodeFromString
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NoticeFragment : ContainerFragment<NoticeSpace>() {

    companion object {
        fun newInstance() = NoticeFragment()
    }

    private val type by extraNotNull<String>("type")
        .map { it.decodeFromString<String>() }
    override val viewModel: NoticeViewModel by viewModels()
    override val viewTitle: String = "공지사항"

    override fun init() {
        viewModel.getNotice(type)
    }

    override fun initView() {
        super.initView()
        binding.recyclerView.adapter = ShimmerAdapter()
        binding.recyclerView.addItemDecoration(
            PaddingItemDecoration(
                requireContext(),
                resources.getDimensionPixelSize(R.dimen.margin_20dp),
            )
        )
    }

    override fun beforeObserverListener() {
        super.beforeObserverListener()
        viewModel.view.observe(this) { result ->
            if (result.uiUiStatusType == UiStatusType.LOGOUT) {
                activity?.finishAffinity()
                viewModel.navigatorLogin.openView(requireContext())
            }
        }
    }

    override fun afterObserverListener() {
        viewModel.view.observe(this) {
            val data = it.data ?: return@observe
            val adapter = ConcatAdapter(
                NoticeHeaderAdapter(data),
                NoticeContentAdapter(data)
            )
            binding.recyclerView.adapter = adapter
        }
    }
}