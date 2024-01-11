package com.space.repository.service.impl

import com.space.repository.service.inf.ChapelService
import com.space.repository.api.ChapelApi
import com.space.shared.data.chapel.ChapelDetail
import com.space.shared.data.chapel.ChapelInfo
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class ChapelServiceImpl @Inject constructor(
    private val chapelApi: ChapelApi
) : ChapelService {
    override suspend fun getChapelInfo(): ChapelInfo {
        return runBlocking {
            chapelApi.getChapelInfo().data
        }
    }

    override suspend fun getChapelDetail(): List<ChapelDetail> {
        return runBlocking {
            chapelApi.getChapelDetail().data
        }
    }
}