package com.example.contactless.payment.sample.domain

interface PaymentRepository {

    suspend fun tokens(): List<String>

    suspend fun tokenize(cardNumber: String)

}