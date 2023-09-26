package com.example.calltest.home.ui


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.calltest.R

@Composable
fun HomeScreen() {
    Image(
        painter = painterResource(id = R.drawable.fondo_de_pantalla), contentDescription = "",
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.Crop
    )

    Column(modifier = Modifier.fillMaxSize()) {
        Spacer(modifier = Modifier.weight(1F))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(60.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Column(
                verticalArrangement = Arrangement.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Call,
                    contentDescription = "Colgar",
                    modifier = Modifier
                        .size(80.dp, 80.dp)
                        .rotate(135F),
                    tint = Color.Red,
                )
            }
            Spacer(modifier = Modifier.size(10.dp))
            Column(
                verticalArrangement = Arrangement.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Call,
                    contentDescription = "Contestar",
                    modifier = Modifier.size(80.dp, 80.dp),
                    tint = Color.Green
                )
            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}

