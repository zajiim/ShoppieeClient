package com.example.shoppieeclient.data.checkout.repository

import android.app.Activity
import android.util.Log
import com.example.shoppieeclient.BuildConfig
import com.example.shoppieeclient.R
import com.example.shoppieeclient.data.checkout.remote.api.ShoppieCheckoutApiService
import com.example.shoppieeclient.data.checkout.remote.dto.CreateOrderRequestDto
import com.example.shoppieeclient.data.checkout.remote.dto.VerifyPaymentRequestDto
import com.example.shoppieeclient.data.checkout.remote.mapper.toOrderResponseModel
import com.example.shoppieeclient.data.checkout.remote.mapper.toPaymentVerificationResponseModel
import com.example.shoppieeclient.domain.checkout.model.PaymentVerificationResponseModel
import com.example.shoppieeclient.domain.checkout.model.RazorPayOrderResponseModel
import com.example.shoppieeclient.domain.checkout.repository.RazorPayPaymentRepository
import com.example.shoppieeclient.utils.Resource
import com.razorpay.Checkout
import com.razorpay.PaymentResultWithDataListener
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.ServerResponseException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.serialization.SerializationException
import org.json.JSONObject
import java.io.IOException

private const val TAG = "RazorPayPaymentRepoImpl"
class RazorPayPaymentRepoImpl(
    private val shoppieCheckoutApiService: ShoppieCheckoutApiService
): RazorPayPaymentRepository {
    override fun createOrder(
        amount: Double,
        currency: String,
        addressId: String
    ): Flow<Resource<RazorPayOrderResponseModel>> = flow {
        try {
            emit(Resource.Loading())
            val createOrderRequest = CreateOrderRequestDto(
                addressId = addressId,
                amount = amount,
                currency = currency
            )

            val orderResponse = shoppieCheckoutApiService.createOrder(
                createOrderRequest
            )
            val result = orderResponse.result
            if (orderResponse.status == 200) {
                val orderResult = result.toOrderResponseModel()
                emit(Resource.Success(orderResult))
            } else {
                emit(Resource.Error("Error: ${orderResponse.message}"))
            }
        } catch (e: ClientRequestException) {
            emit(Resource.Error(e.message))
        } catch (e: ServerResponseException) {
            emit(Resource.Error(e.message))
        } catch (e: SerializationException) {
            emit(Resource.Error(e.message ?: "Unknown error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error(e.message ?: "Unknown error occurred"))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Unknown error occurred"))
        }
    }.catch { e ->
        emit(Resource.Error(e.message ?: "Unexpected error occurred"))
    }.flowOn(Dispatchers.IO)


    override fun startPayment(
        amount: Double,
        activity: Activity,
        currency: String,
        razorPayOrderId: String,
        keyId: String,
        description: String,
    ) {
        try {
            val checkOut = Checkout()

            Log.d(TAG, "Starting payment with: Amount: $amount, Currency: $currency, OrderID: $razorPayOrderId")
            checkOut.setKeyID(keyId)
//            checkOut.setKeyID(BuildConfig.RAZORPAY_ID)

            val options = JSONObject().apply {
                put("name", "Shoppiee")
                put("description", description)
                put("theme.color", "#3399cc")
                put("currency", currency)
                put("amount", (amount * 100).toLong())
                put("order_id", razorPayOrderId)
                put("upi", JSONObject().apply {
                    put("flow", "intent")
                })
                put("readonly", JSONObject().apply {
                    put("contact", false)
                    put("email", false)
                    put("method", false)
                })
            }
            Log.e(TAG, "outside call ", )
            if (activity is PaymentResultWithDataListener) {
                Log.e(TAG, "inside activity is paymentresult with data listener: ", )
                checkOut.open(activity, options)
            }else {
                Log.e(TAG, "throw an error", )
                throw IllegalArgumentException("Activity must implement PaymentResultWithDataListener")
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e(TAG, "Payment start failed: ${e.localizedMessage}")
        }
    }

    override fun verifyPayment(
        orderId: String,
        paymentId: String,
        signature: String,
        razorPayOrderId: String
    ): Flow<Resource<PaymentVerificationResponseModel>> = flow {
        try {
            emit(Resource.Loading())
            val response = shoppieCheckoutApiService.verifyPayment(VerifyPaymentRequestDto(
                orderId = orderId,
                paymentId = paymentId,
                signature = signature,
                razorpayId = razorPayOrderId
                )
            )
            val result = response.result
            if (response.status == 200) {
                val orderResult = result.toPaymentVerificationResponseModel()
                emit(Resource.Success(orderResult))
            } else {
                emit(Resource.Error("Error: ${response.message}"))
            }
        } catch (e: ClientRequestException) {
            emit(Resource.Error(e.message))
        } catch (e: ServerResponseException) {
            emit(Resource.Error(e.message))
        } catch (e: SerializationException) {
            emit(Resource.Error(e.message ?: "Unknown error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error(e.message ?: "Unknown error occurred"))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Unknown error occurred"))
        }
    }.catch { e ->
        emit(Resource.Error(e.message ?: "Unexpected error occurred"))
    }.flowOn(Dispatchers.IO)

}