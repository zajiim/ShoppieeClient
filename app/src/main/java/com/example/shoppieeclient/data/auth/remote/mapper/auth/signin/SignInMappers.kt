package com.example.shoppieeclient.data.auth.remote.mapper.auth.signin

import com.example.shoppieeclient.data.auth.remote.dto.auth.signin.CartItemDto
import com.example.shoppieeclient.data.auth.remote.dto.auth.signin.Product
import com.example.shoppieeclient.data.auth.remote.dto.auth.signin.SignInUserData
import com.example.shoppieeclient.domain.auth.models.auth.signin.CartItemModel
import com.example.shoppieeclient.domain.auth.models.auth.signin.ProductModel
import com.example.shoppieeclient.domain.auth.models.auth.signin.SignInUserModel


fun SignInUserData.toSignInUserModel(): SignInUserModel {
    return SignInUserModel(
        id = id,
        name = name,
        email = email,
        userType = type,
        cartItems = cart.map { it.toCartItemModel() },
        token = token,
        profileImage = profileImage
    )
}

fun CartItemDto.toCartItemModel(): CartItemModel {
    return CartItemModel(
        product = product.toProductModel(), quantity = quantity
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
        images = images,
        inCart = inCart,
        size = size
    )
}