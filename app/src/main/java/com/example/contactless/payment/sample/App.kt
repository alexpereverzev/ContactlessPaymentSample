package com.example.contactless.payment.sample

import android.app.Application
import com.example.contactless.payment.sample.sdk.PaySdk
import com.example.contactless.payment.sample.sdk.ResultOperation
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class App : Application() {

    @Inject
    lateinit var sdk: PaySdk

    @Inject
    lateinit var resultListener: ResultOperation

    override fun onCreate() {
        super.onCreate()
        sdk.init(resultListener)
    }

}