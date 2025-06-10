plugins {
    id(libs.plugins.unimined.get().pluginId)
}

val (_, _, neoforge, _) = createPlatformSourceSets("neoforge")
val (mainCompileOnly, _, _, neoforgeCompileOnly, _, _) = createPlatformConfigurations("neoforge")

unimined.minecraft {
    version(minecraftVersion)
    mappings {
        parchment(parchmentMinecraft, parchmentVersion)
        mojmap()
        devFallbackNamespace("official")
    }
    defaultRemapJar = false
}

unimined.minecraft(neoforge) {
    combineWith(sourceSets.main.get())
    neoForge {
        loader(neoForgeVersion)
    }
    defaultRemapJar = true
}

dependencies {
    listOf(":versions:v1_14_4", ":versions:v1_16_1", ":versions:v1_20_2").forEach {
        mainCompileOnly(project(it))
    }
    neoforgeCompileOnly(srcSetAsDep(":versions:v1_20_2", "neoforge"))
}

tasks.jar {
    from(neoforge.output)
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}
