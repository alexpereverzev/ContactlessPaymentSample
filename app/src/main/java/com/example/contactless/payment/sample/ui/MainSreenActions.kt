package com.example.contactless.payment.sample.ui

sealed interface MainScreenActions {

    object CheckConditions: MainScreenActions

    data class TokenizeCard(val number: String): MainScreenActions

}