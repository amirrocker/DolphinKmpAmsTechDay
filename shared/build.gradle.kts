plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    kotlin("plugin.serialization") version "1.7.21"
    id("com.squareup.sqldelight")
    id("com.android.library")
}

kotlin {
    android()
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        version = "1.0"
        ios.deploymentTarget = "14.1"
        podfile = project.file("../iosApp/Podfile")
        framework {
            baseName = "shared"
        }
    }

    sourceSets {
        val ktorVersion = "2.1.2"
        val sqlDelighVersion = "1.5.4"
        val coroutineVersion = "1.6.4"
        val dateTimeVersion = "0.4.0"
        val commonMain by getting {
            dependencies {
                // date time
                implementation("org.jetbrains.kotlinx:kotlinx-datetime:$dateTimeVersion")
                // coroutines - note that the mpp adds ios/android dependencies separately on its own
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutineVersion")
                // coroutines - note that the mpp adds ios/android dependencies separately on its own
                implementation("io.ktor:ktor-client-core:$ktorVersion")
                implementation("io.ktor:ktor-client-content-negotiation:$ktorVersion")
                implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")
                // sqldelight - seems to be room for kmm projects.
                implementation("com.squareup.sqldelight:runtime:$sqlDelighVersion")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val androidMain by getting {
            dependencies {
                // ktor client
                implementation("io.ktor:ktor-client-android:$ktorVersion")
                implementation("com.squareup.sqldelight:android-driver:$sqlDelighVersion")
            }
        }
        val androidTest by getting
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)

            // ktor client
            dependencies {
                // ktor
                implementation("io.ktor:ktor-client-darwin:$ktorVersion")
                // sqldelight
                implementation("com.squareup.sqldelight:native-driver:$sqlDelighVersion")
            }
        }
        val iosX64Test by getting
        val iosArm64Test by getting
        val iosSimulatorArm64Test by getting
        val iosTest by creating {
            dependsOn(commonTest)
            iosX64Test.dependsOn(this)
            iosArm64Test.dependsOn(this)
            iosSimulatorArm64Test.dependsOn(this)
        }
    }
}

android {
    namespace = "de.amirrocker.mobile.dolphinkmpamstechday"
    compileSdk = 33
    defaultConfig {
        minSdk = 30
        targetSdk = 33
    }
}

// use the new memory manager for kotlin/native - will soon be default
kotlin.targets.withType(org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget::class.java) {
    binaries.all {
        binaryOptions["memoryModel"] = "experimental"
    }
}

// sqldelight - will contain a list of db's and their params.
sqldelight {
    database("AppDatabase") {
        packageName = "de.amirrocker.mobile.dolphinkmpamstechday.shared.cache"
    }
}
