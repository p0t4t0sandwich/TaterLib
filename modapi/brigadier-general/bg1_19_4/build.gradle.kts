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
    depVersions = listOf("1.16.5", "1.14.4")
)

unimined.minecraft(forge.sourceSet) {
    combineWith(sourceSets.main.get())
    minecraftForge {
        loader(forgeVersion)
        mixinConfig("brigadier_general.mixins.v1_19_4.forge.json")
    }
    defaultRemapJar = true
}

registerRelocationTask(
    platform = "forge",
    version = minecraftVersion,
    relocate = "vanilla" to "searge",
    depVersions = listOf("1.17.1", "1.16.5", "1.14.4")
)

unimined.minecraft(sponge.sourceSet) {
    combineWith(sourceSets.main.get())
}

dependencies {
    // Because gradle does things alphabetically. I hate this
    // evaluationDependsOn(":modapi:brigadier-general:bg-api")
    // TODO: Uncomment when JVMDowngrader is applied to this project
    listOf("api-base", "command-api-v2").forEach {
        fabric.modImplementation(fabricApi.fabricModule("fabric-$it", fabricVersion))
    }
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
