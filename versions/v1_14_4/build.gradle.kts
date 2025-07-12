plugins {
    id(libs.plugins.unimined.get().pluginId)
}

val (main, fabric, forge, _, _) = getPlatforms("fabric", "forge")

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
    relocate = "vanilla" to "y_intmdry"
)

unimined.minecraft(forge.sourceSet) {
    combineWith(sourceSets.main.get())
    minecraftForge {
        loader(forgeVersion)
        mixinConfig("taterlib.mixins.v1_14_4.forge.json")
    }
    defaultRemapJar = true
}

registerRelocationTask(
    platform = "forge",
    version = minecraftVersion,
    relocate = "vanilla" to "l_searge"
)

dependencies {
    listOf("api-base", "command-api-v1", "lifecycle-events-v1", "networking-api-v1").forEach {
        fabric.modImplementation(fabricApi.fabricModule("fabric-$it", fabricVersion))
    }
    forge.compileOnly(libs.mixin)
    forge.compileOnly(srcSetAsDep(":versions:modern-utils", "forge"))
}

tasks.jar {
    dependsOn("relocateFabricJar")
    from(jarToFiles("relocateFabricJar"))
    dependsOn("relocateForgeJar")
    from(jarToFiles("relocateForgeJar"))
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}
