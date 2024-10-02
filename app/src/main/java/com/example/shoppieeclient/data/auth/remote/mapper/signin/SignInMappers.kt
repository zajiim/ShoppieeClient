package com.example.shoppieeclient.data.auth.remote.mapper.signin

import com.example.shoppieeclient.data.auth.remote.dto.signin.Product
import com.example.shoppieeclient.data.auth.remote.dto.signin.SignInUserData
import com.example.shoppieeclient.domain.auth.models.signin.ProductModel
import com.example.shoppieeclient.domain.auth.models.signin.SignInUserModel


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