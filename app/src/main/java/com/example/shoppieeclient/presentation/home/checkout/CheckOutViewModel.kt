package com.example.shoppieeclient.presentation.home.checkout

import android.app.Activity
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppieeclient.domain.address.use_cases.GetSelectedAddressUseCase
import com.example.shoppieeclient.domain.cart.use_cases.GetCartTotalUseCase
import com.example.shoppieeclient.domain.checkout.use_cases.CreateOrderUseCase
import com.example.shoppieeclient.domain.checkout.use_cases.PaymentVerificationUseCase
import com.example.shoppieeclient.domain.checkout.use_cases.StartRPPaymentUseCase
import com.example.shoppieeclient.domain.payment.use_cases.GetSelectedCardUseCase
import com.example.shoppieeclient.utils.Resource
import com.example.shoppieeclient.utils.roundToTwoDecimalPlaces
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.lang.ref.WeakReference

private const val TAG = "CheckOutViewModel"

class CheckOutViewModel(
    private val getSelectedCardUseCase: GetSelectedCardUseCase,
    private val getSelectedAddressUseCase: GetSelectedAddressUseCase,
    private val getCartTotalUseCase: GetCartTotalUseCase,
    private val createOrderUseCase: CreateOrderUseCase,
    private val startRPPaymentUseCase: StartRPPaymentUseCase,
    private val verifyPaymentUseCase: PaymentVerificationUseCase,
) : ViewModel() {


    var checkOutState by mutableStateOf(CheckOutStates())
        private set

    private var currentActivity: WeakReference<Activity>? = null

    fun setActivity(activity: Activity?) {
        currentActivity = WeakReference(activity)
    }

    init {
        getSelectedCard()
        getSelectedAddress()
        calculateTotals()
    }

    fun refreshData() {
        getSelectedAddress()
        getSelectedCard()
    }

    fun onEvent(events: CheckoutEvents) {
        when (events) {
            is CheckoutEvents.CreateOrder -> {
                setActivity(events.activity)
                Log.e(TAG, "currecy = ${events.currency}")
                createOrder(
                    addressId = events.addressId,
                    amount = events.amount,
                    currency = events.currency
                )
            }

            is CheckoutEvents.PaymentSuccess -> {
                Log.e(TAG, "success called")
                checkOutState = checkOutState.copy(
                    isLoading = false,
                    paymentId = events.paymentId,
                    paymentSignature = events.signature
                )
                Log.e(TAG, "payment id ===> ${checkOutState.paymentId}")
                checkOutState.paymentId?.let { paymentId ->
                    Log.e(TAG, "paymentId ===> $paymentId")
                    checkOutState.paymentSignature?.let { signature ->
                        Log.e(TAG, "signature ===> $signature")
                        Log.e(TAG, "orderId ===> ${checkOutState.razorPayOrderId}")
                        verifyPayment(paymentId, signature)
                    }
                }
            }

            is CheckoutEvents.PaymentError -> {
                Log.e(TAG, "failure called")
                checkOutState = checkOutState.copy(
                    isLoading = false,
                    error = events.message,
                )
            }
        }
    }

    private fun verifyPayment(
        paymentId: String,
        signature: String
    ) {
        val orderId = checkOutState.orderId
        val razorpayOrderId = checkOutState.razorPayOrderId
        Log.e(
            TAG,
            "verifyPayment: razorpayOrderId = $razorpayOrderId 2. ${checkOutState.razorPayOrderId}",
        )
        if (orderId == null || razorpayOrderId == null) {
            Log.e(TAG, "verifyPayment: razorpayOrderId  error")
            checkOutState = checkOutState.copy(
                error = "Order ID or Razorpay Order ID is null",
            )
            return
        }

        viewModelScope.launch {
            verifyPaymentUseCase(
                orderId = orderId,
                paymentId = paymentId,
                signature = signature,
                razorpayId = razorpayOrderId
            ).collect { result ->
                Log.e(TAG, "verifyPayment: result = $result")
                when (result) {
                    is Resource.Loading -> {
                        checkOutState = checkOutState.copy(isLoading = true)
                    }

                    is Resource.Error -> {
                        checkOutState = checkOutState.copy(
                            isLoading = false,
                            error = result.message
                        )
                    }

                    is Resource.Success -> {
                        checkOutState = checkOutState.copy(
                            isLoading = false,
                            paymentVerified = true,
                            error = null
                        )
                    }
                }
            }
        }
    }


    fun createOrder(
        addressId: String,
        amount: Double,
        currency: String
    ) = viewModelScope.launch {
        createOrderUseCase(
            addressId = addressId,
            amount = amount,
            currency = currency
        ).collect { result ->
            when (result) {
                is Resource.Loading -> {
                    checkOutState = checkOutState.copy(isLoading = true)
                }

                is Resource.Success -> {
                    val orderResult = result.data
                    if (orderResult != null) {
                        Log.e(TAG, "orderResiult is not null: ")
                        checkOutState = checkOutState.copy(
                            orderId = orderResult.orderId,
                            razorPayOrderId = orderResult.razorpayId
                        )
                        Log.e(TAG, "State after update - orderId: ${checkOutState.orderId}, razorId: ${checkOutState.razorPayOrderId}")
                        val activity = currentActivity?.get()
                        Log.e(TAG, "activity is $activity")
                        if (activity != null) {
                            Log.e(TAG, "orderResult ---> $orderResult ")
                            startRPPaymentUseCase(
                                amount = orderResult.amount / 100.0,
                                activity = activity,
                                currency = orderResult.currency,
                                keyId = orderResult.keyId,
                                razorPayOrderId = orderResult.razorpayId,
                                description = "Payment for Order"
                            )
                        } else {
                            Log.e(TAG, "activity null: ")
                            checkOutState = checkOutState.copy(
                                isLoading = false,
                                error = "Activity is not available"
                            )
                        }
                    } else {
                        Log.e(TAG, "orderResiult is null: ")
                        checkOutState = checkOutState.copy(
                            isLoading = false,
                            error = "Failed to create order"
                        )
                    }
                }

                is Resource.Error -> {
                    checkOutState = checkOutState.copy(
                        isLoading = false,
                        error = result.message
                    )
                }
            }
        }
    }


     fun getSelectedAddress() = viewModelScope.launch {
        getSelectedAddressUseCase().collectLatest { result ->
            when (result) {
                is Resource.Loading -> {
                    checkOutState = checkOutState.copy(isLoading = true)
                }

                is Resource.Success -> {
                    checkOutState = checkOutState.copy(
                        selectedAddress = result.data,
                        isLoading = false
                    )
                }

                is Resource.Error -> {
                    checkOutState = checkOutState.copy(
                        selectedAddress = emptyList(),
                        isLoading = false,
                        error = result.message
                    )
                }
            }
        }
    }

    private fun getSelectedCard() = viewModelScope.launch {
        getSelectedCardUseCase().collectLatest { selectedCard ->
            checkOutState = checkOutState.copy(
                selectedCard = selectedCard
            )
        }
        Log.e(TAG, "getSelectedCard: ${checkOutState.selectedCard}")
    }

    private fun calculateTotals() = viewModelScope.launch {
        getCartTotalUseCase().collect { result ->
            when (result) {
                is Resource.Error -> {
                    checkOutState = checkOutState.copy(error = result.message, isLoading = false)
                }

                is Resource.Loading -> {
                    checkOutState = checkOutState.copy(isLoading = true)
                }

                is Resource.Success -> {
                    val cartTotal = result.data
                    checkOutState = checkOutState.copy(
                        isLoading = false,
                        subTotal = cartTotal?.totalPrice?.roundToTwoDecimalPlaces() ?: 0.0,
                        platformFees = cartTotal?.platformFee?.roundToTwoDecimalPlaces() ?: 0.0,
                        totalCost = cartTotal?.grandTotal?.roundToTwoDecimalPlaces() ?: 0.0
                    )
                }
            }
        }
    }
}