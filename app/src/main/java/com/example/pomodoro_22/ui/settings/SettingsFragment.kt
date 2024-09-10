package com.example.pomodoro_22.ui.settings

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.pomodoro_22.R
import com.example.pomodoro_22.ui.main.DividerLine
import com.example.pomodoro_22.ui.main.PomodoroTitle
import com.example.pomodoro_22.ui.main.RoundedIconButton
import com.example.pomodoro_22.ui.main.MainViewModel

@Composable
fun SettingsFragment(
    navController: NavHostController,
    settingsViewModel: SettingsViewModel,
    mainViewModel: MainViewModel
) {
    var workSessions by remember { mutableStateOf(settingsViewModel.workSessionCount.value.toString()) }
    var workTime by remember { mutableStateOf(settingsViewModel.workTimeMinutes.value.toString()) }
    var shortBreakTime by remember { mutableStateOf(settingsViewModel.shortBreakTimeMinutes.value.toString()) }
    var longBreakTime by remember { mutableStateOf(settingsViewModel.longBreakTimeMinutes.value.toString()) }

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
                    // Save settings and reset the timer
                    settingsViewModel.saveSettings(
                        workSessions.toIntOrNull() ?: 4,
                        workTime.toIntOrNull() ?: 25,
                        shortBreakTime.toIntOrNull() ?: 5,
                        longBreakTime.toIntOrNull() ?: 15
                    )
                    mainViewModel.resetTimer()  // Reset the timer with new settings
                    navController.popBackStack()
                },
                icon = R.drawable.arrowbackicon,
                contentDescription = "Go back"
            )

            PomodoroTitle(name = "Einstellungen")

            Spacer(modifier = Modifier.size(56.dp))
        }

        DividerLine()
        Spacer(modifier = Modifier.height(16.dp))

        // Input fields for customizable settings
        CustomTextWithInputField(
            largeText = "Pomodoros bis zur Pause",
            placeholderText = workSessions,
            value = workSessions,
            onValueChanged = { workSessions = it }
        )

        CustomTextWithInputField(
            largeText = "Arbeitszeit (Minuten)",
            placeholderText = workTime,
            value = workTime,
            onValueChanged = { workTime = it }
        )

        CustomTextWithInputField(
            largeText = "Kurze Pause (Minuten)",
            placeholderText = shortBreakTime,
            value = shortBreakTime,
            onValueChanged = { shortBreakTime = it }
        )

        CustomTextWithInputField(
            largeText = "Lange Pause (Minuten)",
            placeholderText = longBreakTime,
            value = longBreakTime,
            onValueChanged = { longBreakTime = it }
        )
    }
}