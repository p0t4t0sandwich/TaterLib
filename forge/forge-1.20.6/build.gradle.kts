plugins {
    alias(libs.plugins.forgegradle)
}

base {
    archivesName = "${projectId}-forge-${minecraftVersion}"
}

java.toolchain.languageVersion = JavaLanguageVersion.of(javaVersion)
java.sourceCompatibility = JavaVersion.toVersion(javaVersion)
java.targetCompatibility = JavaVersion.toVersion(javaVersion)

minecraft {
    mappings(mappingsChannel, mappingsVersion)
    reobf = false
}

dependencies {
    minecraft("net.minecraftforge:forge:${minecraftVersion}-${apiVersion}")

    // Hack fix for now, force jopt-simple to be exactly 5.0.4 because Mojang ships that version, but some transtive dependencies request 6.0+
    implementation("net.sf.jopt-simple:jopt-simple:5.0.4") { version { strictly("5.0.4") } }

    compileOnly(project(":api"))
    compileOnly(project(":common"))
    compileOnly(project(":loader"))
    compileOnly(libs.modapi.metadata)
    compileOnly(project(":forge:forge-utils-modern"))
    compileOnly(project(":vanilla:vanilla-1.20"))
    compileOnly(project(":vanilla:vanilla-1.20.2"))
    compileOnly(project(":vanilla:vanilla-1.20.6"))
}
