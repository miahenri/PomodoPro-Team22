package com.example.pomodoro_22.ui.settings

import android.util.Log
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


@Composable
fun SettingsFragment(navController: NavHostController) {
    var notificationsEnabled by remember { mutableStateOf(false) }

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
                    Log.d("TasksScreen", "Back button clicked")
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

        CustomTextWithInputField(
            largeText = "Pomodoros bis zur Pause",
            placeholderText = "Anzahl"
        )

        CustomTextWithInputField(
            largeText = "Arbeitszeit",
            placeholderText = "Dauer in Minuten"
        )

        CustomTextWithInputField(
            largeText = "Kurze Pause",
            placeholderText = "Dauer in Minuten"
        )

        CustomTextWithInputField(
            largeText = "Lange Pause",
            placeholderText = "Dauer in Minuten"
        )

        NotificationSetting(
            isEnabled = notificationsEnabled,
            onToggle = { notificationsEnabled = it }
        )

        Spacer(modifier = Modifier.weight(1f))
    }
}



