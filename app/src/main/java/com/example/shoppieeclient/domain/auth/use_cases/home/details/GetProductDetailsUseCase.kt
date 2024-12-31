package com.example.shoppieeclient.domain.auth.use_cases.home.details

import com.example.shoppieeclient.domain.home.home.models.DetailsProductModel
import com.example.shoppieeclient.domain.home.home.repository.ShoppieeHomeRepo
import com.example.shoppieeclient.utils.Resource
import kotlinx.coroutines.flow.Flow


class GetProductDetailsUseCase(
    private val shoppieeHomeRepo: ShoppieeHomeRepo
)  {
    operator fun invoke(productId: String): Flow<Resource<DetailsProductModel>> {
        return shoppieeHomeRepo.fetchProductDetails(productId = productId)
    }
}