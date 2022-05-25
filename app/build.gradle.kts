import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import com.example.ktor.project.AndroidProject
import com.example.ktor.project.Dependencies
import com.example.ktor.project.Modules

plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-parcelize")
    id("kotlinx-serialization")
}

android {
    compileSdk = AndroidProject.compileSdkVersion
    buildToolsVersion = AndroidProject.buildToolsVersion

    defaultConfig {
        minSdk = AndroidProject.minSdkVersion
        targetSdk = AndroidProject.targetSdkVersion
        versionCode = 1
        versionName = "1.0"
        buildConfigField("String", "MESSARY_API_KEY", "\"44926959-5c77-4b81-8ffa-43920d45d57f\"")
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    java {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Dependencies.AndroidX.Compose.composeCompilerVersion
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            jvmTarget = "1.8"
        }
    }
}

dependencies {
    implementation(Dependencies.Kotlin.Coroutines.core)

    implementation(Dependencies.AndroidX.activity)
    implementation(Dependencies.AndroidX.Compose.activity)
    implementation(Dependencies.AndroidX.Compose.compiler)
    implementation(Dependencies.AndroidX.Compose.runtime)
    implementation(Dependencies.AndroidX.Compose.themeAdapter)
    implementation(Dependencies.AndroidX.Compose.materialIcons)
    implementation(Dependencies.AndroidX.Compose.material)
    implementation(Dependencies.AndroidX.Compose.tooling)

    implementation(Dependencies.Api.Ktor.core)
    implementation(Dependencies.Api.Ktor.logging)
    implementation(Dependencies.Kotlin.Serialization.ktor)
    implementation(Dependencies.Kotlin.Serialization.json)

    implementation(Dependencies.Koin.core)
    implementation(Dependencies.Koin.android)

    implementation(Dependencies.Navigation.navComponent)

    implementation(Dependencies.Logger.core)
    implementation(Dependencies.Koin.compose)
    implementation(project(Modules.Feature.pricesList))
    implementation(project(Modules.Core.messariApi))
}
