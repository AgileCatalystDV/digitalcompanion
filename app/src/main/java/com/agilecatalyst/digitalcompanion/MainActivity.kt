package com.agilecatalyst.digitalcompanion

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.agilecatalyst.digitalcompanion.sdk.model.ConnectionState
import com.agilecatalyst.digitalcompanion.sdk.model.RokidDevice
import com.agilecatalyst.digitalcompanion.ui.CompanionUiState
import com.agilecatalyst.digitalcompanion.ui.CompanionViewModel

class MainActivity : ComponentActivity() {
    private val viewModel: CompanionViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DigitalCompanionTheme {
                val uiState = viewModel.uiState
                CompanionScreen(
                    uiState = uiState,
                    onMockModeChanged = viewModel::setMockMode,
                    onScan = viewModel::scanForDevices,
                    onDeviceSelected = viewModel::selectDevice,
                    onConnect = viewModel::connectSelectedDevice,
                    onDisconnect = viewModel::disconnect,
                    onBrightnessChanged = viewModel::setBrightness,
                    onVolumeChanged = viewModel::setVolume,
                    onRefreshBattery = viewModel::refreshBattery,
                )
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
fun CompanionScreen(
    uiState: CompanionUiState,
    onMockModeChanged: (Boolean) -> Unit,
    onScan: () -> Unit,
    onDeviceSelected: (RokidDevice) -> Unit,
    onConnect: () -> Unit,
    onDisconnect: () -> Unit,
    onBrightnessChanged: (Int) -> Unit,
    onVolumeChanged: (Int) -> Unit,
    onRefreshBattery: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        Text("Digital Companion", style = MaterialTheme.typography.headlineMedium)
        Text("Rokid AI Glasses — S26 Mock Prep", style = MaterialTheme.typography.bodyLarge)

        Card(modifier = Modifier.fillMaxWidth()) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text("Mock mode", style = MaterialTheme.typography.titleMedium)
                    Switch(
                        checked = uiState.mockModeEnabled,
                        onCheckedChange = onMockModeChanged,
                    )
                }
                Text(
                    if (uiState.mockModeEnabled) {
                        "APK targets the phone. Rokid calls are simulated."
                    } else {
                        "Production seam is visible, but the real Rokid SDK is not wired yet."
                    },
                    style = MaterialTheme.typography.bodyMedium,
                )
            }
        }

        Card(modifier = Modifier.fillMaxWidth()) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                Text("Device discovery", style = MaterialTheme.typography.titleMedium)
                Button(onClick = onScan) {
                    Text("Scan for Rokid device")
                }
                if (uiState.discoveredDevices.isEmpty()) {
                    Text("No device scanned yet.", style = MaterialTheme.typography.bodyMedium)
                } else {
                    uiState.discoveredDevices.forEach { device ->
                        Button(
                            onClick = { onDeviceSelected(device) },
                            modifier = Modifier.fillMaxWidth(),
                        ) {
                            Text("${device.name} (${device.transport})")
                        }
                    }
                }
                Text(
                    "Selected: ${uiState.selectedDevice?.name ?: "None"}",
                    style = MaterialTheme.typography.bodyMedium,
                )
            }
        }

        Card(modifier = Modifier.fillMaxWidth()) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                Text("Connection", style = MaterialTheme.typography.titleMedium)
                Text(
                    "State: ${uiState.connectionState.name.lowercase().replaceFirstChar { it.uppercase() }}",
                    style = MaterialTheme.typography.bodyMedium,
                )
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Button(
                        onClick = onConnect,
                        enabled = uiState.selectedDevice != null &&
                            uiState.connectionState != ConnectionState.CONNECTED,
                    ) {
                        Text("Connect")
                    }
                    Button(
                        onClick = onDisconnect,
                        enabled = uiState.connectionState == ConnectionState.CONNECTED,
                    ) {
                        Text("Disconnect")
                    }
                }
            }
        }

        Card(modifier = Modifier.fillMaxWidth()) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                Text("Glass controls", style = MaterialTheme.typography.titleMedium)
                Text("Brightness: ${uiState.brightness}")
                Slider(
                    value = uiState.brightness.toFloat(),
                    onValueChange = { onBrightnessChanged(it.toInt()) },
                    valueRange = 0f..100f,
                )
                Text("Volume: ${uiState.volume}")
                Slider(
                    value = uiState.volume.toFloat(),
                    onValueChange = { onVolumeChanged(it.toInt()) },
                    valueRange = 0f..100f,
                )
                Text("Battery: ${uiState.batteryPercent}%")
                Button(onClick = onRefreshBattery) {
                    Text("Refresh battery")
                }
            }
        }

        Card(modifier = Modifier.fillMaxWidth()) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                Text("Status", style = MaterialTheme.typography.titleMedium)
                Text(uiState.statusMessage, style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}
