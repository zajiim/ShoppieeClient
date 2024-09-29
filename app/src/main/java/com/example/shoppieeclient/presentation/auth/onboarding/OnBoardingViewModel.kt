package com.example.shoppieeclient.presentation.auth.onboarding

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppieeclient.domain.auth.use_cases.ReadOnBoardingUseCase
import com.example.shoppieeclient.domain.auth.use_cases.SaveOnBoardingUseCase
import kotlinx.coroutines.launch

class OnBoardingViewModel(
    private val saveOnBoardingUseCase: SaveOnBoardingUseCase,
): ViewModel() {
    fun onEvent(event: OnBoardingEvent) {
        when(event) {
            OnBoardingEvent.SaveOnBoardingEvent -> {
                saveOnBoardingValue()
            }
        }
    }

    private fun saveOnBoardingValue() = viewModelScope.launch {
        saveOnBoardingUseCase(false)
        Log.e("tag_onboarding_viewmodel", "saveOnboardingData: ..... ")
    }
}