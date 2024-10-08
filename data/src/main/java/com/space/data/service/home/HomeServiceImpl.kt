package com.space.data.service.home

import com.space.shared.data.home.HomeInfo
import com.space.data.rest.HomeApi
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

internal class HomeServiceImpl @Inject constructor(
    private val spaceHomeApi: HomeApi,
) : HomeService {

    override suspend fun getHome(): HomeInfo {
        return runBlocking {
            spaceHomeApi.getHome().data
        }
    }

}