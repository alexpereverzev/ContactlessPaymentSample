package com.example.contactless.payment.sample.sdk

import android.content.Context
import com.example.contactless.payment.sample.ui.ResultActivity
import dagger.hilt.android.qualifiers.ApplicationContext

class SdkResultOperationImpl(
    @ApplicationContext private val context: Context
): ResultOperation {
    override fun success() {
        context.startActivity(ResultActivity.newInstance(context))
    }

    override fun error(error: String) {
        context.startActivity(ResultActivity.newInstance(context, error))
    }
}