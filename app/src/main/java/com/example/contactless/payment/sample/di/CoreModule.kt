package com.example.contactless.payment.sample.di

import android.content.Context
import com.example.contactless.payment.sample.data.DataStoreManager
import com.example.contactless.payment.sample.data.PaymentRepositoryImpl
import com.example.contactless.payment.sample.domain.PaymentRepository
import com.example.contactless.payment.sample.sdk.PaySdk
import com.example.contactless.payment.sample.sdk.ResultOperation
import com.example.contactless.payment.sample.sdk.SdkResultOperationImpl
import com.example.contactless.payment.sample.ui.NfcSettingsChecker
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class CoreModule {

    @Provides
    fun provideSdk(): PaySdk = PaySdk()

    @Provides
    fun provideDataStore(@ApplicationContext context: Context): DataStoreManager =
        DataStoreManager(context)

    @Provides
    fun provideRepository(dataStoreManager: DataStoreManager): PaymentRepository {
        return PaymentRepositoryImpl(dataStoreManager)
    }

    @Provides
    fun provideListenerSdk(@ApplicationContext context: Context): ResultOperation {
        return SdkResultOperationImpl(context)
    }

    @Provides
    fun provideNfcSettingsChecker(@ApplicationContext context: Context): NfcSettingsChecker {
        return NfcSettingsChecker(context)
    }
}