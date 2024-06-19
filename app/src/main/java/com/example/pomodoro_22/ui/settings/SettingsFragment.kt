package com.example.pomodoro_22.ui.settings

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.pomodoro_22.R
import com.example.pomodoro_22.ui.main.DividerLine
import com.example.pomodoro_22.ui.main.PomodoroTitle
import com.example.pomodoro_22.ui.main.RoundedIconButton

@Composable
fun SettingsScreen(navController: NavHostController) {
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
                    Log.d("SettingsScreen", "Back button clicked")
                    navController.popBackStack() // Navigate back to the previous screen
                },
                icon = R.drawable.arrowbackicon,
                contentDescription = "Go back"
            )

            PomodoroTitle(name = "Einstellungen")

            RoundedIconButton(
                onClick = {
                    Log.d("SettingsScreen", "Back button clicked")
                    navController.popBackStack() // Navigate back to the previous screen
                },
                icon = R.drawable.arrowbackicon,
                contentDescription = "Go back"
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        DividerLine()
        Spacer(modifier = Modifier.height(16.dp))

        // Add your settings content here...

        Spacer(modifier = Modifier.weight(1f))
    }
}