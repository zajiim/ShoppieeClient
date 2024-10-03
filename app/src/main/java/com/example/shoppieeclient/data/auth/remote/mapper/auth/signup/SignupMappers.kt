package com.example.shoppieeclient.data.auth.remote.mapper.auth.signup

import com.example.shoppieeclient.data.auth.remote.dto.auth.signup.Product
import com.example.shoppieeclient.data.auth.remote.dto.auth.signup.SignUpUserData
import com.example.shoppieeclient.domain.auth.models.auth.signup.ProductModel
import com.example.shoppieeclient.domain.auth.models.auth.signup.SignUpUserModel

fun SignUpUserData.toSignUpUserModel(): SignUpUserModel {
    return SignUpUserModel(
        id = id,
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