plugins {
    id(libs.plugins.unimined.get().pluginId)
}

val (main, _, forge, _, _) = getPlatforms("forge")

unimined.minecraft {
    version(minecraftVersion)
    mappings {
        forgeBuiltinMCP(forgeVersion)
    }
    defaultRemapJar = false
}

unimined.minecraft(forge.sourceSet) {
    combineWith(sourceSets.main.get())
    minecraftForge {
        loader(forgeVersion)
    }
    defaultRemapJar = true
}

tasks.jar {
    dependsOn("remapForgeJar")
    from(jarToFiles("remapForgeJar"))
}
