plugins {
    id(libs.plugins.unimined.get().pluginId)
}

val (main, fabric, _, neoforge, sponge) = getPlatforms("fabric", "neoforge", "sponge")

unimined.minecraft {
    version(minecraftVersion)
    mappings {
        mojmap()
        devFallbackNamespace("official")
    }
    defaultRemapJar = false
}

unimined.minecraft(fabric.sourceSet) {
    combineWith(sourceSets.main.get())
    fabric {
        loader(fabricLoaderVersion)
    }
    defaultRemapJar = true
}

registerRelocationTask(
    platform = "fabric",
    version = minecraftVersion,
    relocate = "vanilla" to "y_intmdry",
    depVersions = listOf("1.20.2", "1.16.1", "1.14.4")
)

unimined.minecraft(neoforge.sourceSet) {
    combineWith(sourceSets.main.get())
    neoForge {
        loader(neoForgeVersion)
    }
    defaultRemapJar = true
}

unimined.minecraft(sponge.sourceSet) {
    combineWith(sourceSets.main.get())
}

dependencies {
    listOf(":versions:v1_14_4", ":versions:v1_16_1", ":versions:v1_20_2").forEach {
        main.compileOnly(project(it))
    }
    neoforge.compileOnly(srcSetAsDep(":versions:v1_20_2", "neoforge"))
    sponge.compileOnly("org.spongepowered:spongeapi:${spongeVersion}")
    sponge.compileOnly(srcSetAsDep(":versions:v1_16_5", "sponge"))
    sponge.compileOnly(srcSetAsDep(":versions:v1_19_4", "sponge"))
}

tasks.jar {
    dependsOn("relocateFabricJar")
    from(jarToFiles("relocateFabricJar"))
    from(neoforge.sourceSet.output)
    from(sponge.sourceSet.output)
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}
