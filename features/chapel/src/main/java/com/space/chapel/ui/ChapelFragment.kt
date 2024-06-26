package com.space.chapel.ui

import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ConcatAdapter
import com.space.chapel.ui.databinding.adapter.ChapelDetailAdapter
import com.space.chapel.ui.databinding.adapter.ChapelInfoAdapter
import com.space.chapel.ui.databinding.adapter.ChapelInfoDetailAdapter
import com.space.chapel.ui.databinding.adapter.HeaderAdapter
import com.space.chapel.ui.databinding.adapter.ShimmerAdapter
import com.space.core_ui.base.ContainerFragment
import dagger.hilt.android.AndroidEntryPoint
import com.space.core_ui.binding.adapter.view.HeaderServiceInfoAdapter
import com.space.shared.UiStatusType
import com.space.shared.data.chapel.Chapel


@AndroidEntryPoint
class ChapelFragment : ContainerFragment<Chapel>() {
    companion object {
        fun newInstance() = ChapelFragment()
    }

    override val viewTitle: String = "채플조회"
    override val viewModel: ChapelViewModel by viewModels()

    override fun initView() {
        super.initView()
        if (viewModel.view.value?.uiUiStatusType == UiStatusType.LOADING) {
            binding.recyclerView.adapter = ShimmerAdapter()
        }
    }

    override fun beforeSuccessListener() {
        val data = viewModel.view.value?.data ?: return
        val adapter = ConcatAdapter(
            ChapelInfoAdapter(data.chapelInfo),
            ChapelInfoDetailAdapter(data.chapelInfo),
            HeaderServiceInfoAdapter("채플정보 안내", "채플 정보는 인트라넷과 차이가 발생할 수 있습니다"),
            HeaderAdapter(),
            ChapelDetailAdapter(data.chapelDetail)
        )
        binding.recyclerView.adapter = adapter
    }

}