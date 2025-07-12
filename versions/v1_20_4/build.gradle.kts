plugins {
    id(libs.plugins.unimined.get().pluginId)
}

val (main, _, _, neoforge, _) = getPlatforms("neoforge")

unimined.minecraft {
    version(minecraftVersion)
    mappings {
        parchment(parchmentMinecraft, parchmentVersion)
        mojmap()
        devFallbackNamespace("official")
    }
    defaultRemapJar = false
}

unimined.minecraft(neoforge.sourceSet) {
    combineWith(sourceSets.main.get())
    neoForge {
        loader(neoForgeVersion)
    }
    defaultRemapJar = true
}

dependencies {
    listOf(":versions:v1_14_4", ":versions:v1_16_1", ":versions:v1_20_2").forEach {
        main.compileOnly(project(it))
    }
    neoforge.compileOnly(srcSetAsDep(":versions:v1_20_2", "neoforge"))
}

tasks.jar {
    from(neoforge.sourceSet.output)
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}
