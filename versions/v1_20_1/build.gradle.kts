plugins {
    id(libs.plugins.unimined.get().pluginId)
}

val (fabric, forge, _, _) = createPlatformSourceSets("fabric", "forge")
val (mainCompileOnly, fabricCompileOnly, forgeCompileOnly, _, _, fabricModImplementation
) = createPlatformConfigurations("fabric", "forge")

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
    depVersions = listOf("1.16.1", "1.14.4")
)

unimined.minecraft(forge) {
    combineWith(sourceSets.main.get())
    minecraftForge {
        loader(forgeVersion)
        mixinConfig("taterlib.mixins.v1_20_1.forge.json")
    }
    defaultRemapJar = true
}

registerRelocationTask(
    platform = "forge",
    version = minecraftVersion,
    relocate = "vanilla" to "searge",
    depVersions = listOf("1.16.1", "1.14.4")
)

dependencies {
    listOf(":versions:v1_14_4", ":versions:v1_16_1").forEach {
        mainCompileOnly(project(it))
    }
    forgeCompileOnly(srcSetAsDep(":versions:modern-utils", "forge"))
}

tasks.jar {
    dependsOn("relocateFabricJar")
    from(jarToFiles("relocateFabricJar"))
    dependsOn("relocateForgeJar")
    from(jarToFiles("relocateForgeJar"))
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}
