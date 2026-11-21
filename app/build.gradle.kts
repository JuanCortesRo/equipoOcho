plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.android.legacy.kapt) // requerido por DataBinding con Kotlin
    alias(libs.plugins.ksp)                 // procesador de Room
    alias(libs.plugins.navigation.safeargs)
}

android {
    namespace = "com.equipoocho.dogapp"
    compileSdk = 37

    defaultConfig {
        applicationId = "com.equipoocho.dogapp"
        minSdk = 26
        targetSdk = 37
        versionCode = 1
        versionName = "1.0.0"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    buildFeatures {
        dataBinding = true
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.fragment.ktx)
    implementation(libs.androidx.recyclerview)

    // MVVM
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.kotlinx.coroutines.android)

    // Navigation (Fragments)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)

    // Room (SQLite)
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    ksp(libs.androidx.room.compiler)

    // Retrofit (API REST dog.ceo)
    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.gson)

    // Imágenes (Glide), animación de huella (Lottie) y biometría
    implementation(libs.glide)
    implementation(libs.lottie)
    implementation(libs.androidx.biometric)
}
