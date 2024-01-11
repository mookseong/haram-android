package com.space.repository.service.inf

import com.space.shared.data.chapel.ChapelDetail
import com.space.shared.data.chapel.ChapelInfo

interface ChapelService {

    suspend fun getChapelInfo(): ChapelInfo

    suspend fun getChapelDetail(): List<ChapelDetail>
}