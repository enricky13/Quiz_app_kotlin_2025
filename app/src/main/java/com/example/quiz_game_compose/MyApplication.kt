package com.example.quiz_game_compose
import android.app.Application
import dagger.hilt.android.HiltAndroidApp

// This is necessary so Hilt Knowns that this app has annotations and Dependancy injection
@HiltAndroidApp
class MyApplication: Application()