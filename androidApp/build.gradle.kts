import Build_gradle.ActivityVersions.activiy
import Build_gradle.ComposeVersions.compose

plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    namespace = "de.amirrocker.mobile.dolphinkmpamstechday.android"
    compileSdk = 33
    defaultConfig {
        applicationId = "de.amirrocker.mobile.dolphinkmpamstechday.android"
        minSdk = 30
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.3.0"
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
}

// TODO - think about moving these to a script so it can be applied to multiple build.gradle
// apply(from = "BuildSrc/versionScript.gradle")
object ComposeVersions {
    val compose = "1.3.1"
}

object ActivityVersions {
    val activiy = "1.6.1"
}

dependencies {
    implementation(project(":shared"))
    implementation("androidx.compose.ui:ui:$compose")
    implementation("androidx.compose.ui:ui-tooling:$compose")
    implementation("androidx.compose.ui:ui-tooling-preview:$compose")
    implementation("androidx.compose.foundation:foundation:$compose")
    implementation("androidx.compose.material:material:$compose")
    implementation("androidx.activity:activity-compose:$activiy")
}
