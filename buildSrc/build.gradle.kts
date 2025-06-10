plugins {
    `kotlin-dsl`
}

repositories {
    maven("https://maven.neuralnexus.dev/mirror")
}

dependencies {
    implementation("com.gradleup.shadow:com.gradleup.shadow.gradle.plugin:9.0.0-beta13")
    implementation("xyz.wagyourtail.unimined:xyz.wagyourtail.unimined.gradle.plugin:1.3.14")
}

kotlin {
    jvmToolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}
