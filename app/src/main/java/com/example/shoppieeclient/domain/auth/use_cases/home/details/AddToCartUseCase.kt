package com.example.shoppieeclient.domain.auth.use_cases.home.details

import com.example.shoppieeclient.domain.auth.models.home.AddToCartResultModel
import com.example.shoppieeclient.domain.auth.models.home.DetailsProductModel
import com.example.shoppieeclient.domain.auth.repository.home.ShoppieeHomeRepo
import com.example.shoppieeclient.utils.Resource
import kotlinx.coroutines.flow.Flow

class AddToCartUseCase(
    private val shoppieeHomeRepo: ShoppieeHomeRepo
) {
    operator fun invoke(productId: String): Flow<Resource<AddToCartResultModel>> {
        return shoppieeHomeRepo.addToCart(productId)
    }
}