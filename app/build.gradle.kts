plugins {
    // Kotlin support is built into AGP 9+; the org.jetbrains.kotlin.android
    // plugin must NOT be applied, otherwise the `kotlin` extension collides.
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.guessthenumberk"
    compileSdk {
        version = release(37)
    }

    defaultConfig {
        applicationId = "com.example.guessthenumberk"
        minSdk = 24
        targetSdk = 37
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            optimization {
                enable = true
                packageScope = setOf("androidx.**", "kotlin.**", "kotlinx.**")
            }
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}
