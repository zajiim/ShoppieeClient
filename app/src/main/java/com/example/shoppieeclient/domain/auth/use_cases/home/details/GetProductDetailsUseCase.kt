package com.example.shoppieeclient.domain.auth.use_cases.home.details

import com.example.shoppieeclient.domain.auth.models.home.HomeProductModel
import com.example.shoppieeclient.domain.auth.repository.home.ShoppieeHomeRepo
import com.example.shoppieeclient.utils.Resource
import kotlinx.coroutines.flow.Flow


class GetProductDetailsUseCase(
    private val shoppieeHomeRepo: ShoppieeHomeRepo
)  {
    operator fun invoke(productId: String): Flow<Resource<HomeProductModel>> {
        return shoppieeHomeRepo.fetchProductDetails(productId = productId)
    }
}