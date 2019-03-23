plugins {
    kotlin("multiplatform") version "1.3.30-eap-11"
}

repositories {
    mavenCentral()
    maven("https://dl.bintray.com/kotlin/kotlin-eap")
    maven("https://dl.bintray.com/kotlin/kotlin-dev")
}

kotlin {
    // For ARM, preset function should be changed to iosArm32() or iosArm64()
    // For Linux, preset function should be changed to e.g. linuxX64()
    // For MacOS, preset function should be changed to e.g. macosX64()
    linuxX64("HelloWorld") {
        binaries {
            // Comment the next section to generate Kotlin/Native library (KLIB) instead of executable file:
            executable("HelloWorldApp") {
                // Change to specify fully qualified name of your application's entry point:
                entryPoint = "sample.helloworld.main"
            }
        }
    }
}

// Use the following Gradle tasks to run your application:
// :runHelloWorldAppReleaseExecutableHelloWorld - without debug symbols
// :runHelloWorldAppDebugExecutableHelloWorld - with debug symbols
