package com.example.shoppieeclient.domain.cart.use_cases

import com.example.shoppieeclient.domain.cart.models.CartTotalModel
import com.example.shoppieeclient.domain.cart.models.IncrementDecrementDeleteResultModel
import com.example.shoppieeclient.domain.cart.repository.ShoppieCartRepo
import com.example.shoppieeclient.utils.Resource
import kotlinx.coroutines.flow.Flow

class GetCartTotalUseCase(
    private val shoppieCartRepo: ShoppieCartRepo
) {
    operator fun invoke(): Flow<Resource<CartTotalModel>> {
        return shoppieCartRepo.getCartTotal()
    }
}