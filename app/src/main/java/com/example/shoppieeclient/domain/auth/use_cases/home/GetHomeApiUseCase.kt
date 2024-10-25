package com.example.shoppieeclient.domain.auth.use_cases.home

import com.example.shoppieeclient.domain.auth.models.home.HomeResultModel
import com.example.shoppieeclient.domain.auth.repository.home.ShoppieeHomeRepo
import com.example.shoppieeclient.utils.Resource
import kotlinx.coroutines.flow.Flow

class GetHomeApiUseCase(
    private val shoppieeHomeRepo: ShoppieeHomeRepo
)  {
    operator fun invoke(brand: String): Flow<Resource<HomeResultModel>> {
        return shoppieeHomeRepo.getBrandsData(category = brand)
    }
}