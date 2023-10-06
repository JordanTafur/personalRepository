package com.example.calltest.splash.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.calltest.R
import com.example.calltest.navigation.AppScreens

@Composable
fun SplashScreen(navController: NavController) {
    val splashViewModel: SplashViewModel = hiltViewModel()
    val showHomeScreen: Boolean by splashViewModel.showHomeScreen.observeAsState(initial = false)

    if (showHomeScreen) {
        navController.popBackStack()
        navController.navigate(AppScreens.HomeScreen.route)
    } else Splash()
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SplashScreenPreview() {
    Splash()
}

@Composable
fun Splash() {
    Image(
        painter = painterResource(id = R.drawable.splashbg),
        contentDescription = "Logo",
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.Crop
    )
}

