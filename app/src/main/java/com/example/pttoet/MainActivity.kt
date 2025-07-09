package com.example.pttoet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.pttoet.ui.theme.PTtoETTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PTtoETTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    PTtoETConverter(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun PTtoETConverter(modifier: Modifier = Modifier) {
    var ptInput by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Enter PT Time (0-23):")
        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = ptInput,
            onValueChange = { ptInput = it },
            placeholder = { Text("e.g. 14") },
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            val hour = ptInput.toIntOrNull()
            result = if (hour != null && hour in 0..23) {
                val etHour = (hour + 3) % 24
                "ET Time: $etHour:00"
            } else {
                "Please enter a valid hour (0–23)"
            }
        }) {
            Text("Convert to ET")
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(result)
    }
}
