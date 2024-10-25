package com.example.shoppieeclient.domain.auth.repository.home

import com.example.shoppieeclient.domain.auth.models.home.HomeProductModel
import com.example.shoppieeclient.domain.auth.models.home.HomeResultModel
import com.example.shoppieeclient.utils.Resource
import kotlinx.coroutines.flow.Flow

interface ShoppieeHomeRepo {
    fun getBrandsData(category: String): Flow<Resource<HomeResultModel>>
}