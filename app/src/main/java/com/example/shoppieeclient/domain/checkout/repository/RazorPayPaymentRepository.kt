package com.example.shoppieeclient.domain.checkout.repository

import android.app.Activity
import com.example.shoppieeclient.domain.checkout.model.RazorPayOrderResponseModel
import com.example.shoppieeclient.utils.Resource
import kotlinx.coroutines.flow.Flow

interface RazorPayPaymentRepository {
    fun createOrder(
        amount: Double,
        currency: String,
        addressId: String
    ): Flow<Resource<RazorPayOrderResponseModel>>


    fun startPayment(
        amount: Double,
        activity: Activity,
        currency: String,
        razorPayOrderId: String,
        keyId: String,
        description: String = "Payment for Order"
    )
}