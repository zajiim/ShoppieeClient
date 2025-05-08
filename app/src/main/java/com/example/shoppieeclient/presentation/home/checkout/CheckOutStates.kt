package com.example.shoppieeclient.presentation.home.checkout

import com.example.shoppieeclient.domain.address.models.AddressModel
import com.example.shoppieeclient.domain.payment.models.PaymentCardModel
import org.koin.viewmodel.emptyState

data class CheckOutStates(
    val selectedCard: PaymentCardModel? = null,
    val selectedAddress: List<AddressModel>? = emptyList(),
    val isLoading: Boolean = false,
    val subTotal: Double = 0.0,
    val platformFees: Double = 0.0,
    val totalCost: Double = 0.0,
    val error: String? = null,
    val paymentId: String? = null,
    val orderId: String? = null,
    val razorPayOrderId: String? = null
)
