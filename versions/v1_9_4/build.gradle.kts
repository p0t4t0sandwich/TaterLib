plugins {
    id(libs.plugins.unimined.get().pluginId)
}

val (fabric, forge, _, _) = createPlatformSourceSets("fabric", "forge")
val (mainCompileOnly, fabricCompileOnly, forgeCompileOnly, _, _, fabricModImplementation
) = createPlatformConfigurations("fabric", "forge")

unimined.minecraft {
    version(minecraftVersion)
    mappings {
        calamus()
        feather(28)
        stub.withMappings("searge", "intermediary") {
            // METHODs net/minecraft/unmapped/C_9482745/[m_9076954, getMaxSpeed]()D -> getMaxSpeed
            c(
                "aah",
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
    depVersions = listOf("1.8.9", "1.7.10")
)

unimined.minecraft(forge) {
    combineWith(sourceSets.main.get())
    minecraftForge {
        loader(forgeVersion)
        mixinConfig("taterlib.mixins.v1_9_4.forge.json")
    }
    defaultRemapJar = true
}

registerRelocationTask(
    platform = "forge",
    version = minecraftVersion,
    relocate = "vanilla" to "l_searge",
    depVersions = listOf("1.8.9", "1.7.10")
)

dependencies {
    listOf(":versions:v1_7_10", ":versions:v1_8_9").forEach {
        mainCompileOnly(project(it))
    }
    fabricCompileOnly(srcSetAsDep(":versions:v1_7_10", "fabric"))
    listOf("api-base", "command-api-v2", "lifecycle-events-v1", "networking-api-v1", "permissions-api-v1").forEach {
        fabricModImplementation(fabricApi.legacyFabricModule("legacy-fabric-$it", fabricVersion))
    }
    forgeCompileOnly(libs.mixin)
    forgeCompileOnly(srcSetAsDep(":versions:v1_8_9", "forge"))
}

tasks.register<Jar>("outputJar") {
    dependsOn("relocateFabricJar")
    from(jarToFiles("relocateFabricJar"))
    dependsOn("relocateForgeJar")
    from(jarToFiles("relocateForgeJar"))
}
tasks.build.get().dependsOn("outputJar")
