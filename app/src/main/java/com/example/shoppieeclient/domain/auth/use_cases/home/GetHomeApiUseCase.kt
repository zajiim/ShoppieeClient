package com.example.shoppieeclient.domain.auth.use_cases.home

import com.example.shoppieeclient.domain.home.home.models.HomeResultModel
import com.example.shoppieeclient.domain.home.home.repository.ShoppieeHomeRepo
import com.example.shoppieeclient.utils.Resource
import kotlinx.coroutines.flow.Flow

class GetHomeApiUseCase(
    private val shoppieeHomeRepo: ShoppieeHomeRepo
)  {
    operator fun invoke(brand: String): Flow<Resource<HomeResultModel>> {
        return shoppieeHomeRepo.getBrandsData(category = brand)
    }
}