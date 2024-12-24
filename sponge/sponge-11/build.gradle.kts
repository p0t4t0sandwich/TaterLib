plugins {
    alias(libs.plugins.vanillagradle)
}

base {
    archivesName = "${projectId}-sponge-11"
}

java.toolchain.languageVersion = JavaLanguageVersion.of(javaVersion)
java.sourceCompatibility = JavaVersion.toVersion(javaVersion)
java.targetCompatibility = JavaVersion.toVersion(javaVersion)

minecraft {
    version(minecraftVersion)
}

dependencies {
    compileOnly(libs.brigadier)
    compileOnly("org.spongepowered:spongeapi:${apiVersion}")
    compileOnly(project(":api"))
    compileOnly(project(":common"))
    compileOnly(project(":tooling:loader"))
    compileOnly(project(":vanilla:vanilla-1.20"))
}
