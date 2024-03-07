package com.space.rothem.ui.room

import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ConcatAdapter
import com.space.core_ui.BR
import com.space.core_ui.R
import com.space.core_ui.adapter.BottomButtonAdapter
import com.space.core_ui.base.BaseFragment
import com.space.core_ui.databinding.FragmentImgHomeBinding
import com.space.core_ui.extraNotNull
import com.space.core_ui.map
import com.space.core_ui.transformFragment
import com.space.rothem.ui.reservation.ReservationFragment
import com.space.rothem.ui.room.adapter.RoomAmenitiesAdapter
import com.space.rothem.ui.room.adapter.RoomDescriptionAdapter
import com.space.rothem.ui.room.adapter.RoomHeaderAdapter
import com.space.shared.data.core_ui.ImgHomeDescription
import com.space.shared.data.core_ui.ImgHomeTitle
import com.space.shared.data.rothem.Room
import com.space.shared.decodeFromString
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RoomFragment : BaseFragment<FragmentImgHomeBinding>(
    R.layout.fragment_img_home
) {

    companion object {
        fun newInstance() = RoomFragment()
    }


    private val viewModel: RoomViewModel by viewModels()
    private val room by extraNotNull<String>("room")
        .map { encodeString ->
            encodeString.decodeFromString<Room>()
        }

    override fun beforeObserverListener() {

    }

    override fun init() {
        super.init()
        room.let {
            viewModel.onRothemDetail(it)
        }
    }

    override fun initView() {
        binding.setVariable(BR.title, room.roomName)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.recyclerView.isNestedScrollingEnabled = false
    }

    override fun afterObserverListener() {
        super.afterObserverListener()
        viewModel.rothem.observe(viewLifecycleOwner) {
            binding.setVariable(BR.imgUrl, it.roomResponse.thumbnailPath)
            val adapter = ConcatAdapter(
                RoomHeaderAdapter(
                    ImgHomeTitle(it.roomResponse.roomName, it.roomResponse.location)
                ),
                RoomDescriptionAdapter(
                    ImgHomeDescription("Description", it.roomResponse.roomExplanation)
                ),
                RoomAmenitiesAdapter(it.amenityResponses)
            )
            binding.recyclerView.adapter = BottomButtonAdapter("예약하기", it.roomResponse.roomSeq.toString(), adapter) { params ->
                parentFragmentManager.transformFragment<ReservationFragment>(
                    R.id.container,
                    "reservation" to params
                )
            }
        }
    }
}