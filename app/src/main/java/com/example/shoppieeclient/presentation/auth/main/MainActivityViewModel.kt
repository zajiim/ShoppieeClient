package com.example.shoppieeclient.presentation.auth.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppieeclient.domain.auth.use_cases.onboarding.ReadOnBoardingUseCase
import com.example.shoppieeclient.presentation.navigation.Destination
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

private const val TAG = "MainActivityViewModel"

class MainActivityViewModel(
    private val readOnBoardingUseCase: ReadOnBoardingUseCase
) : ViewModel() {
    private val _splashCondition = MutableStateFlow(true)
    val splashCondition = _splashCondition.asStateFlow()

    private val _startDestination = MutableStateFlow<Destination>(Destination.Onboarding)
    val startDestination = _startDestination.asStateFlow()

    init {
        Log.e(TAG, "MainActivityViewModel invoked")
        viewModelScope.launch {
            Log.e(TAG, "splashVal started: ${_splashCondition.value}", )
            readOnBoardingUseCase().collect { onBoardingValue ->
                Log.e(TAG, "onboarding value ==> $onBoardingValue")
                if (onBoardingValue) {
                    _startDestination.value = Destination.Onboarding
                } else {
                    _startDestination.value = Destination.SignIn
                }
                delay(300)
                _splashCondition.value = false
                Log.e(TAG, "splashVal: ended ${_splashCondition.value}", )
            }
        }
    }


}