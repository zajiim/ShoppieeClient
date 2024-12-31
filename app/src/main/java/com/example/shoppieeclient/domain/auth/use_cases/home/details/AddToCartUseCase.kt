package com.example.shoppieeclient.domain.auth.use_cases.home.details

import com.example.shoppieeclient.domain.home.home.models.AddToCartResultModel
import com.example.shoppieeclient.domain.home.home.repository.ShoppieeHomeRepo
import com.example.shoppieeclient.utils.Resource
import kotlinx.coroutines.flow.Flow

class AddToCartUseCase(
    private val shoppieeHomeRepo: ShoppieeHomeRepo
) {
    operator fun invoke(productId: String, selectedRegion: String, selectedSize: Int): Flow<Resource<AddToCartResultModel>> {
        return shoppieeHomeRepo.addToCart(productId, selectedRegion, selectedSize)
    }
}