import org.jetbrains.kotlin.gradle.internal.config.LanguageFeature


plugins {
    kotlin("jvm") version "2.1.0"
}

group = "org.kotlinlang"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(21)
}


kotlin {
//    explicitApi()
//    explicitApiWarning()

//    compilerOptions {
//        freeCompilerArgs.add("-Xwhen-guards")
//        freeCompilerArgs.add("-Xmulti-dollar-interpolation")
//        freeCompilerArgs.add("-Xnon-local-break-continue")
//    }

    sourceSets.all {
        languageSettings {
            enableLanguageFeature(LanguageFeature.WhenGuards.name)
            enableLanguageFeature(LanguageFeature.MultiDollarInterpolation.name)
            enableLanguageFeature(LanguageFeature.BreakContinueInInlineLambdas.name)
        }
    }

}
