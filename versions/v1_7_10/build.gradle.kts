plugins {
    id(libs.plugins.unimined.get().pluginId)
    // id(libs.plugins.shadow.get().pluginId)
}

val (main, fabric, forge, _, _) = getPlatforms("fabric", "forge")

unimined.minecraft {
    version(minecraftVersion)
    mappings {
        calamus()
        feather(28)
        stub.withMappings("searge", "intermediary") {
            // METHODs cpw/mods/fml/common/registry/FMLControlledNamespacedRegistry/[net/minecraft/unmapped/C_7135514/m_1782140, get](Ljava/lang/String;)Ljava/lang/Object; -> get
            c(
                "cpw/mods/fml/common/registry/FMLControlledNamespacedRegistry", listOf()
            ) {
                m("get", "(Ljava/lang/String;)Ljava/lang/Object;", "net/minecraft/unmapped/C_7135514/m_1782140", "getObjectFromString")
            }
            // METHODs cpw/mods/fml/common/registry/FMLControlledNamespacedRegistry/[net/minecraft/unmapped/C_7135514/m_9381448, get](I)Ljava/lang/Object; -> get
            c(
                "cpw/mods/fml/common/registry/FMLControlledNamespacedRegistry", listOf()
            ) {
                m("get", "(I)Ljava/lang/Object;", "net/minecraft/unmapped/C_7135514/m_9381448", "getObjectFromInteger")
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
    relocate = "vanilla" to "l_intmdry"
)

unimined.minecraft(forge.sourceSet) {
    combineWith(sourceSets.main.get())
    minecraftForge {
        loader(forgeVersion)
        mixinConfig("taterlib.mixins.v1_7_10.forge.json")
    }
    defaultRemapJar = true
}

registerRelocationTask(
    platform = "forge",
    version = minecraftVersion,
    relocate = "vanilla" to "l_searge"
)

dependencies {
    listOf("api-base", "command-api-v2", "lifecycle-events-v1", "networking-api-v1", "permissions-api-v1").forEach {
        fabric.modImplementation(fabricApi.legacyFabricModule("legacy-fabric-$it", fabricVersion))
    }
    forge.compileOnly(libs.mixin)
}

tasks.jar {
    // Main isn't needed in the final Jar, but gradle screams otherwise
    dependsOn("relocateFabricJar")
    from(jarToFiles("relocateFabricJar"))
    dependsOn("relocateForgeJar")
    from(jarToFiles("relocateForgeJar"))
}
