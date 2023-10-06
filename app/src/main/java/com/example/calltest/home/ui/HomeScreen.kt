package com.example.calltest.home.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.calltest.R
import com.example.calltest.navigation.AppScreens

@Composable
fun HomeScreen(navController: NavHostController) {
    HomeScreenInfo(navController)
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun HomeScreenInfo(navController: NavHostController) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.home_screen),
            contentDescription = "HomeScreen",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Icon(
            imageVector = Icons.Default.Phone,
            contentDescription = "Contestar",
            modifier = Modifier
                .size(100.dp)
                .clip(shape = CircleShape)
                .border(1.dp, Color.White, CircleShape)
                .background(Color.Black)
                .align(Alignment.BottomCenter)
                .padding(16.dp)
                .clickable { navController.navigate(AppScreens.CallScreen.route) },
            tint = Color.White,
            )
    }
}


