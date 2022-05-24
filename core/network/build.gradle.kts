import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import com.example.ktor.project.AndroidProject
import com.example.ktor.project.Dependencies
import com.example.ktor.project.Modules

plugins {
    id("com.android.library")
    id("kotlinx-serialization")
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

}

dependencies {
    implementation(kotlin("stdlib"))
    implementation(Dependencies.Logger.core)
    implementation(Dependencies.Kotlin.Serialization.json)
    implementation(Dependencies.Kotlin.Serialization.ktor)
    api(Dependencies.Api.Ktor.core)
    api(Dependencies.Api.Ktor.logging)
    implementation(project(Modules.Core.std))
}
