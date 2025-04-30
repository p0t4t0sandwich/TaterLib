plugins {
    `kotlin-dsl`
}

repositories {
    maven("https://maven.neuralnexus.dev/mirror")
}

dependencies {
    implementation("xyz.wagyourtail.unimined:xyz.wagyourtail.unimined.gradle.plugin:1.3.14")
}

kotlin {
    jvmToolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}
