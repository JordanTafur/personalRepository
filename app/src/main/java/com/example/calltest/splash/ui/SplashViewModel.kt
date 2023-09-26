package com.example.calltest.splash.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class  SplashViewModel @Inject constructor(): ViewModel() {
    var showHomeScreen by mutableStateOf(false)
        private set

    init {
        viewModelScope.launch(Dispatchers.Main) {
            delay(5000)
            showHomeScreen = true
        }
    }
}
