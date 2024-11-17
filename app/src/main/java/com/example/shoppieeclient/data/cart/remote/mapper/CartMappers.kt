package com.example.shoppieeclient.data.cart.remote.mapper

import com.example.shoppieeclient.data.cart.remote.dto.CartItemsDto
import com.example.shoppieeclient.data.cart.remote.dto.CartProductDto
import com.example.shoppieeclient.data.cart.remote.dto.CartResultDto
import com.example.shoppieeclient.domain.cart.models.CartProductModel
import com.example.shoppieeclient.domain.cart.models.CartResultModel
import com.example.shoppieeclient.domain.cart.models.CartResultResponseModel
import kotlin.text.category

fun CartResultDto.toCartProductModel(): CartResultResponseModel {
    return CartResultResponseModel(
        cartItems = this.cartItems.map { it.toCartDetails() },
        currentPage = this.currentPage,
        status = this.status,
        totalCartItems = this.totalCartItems,
        totalPages = this.totalPages
    )
}

fun CartItemsDto.toCartDetails(): CartResultModel {
    return CartResultModel(
        product = this.product.toCartProducts(),
        quantity = this.quantity
    )
}

fun CartProductDto.toCartProducts(): CartProductModel {
    return CartProductModel(
        productId = this.productId,
        name = this.name,
        brand = this.brand,
        description = this.description,
        quantity = this.quantity,
        price = this.price,
        category = this.category,
        images = this.images,
        inCart = this.inCart,
        cartItemCount = this.cartItemCount
    )
}