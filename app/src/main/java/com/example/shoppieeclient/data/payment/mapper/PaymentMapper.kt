package com.example.shoppieeclient.data.payment.mapper

import com.example.shoppieeclient.data.payment.local.entity.PaymentEntity
import com.example.shoppieeclient.domain.payment.models.PaymentCardModel

fun PaymentCardModel.toPaymentEntity(): PaymentEntity {
    return PaymentEntity(
        id = id,
        cardNumber = cardNumber,
        cardHolderName = cardHolderName,
        expirationDate = expirationDate,
        cvv = cvv
    )
}

fun PaymentEntity.toPaymentCardModel(): PaymentCardModel {
    return PaymentCardModel(
        id = id,
        cardNumber = cardNumber,
        cardHolderName = cardHolderName,
        expirationDate = expirationDate,
        cvv = cvv
    )
}
