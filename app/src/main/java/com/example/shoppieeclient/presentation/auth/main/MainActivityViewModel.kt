package com.example.shoppieeclient.presentation.auth.main

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppieeclient.domain.auth.use_cases.auth.siginin.ReadAppTokenUseCase
import com.example.shoppieeclient.domain.auth.use_cases.auth.siginin.ReadUserDetailsUseCase
import com.example.shoppieeclient.domain.auth.use_cases.auth.siginin.SaveTokenUseCase
import com.example.shoppieeclient.domain.auth.use_cases.auth.signout.SignOutUseCase
import com.example.shoppieeclient.domain.auth.use_cases.onboarding.ReadOnBoardingUseCase
import com.example.shoppieeclient.domain.common.model.UserDetails
import com.example.shoppieeclient.presentation.navigation.graphs.Graphs
import com.example.shoppieeclient.utils.Resource
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
    private val saveAppTokenUseCase: SaveTokenUseCase,
    readUserDetailsUseCase: ReadUserDetailsUseCase,
    private val signOutUseCase: SignOutUseCase
) : ViewModel() {
    private val _splashCondition = MutableStateFlow(true)
    val splashCondition = _splashCondition.asStateFlow()

    var startDestination by mutableStateOf<Graphs>(Graphs.OnBoarding)
        private set

    var states by mutableStateOf(State())
        private set

    val userDetails: StateFlow<UserDetails> = readUserDetailsUseCase().stateIn(
        viewModelScope,
        SharingStarted.Lazily,
        UserDetails(name = "", profileImage = "")
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

    fun onEvent(events: MainActivityEvents) {
        when(events) {
            MainActivityEvents.SignOutClicked -> {
                viewModelScope.launch {
                    signOutUseCase().collect { result ->
                        when(result) {
                            is Resource.Error -> {
                                states = states.copy(isLoading = false, isSignedOut = false)
                            }
                            is Resource.Loading -> {
                                states = states.copy(isLoading = true)
                            }
                            is Resource.Success -> {
                                states = states.copy(isLoading = false, isSignedOut = true)
                                saveAppTokenUseCase("")
                                startDestination = Graphs.Auth
                            }
                        }
                    }
                }
            }
        }
    }


}

data class State(
    val isSignedOut: Boolean = false,
    val isLoading: Boolean = false,
)

sealed class MainActivityEvents() {
    data object SignOutClicked : MainActivityEvents()
}