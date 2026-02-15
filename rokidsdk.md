Rokid Glasses SDK - Technical Development Guide
Based on product (Kickstarter):
https://global.rokid.com/pages/rokid-glasses

We want to realy implement it.

Overview
This guide provides a comprehensive technical reference for developing applications using the Rokid Glasses SDK (CXR-M) for Android companion apps. The SDK enables bidirectional communication between Android devices and Rokid Glasses running YodaOS-Sprite.

Architecture
Dual SDK System
CXR-M SDK: Mobile-side development toolkit for Android companion apps
CXR-S SDK: On-device development toolkit for YodaOS-Sprite applications
Communication: Bidirectional protocol over Bluetooth + WiFi P2P
Key Components
Device connection and management
Real-time audio/video streaming
AI interaction pipeline
Media capture and synchronization
Hardware control interface
Development Environment Setup
Prerequisites
Minimum Android SDK: 28
Development Language: Kotlin/Java
Build System: Gradle with Kotlin DSL
Maven Repository Configuration
Add to settings.gradle.kts:

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        maven { url = uri("https://maven.rokid.com/repository/maven-public/") }
        google()
        mavenCentral()
    }
}
Dependencies
Add to build.gradle.kts:

android {
    defaultConfig {
        minSdk = 28
    }
}

dependencies {
    implementation("com.rokid.cxr:client-m:1.0.1-20250812.080117-2")
    
    // Required dependencies
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:okhttp:4.9.3")
    implementation("org.jetbrains.kotlin:kotlin-stdlib:2.1.0")
    implementation("com.squareup.okio:okio:2.8.0")
    implementation("com.google.code.gson:gson:2.10.1")
    implementation("com.squareup.okhttp3:logging-interceptor:4.9.1")
}
Permissions
Add to AndroidManifest.xml:

<uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />
<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
<uses-permission android:name="android.permission.BLUETOOTH" />
<uses-permission android:name="android.permission.BLUETOOTH_ADMIN" />
<uses-permission android:name="android.permission.BLUETOOTH_CONNECT" />
<uses-permission android:name="android.permission.BLUETOOTH_SCAN" />
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
<uses-permission android:name="android.permission.CHANGE_NETWORK_STATE" />
<uses-permission android:name="android.permission.ACCESS_WIFI_STATE" />
<uses-permission android:name="android.permission.CHANGE_WIFI_STATE" />
<uses-permission android:name="android.permission.INTERNET" />
To work with the glasses BLE hardware is a requirement for the application. Optionally we can declare that the application uses BLE features on Android devices. By doing so, users on devices without BLE capabilities won’t see your app on the Google Play Store.

<uses-feature
    android:name="android.hardware.bluetooth_le"
    android:required="true" />
Core API Reference
Device Discovery and Connection
Bluetooth Device Discovery
// Filter Rokid devices using UUID
val ROKID_SERVICE_UUID = "00009100-0000-1000-8000-00805f9b34fb"

// Scan for devices
scanner.startScan(
    listOf(
        ScanFilter.Builder()
            .setServiceUuid(ParcelUuid.fromString(ROKID_SERVICE_UUID))
            .build()
    ),
    ScanSettings.Builder().build(),
    scanCallback
)
Device Initialization
CxrApi.getInstance().initBluetooth(context, device, object : BluetoothStatusCallback {
    override fun onConnectionInfo(
        socketUuid: String?,
        macAddress: String?,
        rokidAccount: String?,
        glassesType: Int // 0=no display, 1=has display
    ) {
        // Connect using received parameters
        socketUuid?.let { uuid ->
            macAddress?.let { address ->
                connectDevice(context, uuid, address)
            }
        }
    }
    
    override fun onConnected() { /* Handle connection */ }
    override fun onDisconnected() { /* Handle disconnection */ }
    override fun onFailed(errorCode: ValueUtil.CxrBluetoothErrorCode?) { /* Handle errors */ }
})
WiFi P2P Connection
// Note: WiFi requires Bluetooth connection first and is high-energy consumption
val status = CxrApi.getInstance().initWifiP2P(object : WifiP2PStatusCallback {
    override fun onConnected() { /* WiFi connected */ }
    override fun onDisconnected() { /* WiFi disconnected */ }
    override fun onFailed(errorCode: ValueUtil.CxrWifiErrorCode?) { /* WiFi failed */ }
})
Hardware Control
Display Control
// Brightness control [0-15]
CxrApi.getInstance().setGlassBrightness(brightness)

