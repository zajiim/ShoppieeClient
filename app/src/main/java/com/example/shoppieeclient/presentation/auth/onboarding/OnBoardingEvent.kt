package com.example.shoppieeclient.presentation.auth.onboarding

sealed class OnBoardingEvent {
    data object SaveOnBoardingEvent : OnBoardingEvent()
}
