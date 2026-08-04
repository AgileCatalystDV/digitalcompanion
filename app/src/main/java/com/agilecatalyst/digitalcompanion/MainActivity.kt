package com.agilecatalyst.digitalcompanion

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DigitalCompanionTheme {
                CompanionScreen()
            }
        }
    }
}

@Composable
fun DigitalCompanionTheme(content: @Composable () -> Unit) {
    MaterialTheme {
        Surface {
            content()
        }
    }
}

@Composable
fun CompanionScreen() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Digital Companion", style = MaterialTheme.typography.headlineMedium)
        Text("Rokid Glasses — MVP", style = MaterialTheme.typography.bodyMedium)
        Button(onClick = { /* BLE Scan */ }, modifier = Modifier.padding(top = 16.dp)) {
            Text("BLE Scan")
        }
        Button(onClick = { /* Connect */ }, modifier = Modifier.padding(top = 8.dp)) {
            Text("Connect (mock)")
        }
    }
}
