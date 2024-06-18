package com.example.pomodoro_22.ui.main

import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pomodoro_22.R
import com.example.pomodoro_22.ui.main.ui.theme.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Pomodoro22Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = darkMode // Grey color
                ) { MainScreen() }
            }
        }
    }
}

@Composable
fun MainScreen() {
    var timerRunning by remember { mutableStateOf(false) }
    var timer: CountDownTimer? by remember { mutableStateOf(null) }
    var timeLeftInMillis by remember { mutableStateOf(25 * 60 * 1000L) }
    var currentTime by remember { mutableStateOf(timeLeftInMillis) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 10.dp)
            .padding(top = 40.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            RoundedIconButton(
                onClick = {
                    Log.d("MainScreen", "Add Task button clicked")
                    // Implement action if needed
                },
                icon = R.drawable.addtaskicon,
                contentDescription = "Got to Tasks"
            )

            PomodoroTitle(name = "Pomodoro")

            RoundedIconButton(
                onClick = {
                    Log.d("MainScreen", "Settings button clicked")
                    // Implement action if needed
                },
                icon = R.drawable.settingsicon,
                contentDescription = "Settings"
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        DividerLine()
        Spacer(modifier = Modifier.height(16.dp))

        // Timer section
        PomodoroTimer(
            timeInMillis = timeLeftInMillis,
            currentTime = currentTime,
            timerRunning = timerRunning,
            onStartTimer = {
                Log.d("MainScreen", "Start button clicked")
                if (timer != null) {
                    timer?.cancel()
                }
                timer = startTimer(timeLeftInMillis) { millisUntilFinished ->
                    timeLeftInMillis = millisUntilFinished
                }
                timerRunning = true
            },
            onStopTimer = {
                Log.d("MainScreen", "Stop button clicked")
                timer?.cancel()
                timerRunning = false
            }

        ) {
            Log.d("MainScreen", "Reset button clicked")
            timer?.cancel()
            timeLeftInMillis = 25 * 60 * 1000L // Setze die Zeit zurück auf 25 Minuten
            currentTime = timeLeftInMillis // Setze currentTime zurück auf 25 Minuten
            timerRunning = false
        }

        Spacer(modifier = Modifier.weight(1f)) // Spacer to push content to top
    }
}

@Composable
fun PomodoroTimer(
    timeInMillis: Long,
    timerRunning: Boolean,
    currentTime: Long,
    onStartTimer: () -> Unit,
    onStopTimer: () -> Unit,
    onResetTimer: () -> Unit
) {
    var timer: CountDownTimer? by remember { mutableStateOf(null) }

    LaunchedEffect(timerRunning) {
        if (timerRunning) {
            timer = startTimer(currentTime) { millisUntilFinished ->
                currentTime = millisUntilFinished
            }
        } else {
            timer?.cancel()
        }
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.padding(vertical = 20.dp)
        ) {
            CircularProgressIndicator(
                progress = if (timeInMillis > 0) currentTime.toFloat() / timeInMillis else 0f,
                modifier = Modifier.size(250.dp),
                strokeWidth = 5.dp,
                color = Color.Red
            )

            Text(
                text = formatTime(currentTime),
                style = MaterialTheme.typography.headlineLarge,
                color = white
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            RoundedButton(
                onClick = {
                    onResetTimer()
                },
                text = "Reset",
                backgroundColor = resetButton
            )

            if (!timerRunning) {
                RoundedButton(
                    onClick = {
                        onStartTimer()
                    },
                    text = "Start",
                    backgroundColor = startButton
                )
            } else {
                RoundedButton(
                    onClick = {
                        onStopTimer()
                    },
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

fun startTimer(totalTimeInMillis: Long, onTick: (Long) -> Unit): CountDownTimer {
    return object : CountDownTimer(totalTimeInMillis, 1000) {
        override fun onTick(millisUntilFinished: Long) {
            onTick(millisUntilFinished)
        }

        override fun onFinish() {
            // Optionally handle timer finish event
        }
    }.start()
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
        color = white, // Set text color to white
        fontSize = 38.sp, // Increase text size
        fontWeight = FontWeight.Normal, // Make text bold
        modifier = modifier
    )
}

@Composable
fun DividerLine() {
    Box(
        modifier = Modifier
            .width(350.dp) // width of the line
            .height(1.dp) // height of the line
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

@Preview(showBackground = true)
@Composable
fun PomodoroTitlePreview() {
    Pomodoro22Theme {
        PomodoroTitle(name = "Pomodoro Timer")
    }
}

@Preview(showBackground = true)
@Composable
fun DividerLinePreview() {
    Pomodoro22Theme {
        DividerLine()
    }
}

@Preview(showBackground = true)
@Composable
fun PomodoroTimerPreview() {
    Surface(
        color = darkMode,
        modifier = Modifier.fillMaxSize()
    ) {
        PomodoroTimer(
            timeInMillis = 25 * 60 * 1000L,
            currentTime = 25 * 60 * 1000L,
            timerRunning = false,
            onStartTimer = {},
            onStopTimer = {}
        ) {}
    }
}