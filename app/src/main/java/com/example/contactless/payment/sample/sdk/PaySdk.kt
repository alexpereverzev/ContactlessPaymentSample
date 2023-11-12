package com.example.contactless.payment.sample.sdk

class PaySdk {

    private var listener : ResultOperation? = null

    fun init(listener: ResultOperation) {
        this.listener = listener
    }

    fun processApdu(commandApdu: ByteArray?): ByteArray {
        listener?.success()
        return commandApdu ?: byteArrayOf()
    }
}


interface ResultOperation {

    fun success()

    fun error(error: String)
}