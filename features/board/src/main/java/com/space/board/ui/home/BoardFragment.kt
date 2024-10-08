package com.space.board.ui.home

import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.space.core_ui.R
import com.space.core_ui.base.ContainerCustomFragment
import com.space.core_ui.binding.adapter.PaddingItemDecoration
import com.space.core_ui.binding.adapter.view.ItemHeaderAdapter
import com.space.core_ui.databinding.FragmentEmtpyContainerBinding
import com.space.shared.data.board.BoardCategory
import com.space.shared.type.DividerType
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BoardFragment : ContainerCustomFragment<FragmentEmtpyContainerBinding, List<BoardCategory>>(
    R.layout.fragment_emtpy_container
) {
    companion object {
        fun newInstance() = BoardFragment()
    }

    override val viewModel: BoardViewModel by viewModels()
    private var adapter: RecyclerView.Adapter<*> = ShimmerAdapter()


    override fun initView() {
        super.initView()
        binding.recyclerView.adapter = adapter
        binding.recyclerView.addItemDecoration(
            PaddingItemDecoration(
                requireContext(),
                resources.getDimensionPixelSize(R.dimen.margin_20dp)
            )
        )
    }

    override fun beforeSuccessListener() {
        super.beforeSuccessListener()
        val result = viewModel.view.value?.data ?: return
        adapter = ItemHeaderAdapter(
            title = "학교게시판",
            titleSize = 18f,
            adapter = CategoryAdapter(result) { category ->
                viewModel.boardNavigatorBoard.openView(requireContext(), category)
            },
            dividerType = DividerType.DefaultMargin10,
            padding = false
        )
        binding.recyclerView.adapter = adapter
    }

}