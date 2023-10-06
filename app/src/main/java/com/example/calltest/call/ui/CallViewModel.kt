package com.example.calltest.call.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.calltest.call.domain.StartRingToneUseCase
import com.example.calltest.call.domain.StopRingToneUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CallViewModel @Inject constructor(
    private val startRingToneUseCase: StartRingToneUseCase,
    private val stopRingToneUseCase: StopRingToneUseCase
) : ViewModel() {
    private var job: Job? = null
    private var countdownSeconds by mutableIntStateOf(5)

    private var _countdownFinished = MutableLiveData<Boolean>()
    val countdownFinished: LiveData<Boolean> = _countdownFinished

    private var _showModal = MutableLiveData<Boolean>()
    val showModal: LiveData<Boolean> = _showModal

    private var _isAnswerButtonReachedTop = MutableLiveData<Boolean>()
    val isAnswerButtonReachedTop: LiveData<Boolean> = _isAnswerButtonReachedTop

    private var _finish = MutableLiveData<Boolean>()
    val finish: LiveData<Boolean> = _finish

    fun startCountdown() {
         job = viewModelScope.launch {
            while (countdownSeconds > 0) {
                delay(1000) // Espera un segundo
                countdownSeconds--
            }

            _countdownFinished.value = true
        }
    }

    fun stopCountdown() {
        job?.cancel()
    }

    fun startRingTone() {
        startRingToneUseCase()
    }

    fun stopRingTone(){
        stopRingToneUseCase()
    }

    fun showModal() {
        _showModal.value = true
    }

    fun dismissModal() {
        _showModal.value = false
    }

    fun isAnswerButtonReachedTop() {
        _isAnswerButtonReachedTop.value = true
    }

    fun isHangButtonReachedTop() {
        _isAnswerButtonReachedTop.value = false
    }

    fun finish() {
        _finish.value = true
    }
}
