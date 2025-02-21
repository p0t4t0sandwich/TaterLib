plugins {
    alias(libs.plugins.unimined)
}

base {
    archivesName = "${projectId}-forge-${minecraftVersion}"
}

java.toolchain.languageVersion = JavaLanguageVersion.of(javaVersion)
java.sourceCompatibility = JavaVersion.toVersion(javaVersion)
java.targetCompatibility = JavaVersion.toVersion(javaVersion)

unimined.minecraft(sourceSets.main.get()) {
    version(minecraftVersion)
    minecraftForge {
        loader(apiVersion)
    }
    mappings {
        mojmap()
    }
}

dependencies {
    compileOnly(project(":api"))
    compileOnly(project(":common"))
    compileOnly(project(":loader"))
    compileOnly(libs.modapi)
    compileOnly(project(":forge:forge-utils-modern"))
    compileOnly(project(":vanilla:vanilla-1.20"))
    compileOnly(project(":vanilla:vanilla-1.20.2"))
    compileOnly(project(":vanilla:vanilla-1.20.6"))
}
