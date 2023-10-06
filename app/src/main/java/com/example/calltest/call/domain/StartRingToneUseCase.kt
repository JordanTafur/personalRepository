package com.example.calltest.call.domain

import com.example.calltest.utils.MediaPlayerHelper
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class StartRingToneUseCase @Inject constructor(
    private val mediaPlayer: MediaPlayerHelper) {
    operator fun invoke (){
        mediaPlayer.start()
    }
}