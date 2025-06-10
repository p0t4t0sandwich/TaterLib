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
        // Fix from https://github.com/CDAGaming/CraftPresence-Mirror/blob/0388d7bdc99440a3c2c317ab59cf728c9033b5da/build.gradle.kts#L253
        stub.withMappings("searge", "mojmap") {
            // METHODs dng/[getHeight, e]()I -> getHeight
            c(
                "dng",
                listOf(
                    "net/minecraft/client/gui/widget/Widget",
                    "net/minecraft/client/gui/components/AbstractWidget"
                )
            ) {
                m("e", "()I", "func_238483_d_", "getHeightRealms")
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
    relocate = "vanilla" to "y_intmdry",
    depVersions = listOf("1.14.4")
)

unimined.minecraft(forge) {
    combineWith(sourceSets.main.get())
    minecraftForge {
        loader(forgeVersion)
        mixinConfig("taterlib.mixins.v1_16_1.forge.json")
    }
    defaultRemapJar = true
}

registerRelocationTask(
    platform = "forge",
    version = minecraftVersion,
    relocate = "vanilla" to "l_searge",
    depVersions = listOf("1.14.4")
)

dependencies {
    mainCompileOnly(project(":versions:v1_14_4"))
    listOf("api-base", "command-api-v1", "lifecycle-events-v1", "networking-api-v1").forEach {
        fabricModImplementation(fabricApi.fabricModule("fabric-$it", fabricVersion))
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
