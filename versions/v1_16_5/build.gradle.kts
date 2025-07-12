plugins {
    id(libs.plugins.unimined.get().pluginId)
}

val (main, fabric, forge, _, sponge) = getPlatforms("fabric", "forge", "sponge")

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
    depVersions = listOf("1.14.4")
)

unimined.minecraft(forge.sourceSet) {
    combineWith(sourceSets.main.get())
    minecraftForge {
        loader(forgeVersion)
        mixinConfig("taterlib.mixins.v1_16_5.forge.json")
    }
    defaultRemapJar = true
}

registerRelocationTask(
    platform = "forge",
    version = minecraftVersion,
    relocate = "vanilla" to "l_searge",
    depVersions = listOf("1.14.4")
)

unimined.minecraft(sponge.sourceSet) {
    combineWith(sourceSets.main.get())
}

dependencies {
    main.compileOnly(project(":versions:v1_14_4"))
    forge.compileOnly(srcSetAsDep(":versions:modern-utils", "forge"))
    sponge.compileOnly("org.spongepowered:spongeapi:${spongeVersion}")
}

tasks.jar {
    dependsOn("relocateFabricJar")
    from(jarToFiles("relocateFabricJar"))
    dependsOn("relocateForgeJar")
    from(jarToFiles("relocateForgeJar"))
    from(sponge.sourceSet.output)
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}
