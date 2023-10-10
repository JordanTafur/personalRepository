package com.example.calltest.splash.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
    Box(
        Modifier
            .background(color = Color.White)
            .fillMaxSize()
    )

    Box(
        contentAlignment = Alignment.Center
    ) {
        Column {
            Image(
                painter = painterResource(id = R.drawable.splash_new),
                contentDescription = "Logo",
                modifier = Modifier.size(100.dp)
                    .rotate(45f),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.size(20.dp))

            Text(text = "CALL SIMULATOR", style = TextStyle(fontFamily = FontFamily.Cursive), fontWeight = FontWeight.ExtraBold, color = Color.Black)
        }
    }
}

