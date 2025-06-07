import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    alias(libs.plugins.shadow)
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

unimined.minecraft(fabric) {
    combineWith(sourceSets.main.get())
    fabric {
        loader(fabricLoaderVersion)
    }
    defaultRemapJar = true
}

tasks.register<ShadowJar>("relocateFabricJar") {
    dependsOn("remapFabricJar")
    from(jarToFiles("remapFabricJar"))
    archiveClassifier.set("fabric-relocated")
    dependencies {
        exclude("dev/neuralnexus/taterlib/mixin/v1_7_10/vanilla/**")
    }
    relocate("dev.neuralnexus.taterlib.v1_7_10.vanilla", "dev.neuralnexus.taterlib.v1_7_10.l_intmdry")
}

unimined.minecraft(forge) {
    combineWith(sourceSets.main.get())
    minecraftForge {
        loader(forgeVersion)
        mixinConfig("taterlib.mixins.v1_7_10.forge.json")
    }
    defaultRemapJar = true
}

tasks.register<ShadowJar>("relocateForgeJar") {
    dependsOn("remapForgeJar")
    from(jarToFiles("remapForgeJar"))
    archiveClassifier.set("forge-relocated")
    dependencies {
        exclude("dev/neuralnexus/taterlib/mixin/v1_7_10/vanilla/**")
    }
    relocate("dev.neuralnexus.taterlib.v1_7_10.vanilla", "dev.neuralnexus.taterlib.v1_7_10.l_searge")
}

dependencies {
    listOf("api-base", "command-api-v2", "lifecycle-events-v1", "networking-api-v1", "permissions-api-v1").forEach {
        fabricModImplementation(fabricApi.legacyFabricModule("legacy-fabric-$it", fabricVersion))
    }
    forgeCompileOnly(libs.mixin)
}

tasks.jar {
    // Main isn't needed in the final Jar, but gradle screams otherwise
    dependsOn("relocateFabricJar")
    from(jarToFiles("relocateFabricJar"))
    dependsOn("relocateForgeJar")
    from(jarToFiles("relocateForgeJar"))
}
