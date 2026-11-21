// Archivo de build raíz: solo declara los plugins; se aplican en :app.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.legacy.kapt) apply false
    alias(libs.plugins.kotlin.android) apply false // solo fija la versión del compilador Kotlin
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.navigation.safeargs) apply false
}
