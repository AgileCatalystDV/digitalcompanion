plugins {
    id("com.android.application") version "8.7.0" apply false
    id("org.jetbrains.kotlin.android") version "2.1.0" apply false
    // Root build.gradle.kts

    // Keep your existing plugins (android, kotlin, etc.)
    id("org.jetbrains.kotlin.plugin.compose") version "2.0.21" apply false // Match your Kotlin version
}
