package com.example.contactless.payment.sample.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.contactless.payment.sample.R
import com.example.contactless.payment.sample.ui.theme.PaymentSampleTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PaymentSampleTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen(viewModel.state.value, tokenizeCardAction = { inputText ->
                        viewModel.actions(MainScreenActions.TokenizeCard(inputText))
                    })
                }
            }
        }
    }



    override fun onResume() {
        super.onResume()
        println("resume")
        viewModel.actions(MainScreenActions.CheckConditions)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    state: MainScreenState,
    tokenizeCardAction: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    when (state) {
        MainScreenState.Empty -> {

        }

        is MainScreenState.Error -> {
            Text(
                text = state.text,
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                modifier = modifier
                    .wrapContentHeight(),
            )
        }

        MainScreenState.Onboarding -> {
            Text(
                text = "Try to tap on terminal",
                fontSize = 24.sp,
                textAlign = TextAlign.Center,
                modifier = modifier
                    .wrapContentHeight(),
            )
        }

        MainScreenState.TokenizeCard -> {
            val cardNumberText = remember { mutableStateOf("") }
            val buttonEnable = remember { mutableStateOf(false) }
            val focusManager = LocalFocusManager.current
            val focusRequester = remember { FocusRequester() }
            Column(
                modifier = modifier.wrapContentHeight().padding(32.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {


                OutlinedTextField(
                    value = cardNumberText.value,
                    {
                        run {
                            cardNumberText.value = it
                            buttonEnable.value = it.isNotEmpty()
                        }
                    },
                    singleLine = true,

                    placeholder = {
                        Text(
                            text = "card number"
                        )
                    },
                    keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),

                    modifier = Modifier
                        .focusRequester(focusRequester)
                        .fillMaxWidth()
                )


                Button(onClick = {
                    tokenizeCardAction.invoke(cardNumberText.value)
                }, enabled = buttonEnable.value) {
                    Text(
                        text = "Tokenize",
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            fontSize = 20.sp
                        )
                    )

                }
            }

        }
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PaymentSampleTheme {
        MainScreen(MainScreenState.Error("test"), {})
    }
}