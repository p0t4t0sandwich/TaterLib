plugins {
    id(libs.plugins.unimined.get().pluginId)
}

val (main, fabric, _, _, sponge) = getPlatforms("fabric", "sponge")

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
    depVersions = listOf("1.16.1", "1.14.4")
)

unimined.minecraft(sponge.sourceSet) {
    combineWith(sourceSets.main.get())
}

dependencies {
    listOf(":versions:v1_14_4", ":versions:v1_16_1").forEach {
        main.compileOnly(project(it))
    }
    sponge.compileOnly("org.spongepowered:spongeapi:${spongeVersion}")
    sponge.compileOnly(srcSetAsDep(":versions:v1_16_5", "sponge"))
}

tasks.jar {
    dependsOn("relocateFabricJar")
    from(jarToFiles("relocateFabricJar"))
    from(sponge.sourceSet.output)
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}
