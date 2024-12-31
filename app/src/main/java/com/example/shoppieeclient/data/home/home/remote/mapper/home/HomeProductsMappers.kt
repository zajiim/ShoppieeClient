package com.example.shoppieeclient.data.home.home.remote.mapper.home


import com.example.shoppieeclient.data.home.home.remote.dto.home.HomeResponseDto
import com.example.shoppieeclient.data.home.home.remote.dto.home.ProductDto
import com.example.shoppieeclient.data.home.home.remote.dto.home.ResultDto
import com.example.shoppieeclient.domain.home.home.models.HomeProductModel
import com.example.shoppieeclient.domain.home.home.models.HomeResultModel

fun ProductDto.toHomeProductModel(): HomeProductModel {
    return HomeProductModel(
        productId = this.productId,
        name = this.name,
        brand = this.brand,
        description = this.description,
        quantity = this.quantity,
        price = this.price,
        category = this.category,
        images = this.images
    )
}

fun ResultDto.toHomeResultModel(): HomeResultModel {
    return HomeResultModel(
        popularItemsModel = this.popular?.map { it.toHomeProductModel() } ?: emptyList(),
        newArrivalItemsModel = this.newArrivals?.map { it.toHomeProductModel() } ?: emptyList()
    )
}

fun HomeResponseDto.toHomeResultModel(): HomeResultModel {
    return HomeResultModel(
        popularItemsModel = this.result?.popular?.map { it.toHomeProductModel() } ?: emptyList(),
        newArrivalItemsModel = this.result?.newArrivals?.map { it.toHomeProductModel() } ?: emptyList()
    )
}

//fun ResultDto.toHomeResultModel(): HomeResultModel {
//    return HomeResultModel(
//        popularItemsModel = popular?.map { it.toHomeProductModel() } ?: emptyList(),
//        newArrivalItemsModel = newArrivals?.mapNotNullTo(mutableListOf()) { it.toHomeProductModel() }
//            ?: emptyList(),)
//}

