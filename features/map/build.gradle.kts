plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
    id("com.google.dagger.hilt.android")
}
apply(plugin = "dev.shreyaspatil.compose-compiler-report-generator")
android {
    namespace = "com.hpr.features.map"
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
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.2"
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

    implementation(project(":data"))
    implementation(project(":core"))

    // Core
    implementation(libs.core.ktx)

    // Map
    implementation(libs.google.map.compose)
    implementation(libs.google.playservices.map)

    // Compose
    implementation(libs.compose.activity)
    implementation(platform(libs.compose.bom))
    implementation(libs.compose.ui)
    implementation(libs.compose.ui.graphics)
    implementation(libs.compose.ui.tooling.preview)
    implementation(libs.compose.ui.tooling)
    implementation(libs.compose.material3)
    implementation(libs.bundles.composeNavigation)

    // Retrofit
    implementation(libs.bundles.retrofit)
    implementation(libs.moshi.converter)
    ksp(libs.moshi.codegen)

    // Room
    implementation (libs.room.runtime)
    ksp (libs.room.compiler)

    // Hilt
    implementation(libs.hilt)
    implementation(libs.hilt.navigation)
    ksp (libs.hilt.compiler)

    // Coil
    implementation(libs.coil.compose)

}