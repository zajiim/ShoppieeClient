package com.example.shoppieeclient.data.auth.remote.mapper.auth.signin

import com.example.shoppieeclient.data.auth.remote.dto.auth.signin.Product
import com.example.shoppieeclient.data.auth.remote.dto.auth.signin.SignInUserData
import com.example.shoppieeclient.domain.auth.models.auth.signin.ProductModel
import com.example.shoppieeclient.domain.auth.models.auth.signin.SignInUserModel


fun SignInUserData.toSignInUserModel(): SignInUserModel {
    return SignInUserModel(
        id = id,
        name = name,
        email = email,
        userType = type,
        cartItems = cart.map { it.toProductModel() },
        token = token
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