package com.example.pomodoro_22.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pomodoro_22.R
import com.example.pomodoro_22.ui.main.ui.theme.Pomodoro22Theme
import com.example.pomodoro_22.ui.main.ui.theme.darkMode
import com.example.pomodoro_22.ui.main.ui.theme.white

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
                onClick = { /* TODO: Implement action */ },
                icon = R.drawable.addtaskicon,
                contentDescription = "Add Task"
            )

            PomodoroTitle(name = "Pomodoro")

            RoundedIconButton(
                onClick = { /* TODO: Implement action */ },
                icon = R.drawable.settingsicon,
                contentDescription = "Settings"
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        DividerLine()
        Spacer(modifier = Modifier.height(16.dp))
        Spacer(modifier = Modifier.weight(1f))
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