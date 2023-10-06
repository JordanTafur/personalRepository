package com.example.calltest.call.domain.di

import android.content.Context
import com.example.calltest.utils.MediaPlayerHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class MediaPlayerModule{
    @Provides
    @Singleton
    fun provideMediaPlayer(@ApplicationContext context: Context):MediaPlayerHelper{
        return MediaPlayerHelper(context)
    }
}