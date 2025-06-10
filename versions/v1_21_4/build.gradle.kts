plugins {
    id(libs.plugins.unimined.get().pluginId)
}

val (fabric, forge, neoforge, sponge) = createPlatformSourceSets("fabric", "forge", "neoforge", "sponge")
val (mainCompileOnly, fabricCompileOnly, forgeCompileOnly,
    neoforgeCompileOnly, spongeCompileOnly, fabricModImplementation
) = createPlatformConfigurations("fabric", "forge", "neoforge", "sponge")

unimined.minecraft {
    version(minecraftVersion)
    mappings {
        parchment(parchmentMinecraft, parchmentVersion)
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
    depVersions = listOf("1.21.1", "1.16.1", "1.14.4")
)

unimined.minecraft(forge) {
    combineWith(sourceSets.main.get())
    minecraftForge {
        loader(forgeVersion)
    }
    defaultRemapJar = true
}

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
    listOf(":versions:v1_14_4", ":versions:v1_16_1", ":versions:v1_20_2",
        ":versions:v1_20_6", ":versions:v1_21_1").forEach {
            mainCompileOnly(project(it))
    }
    listOf("api-base", "command-api-v2", "lifecycle-events-v1", "networking-api-v1").forEach {
        fabricModImplementation(fabricApi.fabricModule("fabric-$it", fabricVersion))
    }
    forgeCompileOnly(srcSetAsDep(":versions:modern-utils", "forge"))
    neoforgeCompileOnly(srcSetAsDep(":versions:v1_20_2", "neoforge"))
    spongeCompileOnly("org.spongepowered:spongeapi:${spongeVersion}")
    spongeCompileOnly(srcSetAsDep(":versions:v1_16_5", "sponge"))
    spongeCompileOnly(srcSetAsDep(":versions:v1_19_4", "sponge"))
    spongeCompileOnly(srcSetAsDep(":versions:v1_20_6", "sponge"))
}

tasks.jar {
    dependsOn("relocateFabricJar")
    from(jarToFiles("relocateFabricJar"))
    from(forge.output)
    from(neoforge.output)
    from(sponge.output)
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}
