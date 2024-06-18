package com.example.pomodoro_22.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pomodoro_22.ui.main.ui.theme.Pomodoro22Theme
import com.example.pomodoro_22.ui.main.ui.theme.darkMode
import com.example.pomodoro_22.ui.main.ui.theme.thinLine
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
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(top = 50.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        pomodoro("Pomodoro")
                        Spacer(modifier = Modifier.height(15.dp))
                        Box(
                            modifier = Modifier
                                .width(350.dp) // width of the line
                                .height(1.dp) // height of the line
                                .background(thinLine)
                        )
                        Spacer(modifier = Modifier.weight(1f)) // Spacer to push content to top
                    }
                }
            }
        }
    }
}

@Composable
fun pomodoro(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "$name",
        color = white, // Set text color to white
        fontSize = 32.sp, // Increase text size
        fontWeight = FontWeight.Bold, // Make text bold
        textAlign = TextAlign.Center, // Center the text
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Pomodoro22Theme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 50.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            pomodoro("Pomodoro Timer")
            Spacer(modifier = Modifier.height(8.dp))
            Box(
                modifier = Modifier
                    .width(150.dp) // width of the line
                    .height(1.dp) // height of the line
                    .background(white)
            )
            Spacer(modifier = Modifier.weight(1f)) // Spacer to push content to top
        }
    }
}