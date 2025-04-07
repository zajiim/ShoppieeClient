package com.example.shoppieeclient.data.checkout.repository

import android.app.Activity
import com.example.shoppieeclient.BuildConfig
import com.example.shoppieeclient.domain.checkout.repository.PaymentResultCallback
import com.example.shoppieeclient.domain.checkout.repository.RazorPayPaymentRepository
import com.example.shoppieeclient.R
import com.razorpay.Checkout
import com.razorpay.PaymentResultWithDataListener
import org.json.JSONObject

class RazorPayPaymentRepoImpl: RazorPayPaymentRepository, PaymentResultCallback {
    private var paymentResultCallback: PaymentResultCallback? = null
    override fun startPayment(
        amount: Double,
        activity: Activity,
        description: String
    ) {
        try {
            val checkOut = Checkout()
            val options = JSONObject().apply {
                put("name", "Shoppiee")
                put("description", description)
                put("theme.color", "#3399cc")
                put("currency", "INR")
                put("amount", (amount * 100).toString())
                put("method", JSONObject().apply {
                    put("upi", true)
                    put("qr", true)
                })
                put("upi", JSONObject().apply {
                    put("flow", "intent")
                })
                put("readonly", JSONObject().apply {
                    put("contact", false)
                    put("email", false)
                    put("method", false)
                })
            }
            checkOut.setKeyID(BuildConfig.RAZORPAY_ID)
            if (activity is PaymentResultWithDataListener) {
                checkOut.setImage(R.drawable.ic_cart)
                checkOut.open(activity, options)
            }else {
             throw IllegalArgumentException("Activity must implement PaymentResultWithDataListener")
            }
        } catch (e: Exception) {
            e.printStackTrace()
            paymentResultCallback?.onPaymentError(-1, e.message ?: "Something went wrong")
        }
    }

    override fun setPaymentResultCallback(callback: PaymentResultCallback) {
        this.paymentResultCallback = callback
    }

    override fun onPaymentSuccess(paymentId: String) {
        paymentResultCallback?.onPaymentSuccess(paymentId)
    }

    override fun onPaymentError(errorCode: Int, errorDescription: String) {
        paymentResultCallback?.onPaymentError(errorCode, errorDescription)
    }

    override fun onPaymentCancelled() {
        paymentResultCallback?.onPaymentCancelled()
    }
}