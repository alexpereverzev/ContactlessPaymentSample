package com.example.contactless.payment.sample.ui

import android.content.ComponentName
import android.content.Context
import android.nfc.NfcAdapter
import android.nfc.cardemulation.CardEmulation
import com.example.contactless.payment.sample.service.PaymentService
import dagger.hilt.android.qualifiers.ApplicationContext

class NfcSettingsChecker(
    @ApplicationContext private val context: Context
) {

    private val nfcAdapter: NfcAdapter? = NfcAdapter.getDefaultAdapter(context)
    private val cardEmulation: CardEmulation? = nfcAdapter?.let { CardEmulation.getInstance(nfcAdapter) }
    private val paymentServiceComponent = ComponentName(context, PaymentService::class.java.canonicalName.orEmpty())

    val isNfcEnable: Boolean = nfcAdapter?.isEnabled ?: false

    val isPaymentDefaultService: Boolean = cardEmulation?.isDefaultServiceForCategory(paymentServiceComponent, CardEmulation.CATEGORY_PAYMENT) ?: false
}