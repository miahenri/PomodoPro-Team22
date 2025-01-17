package com.example.pomodoro_22.ui.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pomodoro_22.ui.main.DividerLine
import com.example.pomodoro_22.ui.main.ui.theme.*

@Composable
fun CustomTextWithInputField(
    largeText: String,
    placeholderText: String,
    value: String,              // New parameter for value binding
    onValueChanged: (String) -> Unit  // New parameter for handling value change
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .padding(vertical = 10.dp)
    ) {
        Text(
            text = largeText,
            fontSize = 20.sp,  // Font size for the large text
            color = Color.White
        )

        Spacer(modifier = Modifier.height(16.dp))

        // TextField now bound to the value and updates using the onValueChanged callback
        TextField(
            value = value,  // Current value displayed in the TextField
            onValueChange = { onValueChanged(it) },  // Call the onValueChanged function
            placeholder = {
                Text(
                    text = placeholderText,
                    fontSize = 10.sp,  // Smaller font size for the placeholder
                    color = Color.LightGray
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .background(darkMode) // Background color
        )

        Spacer(modifier = Modifier.height(10.dp))

        DividerLine()
    }
}

@Composable
fun NotificationSetting(
    isEnabled: Boolean,
    onToggle: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Benachrichtigungen",
                color = Color.White,
                fontSize = 20.sp
            )

            Switch(
                checked = isEnabled,
                onCheckedChange = onToggle,
                colors = SwitchDefaults.colors(
                    checkedThumbColor = Color.Green,
                    uncheckedThumbColor = Color.Gray,
                    checkedTrackColor = Color.Green.copy(alpha = 0.5f),
                    uncheckedTrackColor = Color.Gray.copy(alpha = 0.5f)
                )
            )
        }
    }
}
