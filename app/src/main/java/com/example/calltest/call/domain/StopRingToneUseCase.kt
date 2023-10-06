package com.example.calltest.call.domain

import com.example.calltest.utils.MediaPlayerHelper
import javax.inject.Inject

class StopRingToneUseCase @Inject constructor(
    private val mediaPlayer: MediaPlayerHelper
) {
    operator fun invoke(){
        mediaPlayer.stop()
    }
}