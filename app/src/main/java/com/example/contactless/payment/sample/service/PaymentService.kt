package com.example.contactless.payment.sample.service

import android.nfc.cardemulation.HostApduService
import android.os.Bundle
import com.example.contactless.payment.sample.sdk.PaySdk
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PaymentService : HostApduService() {

    @Inject
    lateinit var sdk: PaySdk

    override fun processCommandApdu(commandApdu: ByteArray?, extras: Bundle?): ByteArray {
        return sdk.processApdu(commandApdu)
    }

    override fun onDeactivated(reason: Int) {

    }
}