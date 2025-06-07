plugins {
    alias(libs.plugins.shadow)
    id(libs.plugins.unimined.get().pluginId)
}

val (_, forge, _, _) = createPlatformSourceSets("forge")
val (mainCompileOnly, _, forgeCompileOnly, _, _, _) = createPlatformConfigurations("forge")

unimined.minecraft {
    version(minecraftVersion)
    mappings {
        searge()
        mcp(mcpChannel, mcpVersion)
    }
    defaultRemapJar = false
}

unimined.minecraft(forge) {
    combineWith(sourceSets.main.get())
    minecraftForge {
        loader(forgeVersion)
    }
    defaultRemapJar = true
}

dependencies {
    forgeCompileOnly(srcSetAsDep(":versions:modern-utils", "forge"))
}

tasks.jar {
    dependsOn("remapForgeJar")
    from(jarToFiles("remapForgeJar"))
}
