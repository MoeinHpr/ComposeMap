
plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.hpr.module.core"
    compileSdk = 34

    buildFeatures{
        buildConfig = true
    }

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")

        buildConfigField("String" , "BASE_URL" , "\"https://cdn.sixt.io/\"")
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
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.2"
    }
    kotlinOptions {
        jvmTarget = "17"
    }


}

dependencies {
    implementation(project(":data"))

    // Core
    implementation(libs.core.ktx)
    implementation(libs.core.ktx.lifecycle)

    // Retrofit
    implementation(libs.bundles.retrofit)
    implementation(libs.moshi.converter)
    ksp(libs.moshi.codegen)

    // Room
    implementation(libs.room.runtime)
    ksp (libs.room.compiler)

    // Hilt
    implementation(libs.hilt)
    ksp (libs.hilt.compiler)

    // Coil
    implementation(libs.coil.compose)


}