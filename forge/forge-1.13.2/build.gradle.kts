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
    compileOnly(project(":loader"))
    compileOnly(variantOf(libs.modapi.metadata) {
        classifier("downgraded-8")
    })
    compileOnly(project(":forge:forge-utils-modern"))
}
