package com.example.calltest.splash.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor() : ViewModel() {
    private val _showHomeScreen = MutableLiveData<Boolean>()
    val showHomeScreen: LiveData<Boolean> = _showHomeScreen

    init {
        viewModelScope.launch(Dispatchers.Main) {
            delay(4000)
            _showHomeScreen.value = true
        }
    }
}
