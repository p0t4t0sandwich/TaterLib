plugins {
    id(libs.plugins.unimined.get().pluginId)
}

val (fabric, _, neoforge, sponge) = createPlatformSourceSets("fabric", "neoforge", "sponge")
val (mainCompileOnly, fabricCompileOnly, _, neoforgeCompileOnly, spongeCompileOnly, fabricModImplementation
) = createPlatformConfigurations("fabric", "neoforge", "sponge")

unimined.minecraft {
    version(minecraftVersion)
    mappings {
        mojmap()
        devFallbackNamespace("official")
    }
    defaultRemapJar = false
}

unimined.minecraft(fabric) {
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

unimined.minecraft(neoforge) {
    combineWith(sourceSets.main.get())
    neoForge {
        loader(neoForgeVersion)
    }
    defaultRemapJar = true
}

unimined.minecraft(sponge) {
    combineWith(sourceSets.main.get())
}

dependencies {
    listOf(":versions:v1_14_4", ":versions:v1_16_1", ":versions:v1_20_2").forEach {
        mainCompileOnly(project(it))
    }
    neoforgeCompileOnly(srcSetAsDep(":versions:v1_20_2", "neoforge"))
    spongeCompileOnly("org.spongepowered:spongeapi:${spongeVersion}")
    spongeCompileOnly(srcSetAsDep(":versions:v1_16_5", "sponge"))
    spongeCompileOnly(srcSetAsDep(":versions:v1_19_4", "sponge"))
}

tasks.jar {
    dependsOn("relocateFabricJar")
    from(jarToFiles("relocateFabricJar"))
    from(neoforge.output)
    from(sponge.output)
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}
