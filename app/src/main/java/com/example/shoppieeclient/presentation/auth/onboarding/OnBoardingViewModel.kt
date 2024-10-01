package com.example.shoppieeclient.presentation.auth.onboarding

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppieeclient.domain.auth.use_cases.onboarding.SaveOnBoardingUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class OnBoardingViewModel(
    private val saveOnBoardingUseCase: SaveOnBoardingUseCase,
): ViewModel() {

    var onBoardingState by mutableStateOf(OnBoardingState())
        private set
    fun onEvent(event: OnBoardingEvent) {
        when(event) {
            OnBoardingEvent.SaveOnBoardingEvent -> {
                saveOnBoardingValue()
            }
        }
    }

    private fun saveOnBoardingValue() = viewModelScope.launch {
        onBoardingState = onBoardingState.copy(isLoading = true)
        saveOnBoardingUseCase(false)
        onBoardingState = onBoardingState.copy(isLoading = false)
        Log.e("tag_onboarding_viewmodel", "saveOnboardingData: ..... ")
    }
}