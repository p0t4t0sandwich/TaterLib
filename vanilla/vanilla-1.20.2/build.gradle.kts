plugins {
    alias(libs.plugins.vanillagradle)
}

base {
    archivesName = "${projectId}-vanilla-${minecraftVersion}"
}

java.toolchain.languageVersion = JavaLanguageVersion.of(javaVersion)
java.sourceCompatibility = JavaVersion.toVersion(javaVersion)
java.targetCompatibility = JavaVersion.toVersion(javaVersion)

minecraft {
    version(minecraftVersion)
}

dependencies {
    compileOnly(libs.mixin)
    compileOnly(project(":api"))
    compileOnly(project(":common"))
    compileOnly(project(":loader"))
    compileOnly(variantOf(libs.modapi.metadata) {
        classifier("downgraded-8")
    })
    compileOnly(libs.modapi.muxins)
    compileOnly(project(":vanilla:vanilla-1.20"))
}