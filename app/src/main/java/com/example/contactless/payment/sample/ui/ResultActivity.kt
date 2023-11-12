package com.example.contactless.payment.sample.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.contactless.payment.sample.R
import com.example.contactless.payment.sample.ui.theme.PaymentSampleTheme

class ResultActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        val textError = intent.getStringExtra(PARAM_TEXT)
        setContent {
            PaymentSampleTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ResultScreen(textError)
                }
            }
        }
    }

    @Composable
    fun ResultScreen(errorText: String? = null) {
        if (errorText != null) {
            Column(
                modifier = Modifier.wrapContentHeight().padding(32.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painterResource(R.drawable.alert_circle_outline),
                    alignment = Alignment.Center,
                    contentDescription = "",

                    modifier = Modifier.size(96.dp)
                )

                Text(
                    text = errorText,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center
                )
            }
        } else {
            Image(
                painterResource(R.drawable.check_circle_outline),
                alignment = Alignment.Center,
                contentDescription = "",
                modifier = Modifier.wrapContentHeight().size(96.dp)
            )
        }
    }

    companion object {
        private const val PARAM_TEXT = "param_text"
        fun newInstance(context: Context, errorText: String? = null): Intent {
            return Intent(context, ResultActivity::class.java).apply {
                errorText?.let {
                    putExtra(PARAM_TEXT, it)
                }
            }

        }
    }
}