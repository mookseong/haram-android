package com.space.board.ui.page

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.space.domain.usecase.board.BoardPageUseCase
import com.space.shared.UiStatus
import com.space.shared.UiStatusType
import com.space.shared.data.board.BoardPage
import com.space.shared.mapCatching
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class PageViewModel @Inject constructor(
    private val boardPageUseCase: BoardPageUseCase
) : ViewModel() {

    private val _category: MutableLiveData<UiStatus<BoardPage>> =
        MutableLiveData<UiStatus<BoardPage>>()
    val category: LiveData<UiStatus<BoardPage>> = _category

    fun getPages(
        type: Int,
        page: Int = 1
    ) {
        viewModelScope.launch {
            val result = async { boardPageUseCase(Pair(type, page)) }.await()
            result.mapCatching(
                onSuccess = { boardPage ->
                    _category.value = UiStatus(UiStatusType.SUCCESS, boardPage)
                },
                onError = { error ->
                    Timber.d(error.message)
                }
            )
        }
    }
}