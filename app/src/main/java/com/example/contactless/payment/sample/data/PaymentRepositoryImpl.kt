package com.example.contactless.payment.sample.data

import com.example.contactless.payment.sample.domain.PaymentRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext

class PaymentRepositoryImpl(
    private val dataStore: DataStoreManager,
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.IO
) : PaymentRepository {

    override suspend fun tokens(): List<String> {
        return withContext(defaultDispatcher) {
            dataStore.tokens.first().toList()
        }
    }

    override suspend fun tokenize(cardNumber: String) {
        withContext(defaultDispatcher) {
            dataStore.saveTokens(setOf(cardNumber))
        }
    }


}