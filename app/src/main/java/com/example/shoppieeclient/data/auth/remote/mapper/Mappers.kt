package com.example.shoppieeclient.data.auth.remote.mapper

import com.example.shoppieeclient.data.auth.remote.dto.signup.Product
import com.example.shoppieeclient.data.auth.remote.dto.signup.UserData
import com.example.shoppieeclient.domain.auth.models.signup.ProductModel
import com.example.shoppieeclient.domain.auth.models.signup.UserModel

fun UserData.toUserModel(): UserModel {
    return UserModel(
        id = id.toString(),
        name = name,
        email = email,
        userType = type,
        cartItems = cart.map { it.toProductModel() }
    )
}

fun Product.toProductModel(): ProductModel {
    return ProductModel(
        productId = productId,
        name = name,
        brand = brand,
        description = description,
        quantity = quantity,
        price = price,
        category = category,
        images = images
    )
}