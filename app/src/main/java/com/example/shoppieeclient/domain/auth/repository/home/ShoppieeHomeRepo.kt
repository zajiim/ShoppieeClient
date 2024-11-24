package com.example.shoppieeclient.domain.auth.repository.home

import com.example.shoppieeclient.domain.auth.models.home.AddToCartResultModel
import com.example.shoppieeclient.domain.auth.models.home.DetailsProductModel
import com.example.shoppieeclient.domain.auth.models.home.HomeProductModel
import com.example.shoppieeclient.domain.auth.models.home.HomeResultModel
import com.example.shoppieeclient.utils.Resource
import kotlinx.coroutines.flow.Flow

interface ShoppieeHomeRepo {
    fun getBrandsData(category: String): Flow<Resource<HomeResultModel>>

    fun fetchProductDetails(productId: String): Flow<Resource<DetailsProductModel>>

    fun addToCart(productId: String, selectedRegion: String, selectedSize: Int): Flow<Resource<AddToCartResultModel>>
}