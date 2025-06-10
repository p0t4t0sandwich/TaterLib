plugins {
    id(libs.plugins.unimined.get().pluginId)
}

val (fabric, forge, _, sponge) = createPlatformSourceSets("fabric", "forge", "sponge")
val (mainCompileOnly, fabricCompileOnly, forgeCompileOnly, _, spongeCompileOnly, fabricModImplementation
) = createPlatformConfigurations("fabric", "forge", "sponge")

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
    depVersions = listOf("1.14.4")
)

unimined.minecraft(forge) {
    combineWith(sourceSets.main.get())
    minecraftForge {
        loader(forgeVersion)
        mixinConfig("brigadier_general.mixins.v1_16_5.forge.json")
    }
    defaultRemapJar = true
}

registerRelocationTask(
    platform = "forge",
    version = minecraftVersion,
    relocate = "vanilla" to "l_searge",
    depVersions = listOf("1.14.4")
)

unimined.minecraft(sponge) {
    combineWith(sourceSets.main.get())
}

dependencies {
    // Because gradle does things alphabetically. I hate this
    // evaluationDependsOn(":modapi:brigadier-general:bg-api")
    // TODO: Uncomment when JVMDowngrader is applied to this project
    spongeCompileOnly("org.spongepowered:spongeapi:${spongeVersion}")
}

tasks.jar {
    dependsOn("relocateFabricJar")
    from(jarToFiles("relocateFabricJar"))
    dependsOn("relocateForgeJar")
    from(jarToFiles("relocateForgeJar"))
    from(sponge.output)
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}
