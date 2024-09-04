package com.example.pomodoro_22.ui.main

import android.os.CountDownTimer
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.pomodoro_22.R
import com.example.pomodoro_22.ui.main.ui.theme.Pomodoro22Theme
import com.example.pomodoro_22.ui.main.ui.theme.darkMode
import com.example.pomodoro_22.ui.main.ui.theme.lightGrey
import com.example.pomodoro_22.ui.main.ui.theme.resetButton
import com.example.pomodoro_22.ui.main.ui.theme.startButton
import com.example.pomodoro_22.ui.main.ui.theme.stopButton
import com.example.pomodoro_22.ui.main.ui.theme.white
import com.example.pomodoro_22.ui.task.TaskList
import com.example.pomodoro_22.ui.task.TaskViewModel
import com.example.pomodoro_22.util.totalTimeInMillis

@Composable
fun MainScreen(navController: NavHostController, taskViewModel: TaskViewModel) {
    Log.d("MainScreen", "MainScreen Composable created")

    var timerRunning by remember { mutableStateOf(false) }
    var timer: CountDownTimer? by remember { mutableStateOf(null) }
    var timeLeftInMillis by remember { mutableStateOf(25 * 60 * 1000L) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 10.dp)
            .padding(top = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            RoundedIconButton(
                onClick = {
                    Log.d("MainScreen", "Navigating to Task screen")
                    navController.navigate("task_screen")
                },
                icon = R.drawable.addtaskicon,
                contentDescription = "Go to Tasks"
            )

            PomodoroTitle(name = "Pomodoro")

            RoundedIconButton(
                onClick = {
                    Log.d("MainScreen", "Navigating to Settings screen")
                    navController.navigate("settings_screen")
                },
                icon = R.drawable.settingsicon,
                contentDescription = "Settings"
            )
        }

        // Timer operations
        PomodoroTimer(
            timeInMillis = timeLeftInMillis,
            timerRunning = timerRunning,
            onStartTimer = {
                Log.d("PomodoroTimer", "Start button clicked, starting timer")
                timer?.cancel()
                timer = startTimer(timeLeftInMillis) { millisUntilFinished ->
                    timeLeftInMillis = millisUntilFinished
                    Log.d("PomodoroTimer", "Timer tick: $timeLeftInMillis milliseconds left")
                }
                timerRunning = true
            },
            onStopTimer = {
                Log.d("PomodoroTimer", "Stop button clicked, stopping timer")
                timer?.cancel()
                timerRunning = false
            }
        ) {
            Log.d("PomodoroTimer", "Reset button clicked, resetting timer")
            timer?.cancel()
            timeLeftInMillis = 25 * 60 * 1000L
            timerRunning = false
        }

        // Log tasks being displayed and any updates
        TaskList(
            tasks = taskViewModel.tasks.observeAsState(emptyList()).value,
            onTaskClick = { updatedTask ->
                Log.d("MainScreen", "Task clicked: ${updatedTask.id}")
                taskViewModel.updateTask(updatedTask)
            },
            onTaskDelete = { task ->
                Log.d("MainScreen", "Task deleted: ${task.id}")
                taskViewModel.deleteTask(task)
            }
        )
    }
}

@Composable
fun WorkPhases() {
    // TODO: Implement work phases here
}


@Composable
fun PomodoroTimer(
    timeInMillis: Long,
    timerRunning: Boolean,
    onStartTimer: () -> Unit,
    onStopTimer: () -> Unit,
    onResetTimer: () -> Unit
) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.padding(vertical = 20.dp)
        ) {
            CircularProgressIndicator(
                progress = if (timeInMillis > 0) timeInMillis.toFloat() / totalTimeInMillis else 0f,
                modifier = Modifier.size(250.dp),
                strokeWidth = 5.dp,
                color = Color.Red
            )

            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Arbeitsphase", // TODO Hier variable für die aktuelle Arbeitsphase einfügen
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

                Text(
                    text = "Runde: 2", // TODO Hier Variable für die aktuelle Runde einfügen
                    modifier = Modifier.offset(y = (-8).dp),
                    style = MaterialTheme.typography.bodyMedium,
                    color = lightGrey
                )
            }
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

            Spacer(modifier = Modifier.width(140.dp))

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
            // Aktion nachdem der Timer abgelaufen ist hier noch implementieren
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


//Preview Funktionen
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
            timerRunning = false,
            onStartTimer = {},
            onStopTimer = {}
        ) {}
    }
}