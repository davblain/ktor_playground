import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import com.example.ktor.project.AndroidProject
import com.example.ktor.project.Dependencies
import com.example.ktor.project.Modules

plugins {
    id("com.android.library")
    id("kotlinx-serialization")
    id("kotlin-parcelize")
    kotlin(module = "android")
}

android {

    compileSdk = AndroidProject.compileSdkVersion
    buildToolsVersion = AndroidProject.buildToolsVersion

    defaultConfig {
        minSdk = AndroidProject.minSdkVersion
        targetSdk = AndroidProject.targetSdkVersion
    }

    java {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            jvmTarget = "1.8"
        }
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Dependencies.AndroidX.Compose.composeCompilerVersion
    }
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation(Dependencies.Logger.core)
    implementation(Dependencies.Tea.keemun)
    implementation(Dependencies.Kotlin.Serialization.json)
    implementation(Dependencies.Koin.compose)
    implementation(Dependencies.Koin.core)
    implementation(Dependencies.Koin.android)
    implementation(Dependencies.Navigation.navComponent)

    implementation(Dependencies.AndroidX.Compose.compiler)
    implementation(Dependencies.AndroidX.Compose.runtime)
    implementation(Dependencies.AndroidX.Compose.material)
    implementation(Dependencies.AndroidX.Compose.tooling)
    implementation(Dependencies.AndroidX.Compose.toolingPreview)
    implementation(Dependencies.AndroidX.Compose.activity)
    //Временный Workaround  обхода https://issuetracker.google.com/issues/227767363
    debugImplementation("androidx.customview:customview:1.2.0-alpha01")
    debugImplementation("androidx.customview:customview-poolingcontainer:1.0.0-beta02")
    implementation(project(Modules.Core.messariApi))
    implementation(project(Modules.Core.std))
}
