package com.example.shoppieeclient.data.checkout.remote.mapper

import com.example.shoppieeclient.data.checkout.remote.dto.PaymentVerificationResultDto
import com.example.shoppieeclient.data.checkout.remote.dto.RazorPayOrderResponseModelDto
import com.example.shoppieeclient.domain.checkout.model.PaymentVerificationResponseModel
import com.example.shoppieeclient.domain.checkout.model.RazorPayOrderResponseModel

fun RazorPayOrderResponseModelDto.toOrderResponseModel(): RazorPayOrderResponseModel {
    return RazorPayOrderResponseModel(
        orderId = this.orderId,
        razorpayId = this.razorpayId,
        amount = this.amount,
        currency = this.currency,
        keyId = this.keyId
    )
}


fun RazorPayOrderResponseModel.toOrderResponseModelDto(): RazorPayOrderResponseModelDto {
    return RazorPayOrderResponseModelDto(
        orderId = this.orderId,
        razorpayId = this.razorpayId,
        amount = this.amount,
        currency = this.currency,
        keyId = this.keyId
    )
}

fun PaymentVerificationResultDto.toPaymentVerificationResponseModel(): PaymentVerificationResponseModel {
    return PaymentVerificationResponseModel(
        orderId = this.orderId,
        paymentId = this.paymentId,
        razorpayId = this.razorpayId
    )
}

