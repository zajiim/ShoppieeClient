package com.example.shoppieeclient.domain.home.home.repository

import com.example.shoppieeclient.domain.home.home.models.AddToCartResultModel
import com.example.shoppieeclient.domain.home.home.models.DetailsProductModel
import com.example.shoppieeclient.domain.home.home.models.HomeResultModel
import com.example.shoppieeclient.utils.Resource
import kotlinx.coroutines.flow.Flow

interface ShoppieeHomeRepo {
    fun getBrandsData(category: String): Flow<Resource<HomeResultModel>>

    fun fetchProductDetails(productId: String): Flow<Resource<DetailsProductModel>>

    fun addToCart(productId: String, selectedRegion: String, selectedSize: Int): Flow<Resource<AddToCartResultModel>>
}