// Listen for brightness changes
CxrApi.getInstance().setBrightnessUpdateListener(object : BrightnessUpdateListener {
    override fun onBrightnessUpdated(brightness: Int) {
        // Handle brightness change
    }
})
Audio Control
// Volume control [0-15]
CxrApi.getInstance().setGlassVolume(volume)

// Sound effects
CxrApi.getInstance().setSoundEffect("AdiMode0") // Loud
CxrApi.getInstance().setSoundEffect("AdiMode1") // Rhythm  
CxrApi.getInstance().setSoundEffect("AdiMode2") // Podcast
Power Management
// Screen timeout (seconds)
CxrApi.getInstance().setScreenOffTimeout(seconds)

// Power timeout (minutes)
CxrApi.getInstance().setPowerOffTimeout(minutes)

// Device control
CxrApi.getInstance().notifyGlassReboot()
CxrApi.getInstance().notifyGlassShutdown()
Battery Monitoring
CxrApi.getInstance().setBatteryLevelUpdateListener(object : BatteryLevelUpdateListener {
    override fun onBatteryLevelUpdated(level: Int, charging: Boolean) {
        // Handle battery updates [0-100]
    }
})
Media Capture
Photo Capture
// Supported resolutions: 4032x3024 down to 176x144
// Quality range: [0-100]

// AI scene photo capture (returns WebP via Bluetooth)
CxrApi.getInstance().takeGlassPhoto(width, height, quality, object : PhotoResultCallback {
    override fun onPhotoResult(status: ValueUtil.CxrStatus?, photo: ByteArray?) {
        // Handle WebP photo data
    }
})

// Path-based photo capture
CxrApi.getInstance().takeGlassPhoto(width, height, quality, object : PhotoPathCallback {
    override fun onPhotoPath(status: ValueUtil.CxrStatus?, path: String?) {
        // Handle file path for sync
    }
})
Video Recording
// Set video parameters
CxrApi.getInstance().setVideoParams(duration, 30, width, height, unit) // FPS=30

// Control recording via scenes
CxrApi.getInstance().controlScene(
    ValueUtil.CxrSceneType.VIDEO_RECORD, 
    true, // true=start, false=stop
    null
)
Audio Streaming
// Real-time audio capture
CxrApi.getInstance().setAudioStreamListener(object : AudioStreamListener {
    override fun onStartAudioStream(codecType: Int, streamType: String?) {
        // codecType: 1=PCM, 2=Opus
    }
    
    override fun onAudioStream(data: ByteArray?, offset: Int, length: Int) {
        // Handle real-time audio data
    }
})

// Start/stop audio recording
CxrApi.getInstance().openAudioRecord(codecType, "AI_assistant")
CxrApi.getInstance().closeAudioRecord("AI_assistant")
AI Interaction Pipeline
Event Handling
CxrApi.getInstance().setAiEventListener(object : AiEventListener {
    override fun onAiKeyDown() {
        // User pressed AI key - start interaction
    }
    
    override fun onAiKeyUp() {
        // Key released (currently unused)
    }
    
    override fun onAiExit() {
        // AI scene exited
    }
})
Speech Recognition Flow
// Send ASR results
CxrApi.getInstance().sendAsrContent(recognizedText)

