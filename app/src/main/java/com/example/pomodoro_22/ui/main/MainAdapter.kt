package com.example.pomodoro_22.ui.main

import android.os.CountDownTimer
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pomodoro_22.R
import com.example.pomodoro_22.ui.main.ui.theme.*
import com.example.pomodoro_22.util.totalTimeInMillis

@Composable
fun PomodoroTimer(
    timeInMillis: Long,
    timerRunning: Boolean,
    currentPhase: PomodoroPhase,
    totalTimeForCurrentPhase: Long,  // Pass total time for the current phase
    onStartTimer: () -> Unit,
    onStopTimer: () -> Unit,
    onResetTimer: () -> Unit,
    onSkip: () -> Unit  // Add skip action here
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.padding(vertical = 20.dp)
        ) {
            CircularProgressIndicator(
                progress = if (timeInMillis > 0) timeInMillis.toFloat() / totalTimeForCurrentPhase else 0f,
                modifier = Modifier.size(250.dp),
                strokeWidth = 5.dp,
                color = when (currentPhase) {
                    PomodoroPhase.WORK -> circleRed
                    PomodoroPhase.SHORT_BREAK, PomodoroPhase.LONG_BREAK -> circleBlue
                }
            )

            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = when (currentPhase) {
                        PomodoroPhase.WORK -> "Arbeitsphase"
                        PomodoroPhase.SHORT_BREAK -> "Kurze Pause"
                        PomodoroPhase.LONG_BREAK -> "Lange Pause"
                    },
                    modifier = Modifier.offset(y = (8).dp),
                    style = MaterialTheme.typography.bodyMedium,
                    color = lightGrey
                )

                Text(
                    text = formatTime(timeInMillis),
                    style = MaterialTheme.typography.headlineLarge,
                    fontSize = 60.sp,
                    color = white
                )
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            RoundedButton(
                onClick = { onResetTimer() },
                text = "Reset",
                backgroundColor = resetButton
            )


            // Subtle Skip Button
            SubtleSkipButton(
                onClick = { onSkip() },
                icon = R.drawable.skipicon, // Replace with your skip icon
                contentDescription = "Skip Phase"
            )
            //Spacer(modifier = Modifier.width(140.dp))

            if (!timerRunning) {
                RoundedButton(
                    onClick = { onStartTimer() },
                    text = "Start",
                    backgroundColor = startButton
                )
            } else {
                RoundedButton(
                    onClick = { onStopTimer() },
                    text = "Stop",
                    backgroundColor = stopButton
                )
            }
        }
    }
}

fun formatTime(millis: Long): String {
    val minutes = (millis / 1000) / 60
    val seconds = (millis / 1000) % 60
    return String.format("%02d:%02d", minutes, seconds)
}

@Composable
fun RoundedButton(
    onClick: () -> Unit,
    text: String,
    backgroundColor: Color
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .padding(vertical = 8.dp, horizontal = 16.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor)
    ) {
        Text(text = text, color = Color.White)
    }
}

@Composable
fun PomodoroTitle(name: String, modifier: Modifier = Modifier) {
    Text(
        text = name,
        color = white,
        fontSize = 38.sp,
        fontWeight = FontWeight.Normal,
        modifier = modifier
    )
}

@Composable
fun DividerLine() {
    Box(
        modifier = Modifier
            .width(350.dp)
            .height(1.dp)
            .background(Color.Gray)
    )
}

@Composable
fun RoundedIconButton(
    onClick: () -> Unit,
    icon: Int,
    contentDescription: String
) {
    IconButton(
        onClick = onClick,
        modifier = Modifier.size(56.dp),
        content = {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = contentDescription,
                tint = white
            )
        }
    )
}

@Composable
fun SubtleSkipButton(
    onClick: () -> Unit,
    icon: Int,
    contentDescription: String
) {
    IconButton(
        onClick = onClick,
        modifier = Modifier
            .size(40.dp) // Make the button smaller
            .padding(horizontal = 8.dp), // Add padding for spacing
        content = {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = contentDescription,
                tint = Color.LightGray // Use a light gray color to make it subtle
            )
        }
    )
}