package com.example.contactless.payment.sample.ui

sealed interface MainScreenState {

    class Error(val text: String) : MainScreenState

    object Empty : MainScreenState

    object TokenizeCard : MainScreenState

    object Onboarding : MainScreenState
}