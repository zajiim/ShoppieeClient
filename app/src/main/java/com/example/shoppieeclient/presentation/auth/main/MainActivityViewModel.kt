package com.example.shoppieeclient.presentation.auth.main

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.GraphicsContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppieeclient.domain.auth.use_cases.auth.siginin.ReadAppTokenUseCase
import com.example.shoppieeclient.domain.auth.use_cases.auth.siginin.ReadProfileImageUseCase
import com.example.shoppieeclient.domain.auth.use_cases.auth.siginin.ReadUsernameUseCase
import com.example.shoppieeclient.domain.auth.use_cases.onboarding.ReadOnBoardingUseCase
import com.example.shoppieeclient.presentation.navigation.Destination
import com.example.shoppieeclient.presentation.navigation.graphs.Graphs
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

private const val TAG = "MainActivityViewModel"

class MainActivityViewModel(
    private val readOnBoardingUseCase: ReadOnBoardingUseCase,
    private val readAppTokenUseCase: ReadAppTokenUseCase,
    private val readUsernameUseCase: ReadUsernameUseCase,
    private val readProfileImageUseCase: ReadProfileImageUseCase
) : ViewModel() {
    private val _splashCondition = MutableStateFlow(true)
    val splashCondition = _splashCondition.asStateFlow()

    var startDestination by mutableStateOf<Graphs>(Graphs.OnBoarding)
        private set

    val userName: StateFlow<String?> = readUsernameUseCase().stateIn(
        viewModelScope,
        SharingStarted.Lazily,
        ""
    )

    val userProfileImage: StateFlow<String?> = readProfileImageUseCase().stateIn(
        viewModelScope,
        SharingStarted.Lazily,
        ""
    )


    init {
        Log.e(TAG, "MainActivityViewModel invoked")
        viewModelScope.launch {
            Log.e(TAG, "splashVal started: ${_splashCondition.value}")
            readOnBoardingUseCase().collect { onBoardingValue ->
                Log.e(TAG, "onboarding value ==> $onBoardingValue")
                if (onBoardingValue) {
                    startDestination = Graphs.OnBoarding
//                    startDestination = Destination.Onboarding
                } else {
                    val token = readAppTokenUseCase().firstOrNull()

                    startDestination = if (token.isNullOrEmpty().not()) {
                        Graphs.Home
//                        Destination.Home
                    } else {
                        Graphs.Auth
//                        Destination.SignIn
                    }
                }
                delay(300)
                _splashCondition.value = false
                Log.e(TAG, "splashVal: ended ${_splashCondition.value}")
            }
        }
    }


}