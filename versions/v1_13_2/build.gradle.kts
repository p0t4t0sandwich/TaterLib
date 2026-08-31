plugins {
    id(libs.plugins.unimined.get().pluginId)
}

val (main, _, forge, _, _) = getPlatforms("forge")

unimined.minecraft {
    version(minecraftVersion)
    mappings {
        searge()
        mcp(mcpChannel, mcpVersion)
    }
    defaultRemapJar = false
}

unimined.minecraft(forge.sourceSet) {
    combineWith(sourceSets.main.get())
    minecraftForge {
        loader(forgeVersion)
        mixinConfig("$modId.mixins.v13_2.forge.json")
    }
    defaultRemapJar = true
}

dependencies {
    forge.compileOnly(libs.mixin)
    forge.compileOnly(srcSetAsDep(":versions:modern-utils", "forge"))
}

tasks.jar {
    dependsOn("remapForgeJar")
    from(jarToFiles("remapForgeJar"))
}
