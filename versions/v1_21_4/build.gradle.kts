plugins {
    id(libs.plugins.unimined.get().pluginId)
}

val (main, fabric, forge, neoforge, sponge) = getPlatforms("fabric", "forge", "neoforge", "sponge")

unimined.minecraft {
    version(minecraftVersion)
    mappings {
        parchment(parchmentMinecraft, parchmentVersion)
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
    depVersions = listOf("1.21.1", "1.16.1", "1.14.4")
)

unimined.minecraft(forge.sourceSet) {
    combineWith(sourceSets.main.get())
    minecraftForge {
        loader(forgeVersion)
    }
    defaultRemapJar = true
}

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
    listOf(":versions:v1_14_4", ":versions:v1_16_1", ":versions:v1_20_2",
        ":versions:v1_20_6", ":versions:v1_21_1").forEach {
            main.compileOnly(project(it))
    }
    listOf("api-base", "command-api-v2", "lifecycle-events-v1", "networking-api-v1").forEach {
        fabric.modImplementation(fabricApi.fabricModule("fabric-$it", fabricVersion))
    }
    forge.compileOnly(srcSetAsDep(":versions:modern-utils", "forge"))
    neoforge.compileOnly(srcSetAsDep(":versions:v1_20_2", "neoforge"))
    sponge.compileOnly("org.spongepowered:spongeapi:${spongeVersion}")
    sponge.compileOnly(srcSetAsDep(":versions:v1_16_5", "sponge"))
    sponge.compileOnly(srcSetAsDep(":versions:v1_19_4", "sponge"))
    sponge.compileOnly(srcSetAsDep(":versions:v1_20_6", "sponge"))
}

tasks.jar {
    dependsOn("relocateFabricJar")
    from(jarToFiles("relocateFabricJar"))
    from(forge.sourceSet.output)
    from(neoforge.sourceSet.output)
    from(sponge.sourceSet.output)
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}
