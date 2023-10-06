package com.example.calltest.navigation

sealed class AppScreens(val route: String) {
    object SplashScreen: AppScreens("splash_screen")
    object HomeScreen: AppScreens("home_screen")
    object CallScreen: AppScreens("call_screen")
}