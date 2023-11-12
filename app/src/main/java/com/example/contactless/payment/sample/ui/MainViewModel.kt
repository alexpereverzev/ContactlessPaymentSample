package com.example.contactless.payment.sample.ui

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.contactless.payment.sample.domain.PaymentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: PaymentRepository,
    private val nfcSettingsChecker: NfcSettingsChecker
) : ViewModel() {

    var state = mutableStateOf<MainScreenState>(MainScreenState.Empty)
        private set

    fun actions(action: MainScreenActions) {
        when (action) {
            MainScreenActions.CheckConditions -> {
                viewModelScope.launch {
                    if (repository.tokens().isEmpty()) {
                        state.value = MainScreenState.TokenizeCard
                    } else if (!nfcSettingsChecker.isNfcEnable) {
                        state.value = MainScreenState.Error("You need enable NFC module")
                    } else if (!nfcSettingsChecker.isPaymentDefaultService) {
                        state.value =
                            MainScreenState.Error("You set PaymentSample service by default")
                    } else {
                        state.value = MainScreenState.Onboarding
                    }
                }

            }

            is MainScreenActions.TokenizeCard -> {
                viewModelScope.launch {
                    try {
                        repository.tokenize(action.number)
                        actions(MainScreenActions.CheckConditions)
                    } catch (e: Exception) {
                        Log.e(TAG, e.message.orEmpty())
                    }
                }

            }
        }
    }

    companion object {
        private const val TAG = "PaymentSample"
    }
}