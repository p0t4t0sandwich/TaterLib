plugins {
    id(libs.plugins.unimined.get().pluginId)
}

val (fabric, forge, _, sponge) = createPlatformSourceSets("fabric", "forge", "sponge")
val (mainCompileOnly, fabricCompileOnly, forgeCompileOnly, _, spongeCompileOnly, fabricModImplementation
) = createPlatformConfigurations("fabric", "forge", "sponge")

unimined.minecraft {
    version(minecraftVersion)
    mappings {
        calamus()
        feather(28)
        stub.withMappings("searge", "intermediary") {
            // METHODs net/minecraft/unmapped/C_9482745/[m_9076954, getMaxSpeed]()D -> getMaxSpeed
            c(
                "aaq",
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
    relocate = "vanilla" to "l_intmdry",
    depVersions = listOf("1.9.4", "1.8.9", "1.7.10")
)

unimined.minecraft(forge) {
    combineWith(sourceSets.main.get())
    combineWith(sponge)
    minecraftForge {
        loader(forgeVersion)
        mixinConfig("taterlib.mixins.v1_10_2.forge.json")
    }
    defaultRemapJar = true
}

registerRelocationTask(
    platform = "forge",
    version = minecraftVersion,
    relocate = "vanilla" to "l_searge",
    depVersions = listOf("1.9.4", "1.8.9", "1.7.10")
)

unimined.minecraft(sponge) {
    combineWith(sourceSets.main.get())
}

dependencies {
    // Because gradle does things alphabetically. I hate this
    evaluationDependsOn(":versions:v1_7_10")
    evaluationDependsOn(":versions:v1_8_9")
    evaluationDependsOn(":versions:v1_9_4")
    listOf(":versions:v1_7_10", ":versions:v1_8_9").forEach {
        mainCompileOnly(project(it))
    }
    fabricCompileOnly(srcSetAsDep(":versions:v1_7_10", "fabric"))
    listOf("api-base", "command-api-v2", "lifecycle-events-v1", "networking-api-v1", "permissions-api-v1").forEach {
        fabricModImplementation(fabricApi.legacyFabricModule("legacy-fabric-$it", fabricVersion))
    }
    forgeCompileOnly(libs.mixin)
    "fabricCompileOnly"(srcSetAsDep(":versions:v1_7_10", "fabric"))
    "forgeCompileOnly"(srcSetAsDep(":versions:v1_8_9", "forge"))
    "forgeCompileOnly"(srcSetAsDep(":versions:v1_9_4", "forge"))
    "spongeCompileOnly"("org.spongepowered:spongeapi:${spongeVersion}")
    "spongeCompileOnly"(srcSetAsDep(":versions:v1_8_9", "sponge"))
}

tasks.jar {
    dependsOn("relocateFabricJar")
    from(jarToFiles("relocateFabricJar"))
    dependsOn("relocateForgeJar")
    from(jarToFiles("relocateForgeJar"))
}
