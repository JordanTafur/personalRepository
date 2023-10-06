package com.example.calltest.utils

import android.content.Context
import android.media.MediaPlayer
import com.example.calltest.R

class MediaPlayerHelper(private val  context: Context) {
    private var mediaPlayer: MediaPlayer? = null
    fun start(){
        mediaPlayer?.start() ?: run {
            mediaPlayer = MediaPlayer.create(context, R.raw.ringtone_iphone_xv_plus)
            mediaPlayer?.start()
        }
    }

    fun stop(){
        mediaPlayer?.stop()
        mediaPlayer = null
    }
}