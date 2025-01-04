plugins {
    alias(libs.plugins.unimined)
}

base {
    archivesName = "${projectId}-neoforge-${minecraftVersion}"
}

java.toolchain.languageVersion = JavaLanguageVersion.of(javaVersion)
java.sourceCompatibility = JavaVersion.toVersion(javaVersion)
java.targetCompatibility = JavaVersion.toVersion(javaVersion)

unimined.minecraft(sourceSets.main.get()) {
    version(minecraftVersion)
    neoForged {
        loader(apiVersion)
    }
    mappings {
        mojmap()
    }
}

dependencies {
//    compileOnly("net.neoforged:neoforge:${apiVersion}")
    compileOnly(project(":api"))
    compileOnly(project(":common"))
    compileOnly(project(":loader"))
    compileOnly(variantOf(libs.modapi.muxins) {
        classifier("downgraded-8-all")
    })
    compileOnly(project(":vanilla:vanilla-1.20"))
}
