plugins {
    alias(libs.plugins.forgegradle)
}

base {
    archivesName = "${projectId}-forge-${minecraftVersion}"
}

minecraft {
    mappings(mappingsChannel, mappingsVersion)
}

dependencies {
    minecraft("net.minecraftforge:forge:${minecraftVersion}-${apiVersion}")
    compileOnly(project(":api"))
    compileOnly(project(":common"))
    compileOnly(project(":tooling:loader"))
    compileOnly(project(":forge:forge-utils-modern"))
}
