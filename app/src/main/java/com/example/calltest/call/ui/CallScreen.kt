package com.example.calltest.call.ui

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.calltest.R

@Composable
fun CallScreen(navController: NavHostController) {
    val callViewModel: CallViewModel = hiltViewModel()
    val countdownFinished by callViewModel.countdownFinished.observeAsState(false)
    val finish by callViewModel.finish.observeAsState(false)

    if (countdownFinished || finish) {
        Finish(callViewModel, navController)
    } else {
        CountToBack(callViewModel)
        RingTone(callViewModel)
        CallScreen2(callViewModel, navController)
    }
}

@Composable
fun Finish(callViewModel: CallViewModel, navController: NavHostController) {
    DisposableEffect(Unit) {
        navController.popBackStack()
        onDispose {
            callViewModel.reset()
        }
    }
}

@Composable
fun CountToBack(
    callViewModel: CallViewModel
) {
    LaunchedEffect(Unit) {
        callViewModel.startCountdown()
    }
}

@Composable
fun RingTone(callViewModel: CallViewModel) {
    callViewModel.startRingTone()
}

@Composable
fun CallScreen2(callViewModel: CallViewModel, navController: NavHostController) {
    val showModal by callViewModel.showModal.observeAsState(false)
    val isAnswerButtonReachedTop by callViewModel.isAnswerButtonReachedTop.observeAsState(false)

    var hangUpIconOffsetY by remember { mutableFloatStateOf(0f) }
    var answerIconOffsetY by remember { mutableFloatStateOf(0f) }

    Image(
        painter = painterResource(id = R.drawable.call_screen),
        contentDescription = "callScreen",
        contentScale = ContentScale.Crop,
        modifier = Modifier.fillMaxSize()
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 60.dp)
            .padding(bottom = 60.dp)
            .padding(top = 350.dp), contentAlignment = Alignment.BottomCenter
    ) {
        val boxHeight =
            with(LocalDensity.current) { (80.dp).toPx() }// Hasta donde suben los iconos

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom,
            modifier = Modifier.fillMaxSize()
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Icon(
                    imageVector = Icons.Default.Phone,
                    contentDescription = "Colgar",
                    modifier = Modifier
                        .size(80.dp)
                        .offset(y = answerIconOffsetY.dp)
                        .pointerInput(Unit) {
                            detectVerticalDragGestures { _, dragAmount ->
                                val newY = answerIconOffsetY + dragAmount
                                answerIconOffsetY = newY.coerceIn(
                                    -boxHeight, 0f
                                ) // Limita el deslizamiento vertical
                                if (newY < -boxHeight) {
                                    callViewModel.showModal()
                                    callViewModel.isHangButtonReachedTop()
                                }
                            }
                        }
                        .clip(CircleShape)
                        .border(3.dp, Color.White, CircleShape)
                        .background(Color.Red)
                        .rotate(135F)
                        .padding(10.dp),
                    tint = Color.White,
                )

                Icon(
                    imageVector = Icons.Default.Phone,
                    contentDescription = "Contestar",
                    modifier = Modifier
                        .size(80.dp)
                        .offset(y = hangUpIconOffsetY.dp)
                        .pointerInput(Unit) {
                            detectVerticalDragGestures { _, dragAmount ->
                                val newY = hangUpIconOffsetY + dragAmount
                                hangUpIconOffsetY = newY.coerceIn(
                                    -boxHeight, 0f
                                )// Limita el deslizamiento vertical
                                Log.d("Debug", "newY: $newY, boxHeight: $boxHeight")
                                if (newY < -boxHeight) {
                                    callViewModel.showModal()
                                    callViewModel.isAnswerButtonReachedTop()
                                }
                            }
                        }
                        .clip(CircleShape)
                        .border(3.dp, Color.White, CircleShape)
                        .background(Color.Green)
                        .padding(10.dp),
                    tint = Color.White,
                )
            }
        }
    }

    if (showModal) {
        ShowModal(isAnswerButtonReachedTop) {
            callViewModel.dismissModal()
            callViewModel.finish()
        }
    }
}

@Composable
fun ShowModal(isHangUpButtonReachedTop: Boolean, onAcceptDialog: () -> Unit) {
    AlertDialog(
        onDismissRequest = {},
        title = { Text(text = "Información") },
        text = {
            Text(text = if (!isHangUpButtonReachedTop) "Colgaste la llamada" else "Contestaste la llamada")
        },
        confirmButton = {
            Button(
                onClick = {
                    onAcceptDialog()
                }
            ) { Text(text = "Aceptar") }
        }
    )
}
