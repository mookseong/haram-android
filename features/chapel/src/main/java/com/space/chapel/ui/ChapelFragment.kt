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
import com.space.shared.type.AuthType


@AndroidEntryPoint
class ChapelFragment : ContainerFragment() {
    companion object {
        fun newInstance() = ChapelFragment()
    }
    override val viewTitle: String = "채플조회"
    override val viewModel: ChapelViewModel by viewModels()

    override fun beforeObserverListener() {
        viewModel.view.observe(this) {
            if (it.uiUiStatusType == UiStatusType.REJECT) {
                viewModel.navigatorLogin.openView(requireContext(), AuthType.INTRANET)
                activity?.finish()
            }
        }
    }



    override fun initView() {
        super.initView()
        binding.recyclerView.adapter = ShimmerAdapter()
    }

    override fun initListener() {
        viewModel.view.observe(viewLifecycleOwner) { result ->
            if (result.uiUiStatusType == UiStatusType.SUCCESS) {
                val data = result.data!!
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
    }
}