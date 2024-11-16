package com.example.shoppieeclient.domain.cart.use_cases

import androidx.paging.PagingData
import com.example.shoppieeclient.domain.cart.models.CartProductModel
import com.example.shoppieeclient.domain.cart.models.CartResultModel
import com.example.shoppieeclient.domain.cart.repository.ShoppieCartRepo
import kotlinx.coroutines.flow.Flow

class GetCartUseCase(
    private val shoppieCartRepo: ShoppieCartRepo
) {
    operator fun invoke(page: Int, limit: Int): Flow<PagingData<CartProductModel>> {
        return shoppieCartRepo.getCartData(page, limit)
    }
}