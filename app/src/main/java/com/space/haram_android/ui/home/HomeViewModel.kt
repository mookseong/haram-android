package com.space.haram_android.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.space.data.ResponseBody
import com.space.data.ResultData
import com.space.data.type.ViewType
import com.space.data.response.home.HomeRes
import com.space.domain.usecase.home.HomeRepository
import com.space.haram_android.adapter.ViewTypeListener
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeRepository: HomeRepository,
) : ViewModel() {

    private val _homeFormState = MutableLiveData<HomeFormState>()
    val homeFormState: LiveData<HomeFormState> = _homeFormState

    private val _homeData = MutableLiveData<HomeRes>()
    val homeData: LiveData<HomeRes> = _homeData

    private val _eventViewType = MutableLiveData<ViewType>()
    val eventViewType: LiveData<ViewType> = _eventViewType

    private var offsetPx: Int? = null

    init {
        viewModelScope.launch {
            homeRepository.getHome().let { result ->
                when (result) {
                    is ResultData.Success<ResponseBody<HomeRes>> ->
                        result.body.data.let {
                            _homeData.value = it
                            _homeFormState.value = HomeFormState(homeStatus = true)
                        }

                    is ResultData.Error ->
                        _homeFormState.value = HomeFormState(loginStatus = false)

                    is ResultData.Unauthorized ->
                        _homeFormState.value = HomeFormState(loginStatus = false)

                }
            }

        }
    }

    val bindingListener = object : ViewTypeListener<ViewType> {
        override fun setViewType(viewType: ViewType) {
            Log.d("HomeViewModel", "set viewType $viewType")
            _eventViewType.value = viewType
        }

        override fun clearViewType() {
            _eventViewType.value = ViewType.NOT_THING
        }

    }


    fun viewTypeVerify(): Boolean =
        ViewType.NOT_THING != eventViewType.value


    fun getOffsetPx() = offsetPx

    fun setOffsetPx(offset: Int) {
        offsetPx = offset
    }

}