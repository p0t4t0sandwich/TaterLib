plugins {
    id(libs.plugins.unimined.get().pluginId)
}

val (main, fabric, forge, _, sponge) = getPlatforms("fabric", "forge", "sponge")

unimined.minecraft {
    version(minecraftVersion)
    mappings {
        calamus()
        feather(28)
        stub.withMappings("searge", "intermediary") {
            // METHODs net/minecraft/unmapped/C_9482745/[m_9076954, getMaxSpeed]()D -> getMaxSpeed
            c(
                "va",
                listOf(
                    "net/minecraft/entity/item/EntityMinecart",
                    "net/minecraft/entity/vehicle/MinecartEntity"
                )
            ) {
                m("getMaxSpeed", "()D", "m_9076954", "getMaxSpeedForge")
            }
        }
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
    relocate = "vanilla" to "l_intmdry",
    depVersions = listOf("1.7.10")
)

unimined.minecraft(forge.sourceSet) {
    combineWith(sourceSets.main.get())
    combineWith(sponge.sourceSet)
    minecraftForge {
        loader(forgeVersion)
        mixinConfig("taterlib.mixins.v1_8_9.forge.json")
    }
    defaultRemapJar = true
}

registerRelocationTask(
    platform = "forge",
    version = minecraftVersion,
    relocate = "vanilla" to "l_searge",
    depVersions = listOf("1.7.10")
)

unimined.minecraft(sponge.sourceSet) {
    combineWith(sourceSets.main.get())
}

dependencies {
    main.compileOnly(project(":versions:v1_7_10"))
    fabric.compileOnly(srcSetAsDep(":versions:v1_7_10", "fabric"))
    listOf("api-base", "command-api-v2", "lifecycle-events-v1", "networking-api-v1", "permissions-api-v1").forEach {
        fabric.modImplementation(fabricApi.legacyFabricModule("legacy-fabric-$it", fabricVersion))
    }
    forge.compileOnly(libs.mixin)
    forge.compileOnly(srcSetAsDep(":versions:v1_7_10", "forge"))
    sponge.compileOnly("org.spongepowered:spongeapi:${spongeVersion}")
}

tasks.jar {
    dependsOn("relocateFabricJar")
    from(jarToFiles("relocateFabricJar"))
    dependsOn("relocateForgeJar")
    from(jarToFiles("relocateForgeJar"))
}
