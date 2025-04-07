package com.example.shoppieeclient.presentation.home.checkout

import com.example.shoppieeclient.domain.address.models.AddressModel
import com.example.shoppieeclient.domain.payment.models.PaymentCardModel
import org.koin.viewmodel.emptyState

data class CheckOutStates(
    val selectedCard: PaymentCardModel? = null,
    val selectAddress: List<AddressModel>? = null,
    val isLoading: Boolean = false,
    val subTotal: Double = 0.0,
    val platformFees: Double = 0.0,
    val totalCost: Double = 0.0,
    val error: String? = null,
    val paymentStatus: PaymentStatus = PaymentStatus.IDLE,
    val paymentId: String? = null
)



enum class PaymentStatus {
    IDLE, PROCESSING, SUCCESS, FAILED, CANCELLED
}