plugins {
    alias(libs.plugins.unimined)
}

base {
    archivesName = "${projectId}-forge-${minecraftVersion}"
}

unimined.minecraft(sourceSets.main.get()) {
    version(minecraftVersion)
    minecraftForge {
        loader(apiVersion)
    }
    mappings {
        forgeBuiltinMCP(apiVersion)
    }
}

dependencies {
    compileOnly(project(":api"))
    compileOnly(project(":common"))
    compileOnly(project(":loader"))
    compileOnly(variantOf(libs.modapi.metadata) {
        classifier("downgraded-8")
    })
}