// Handle ASR states
CxrApi.getInstance().notifyAsrNone()  // No speech detected
CxrApi.getInstance().notifyAsrError() // Recognition error
CxrApi.getInstance().notifyAsrEnd()   // Recognition complete
AI Response Delivery
// Send TTS content
CxrApi.getInstance().sendTtsContent(aiResponse)

// Notify TTS completion
CxrApi.getInstance().notifyTtsAudioFinished()
Error Handling
// Network issues
CxrApi.getInstance().notifyNoNetwork()

// Upload failures
CxrApi.getInstance().notifyPicUploadError()

// AI processing errors
CxrApi.getInstance().notifyAiError()
Data Operations
Stream Data to Glasses
CxrApi.getInstance().sendStream(
    ValueUtil.CxrStreamType.WORD_TIPS, // Teleprompter text
    dataByteArray,
    fileName,
    object : SendStatusCallback {
        override fun onSendSucceed() { /* Success */ }
        override fun onSendFailed(errorCode: ValueUtil.CxrSendErrorCode?) { /* Failed */ }
    }
)
File Synchronization
// Get unsync file counts
CxrApi.getInstance().getUnsyncNum(object : UnsyncNumResultCallback {
    override fun onUnsyncNumResult(
        status: ValueUtil.CxrStatus?,
        audioNum: Int,
        pictureNum: Int,
        videoNum: Int
    ) {
        // Handle file counts
    }
})

// Sync files (requires WiFi)
CxrApi.getInstance().startSync(
    savePath,
    arrayOf(ValueUtil.CxrMediaType.ALL),
    object : SyncStatusCallback {
        override fun onSyncStart() { /* Sync started */ }
        override fun onSingleFileSynced(fileName: String?) { /* File synced */ }
        override fun onSyncFailed() { /* Sync failed */ }
        override fun onSyncFinished() { /* Sync complete */ }
    }
)
Error Codes and Status
CxrStatus
REQUEST_SUCCEED: Request successful
REQUEST_WAITING: Request in progress, don't retry
REQUEST_FAILED: Request failed
RESPONSE_SUCCEED: Response successful
RESPONSE_INVALID: Invalid response
RESPONSE_TIMEOUT: Response timeout
Bluetooth Error Codes
PARAM_INVALID: Invalid parameters
BLE_CONNECT_FAILED: BLE connection failed
SOCKET_CONNECT_FAILED: Socket connection failed
UNKNOWN: Unknown error
WiFi Error Codes
WIFI_DISABLED: Mobile WiFi disabled
WIFI_CONNECT_FAILED: P2P connection failed
UNKNOWN: Unknown error
Best Practices
Performance Considerations
Camera operations are high-energy consumption
WiFi P2P is high-energy consumption - use only when necessary
Choose smallest possible image formats for Bluetooth transmission
Implement proper connection state management
Connection Management
Always complete Bluetooth connection before attempting WiFi
Implement robust reconnection logic
Handle device disconnection gracefully
Monitor battery levels to prevent unexpected shutdowns
AI Integration
Implement comprehensive error handling for network failures
Use streaming for real-time interactions
Cache responses when possible to reduce latency
Provide user feedback for all AI operations
Development Workflow
Start with basic Bluetooth connectivity
Implement media capture functionality
Add AI interaction pipeline
Integrate file synchronization
Optimize for battery and performance
Common Patterns
Singleton API Access
val api = CxrApi.getInstance()
Callback Pattern
Most operations use callback interfaces for asynchronous results:

interface StatusCallback {
    fun onSuccess()
    fun onFailure(errorCode: ErrorCode?)
}
Listener Pattern
For continuous monitoring:

api.setUpdateListener(listener) // Set listener
api.setUpdateListener(null)     // Remove listener
This documentation provides the foundation for developing Digital Companion applications using the Rokid Glasses SDK. Refer to specific callback interfaces and error codes for detailed implementation guidance.
