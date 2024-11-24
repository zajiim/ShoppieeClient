package com.example.shoppieeclient.domain.cart.use_cases

import com.example.shoppieeclient.domain.cart.models.IncrementDecrementDeleteResultModel
import com.example.shoppieeclient.domain.cart.repository.ShoppieCartRepo
import com.example.shoppieeclient.utils.Resource
import kotlinx.coroutines.flow.Flow

class RemoveItemUseCase(
    private val shoppieCartRepo: ShoppieCartRepo
) {
    operator fun invoke(id: String): Flow<Resource<IncrementDecrementDeleteResultModel>> {
        return shoppieCartRepo.removeFromCart(productId = id)
    }
}