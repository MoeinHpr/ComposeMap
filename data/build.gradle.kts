plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")

}

android {
    namespace = "com.hpr.module.data"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {

    // Core
    implementation(libs.core.ktx)

    // Room
    implementation (libs.bundles.room)
    ksp (libs.room.compiler)

    // Retrofit
    implementation(libs.bundles.retrofit)
    implementation(libs.moshi.converter)
    ksp(libs.moshi.codegen)

    // Hilt
    implementation (libs.hilt)
    ksp (libs.hilt.compiler)

}