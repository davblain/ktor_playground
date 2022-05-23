
buildscript {
    repositories {
        mavenCentral()
        google()
        mavenLocal()
    }

    dependencies {
        classpath(dependencyNotation = com.example.ktor.project.BuildPlugins.gradle)
        classpath(dependencyNotation = com.example.ktor.project.BuildPlugins.Kotlin.core)
        classpath(dependencyNotation = com.example.ktor.project.BuildPlugins.Kotlin.serialization)
    }
}

allprojects {
    repositories {
        mavenCentral()
        google()
        mavenLocal()
    }
}

val clean by tasks.registering(Delete::class) { delete(rootProject.buildDir) }
