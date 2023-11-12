package com.example.contactless.payment.sample.data

import androidx.datastore.preferences.core.stringSetPreferencesKey

object PaymentScheme {
    val TOKENS = stringSetPreferencesKey("tokens")
}