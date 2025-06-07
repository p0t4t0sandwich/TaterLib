import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    alias(libs.plugins.shadow)
    id(libs.plugins.unimined.get().pluginId)
}

val (fabric, forge, neoforge, _) = createPlatformSourceSets("fabric", "forge", "neoforge")
val (mainCompileOnly, fabricCompileOnly, forgeCompileOnly,
    neoforgeCompileOnly, _, fabricModImplementation
) = createPlatformConfigurations("fabric", "forge", "neoforge")

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

tasks.register<ShadowJar>("relocateFabricJar") {
    dependsOn("remapFabricJar")
    from(jarToFiles("remapFabricJar"))
    archiveClassifier.set("fabric-relocated")
    dependencies {
        exclude("dev/neuralnexus/taterlib/mixin/v1_20_2/vanilla/**")
    }
    relocate("dev.neuralnexus.taterlib.v1_20_2.vanilla", "dev.neuralnexus.taterlib.v1_20_2.y_intmdry")
    relocate("dev.neuralnexus.taterlib.v1_16_1.vanilla", "dev.neuralnexus.taterlib.v1_16_1.y_intmdry")
    relocate("dev.neuralnexus.taterlib.v1_14_4.vanilla", "dev.neuralnexus.taterlib.v1_14_4.y_intmdry")
}

unimined.minecraft(forge) {
    combineWith(sourceSets.main.get())
    minecraftForge {
        loader(forgeVersion)
        mixinConfig("taterlib.mixins.v1_20_2.forge.json")
    }
    defaultRemapJar = true
}

tasks.register<ShadowJar>("relocateForgeJar") {
    dependsOn("remapForgeJar")
    from(jarToFiles("remapForgeJar"))
    archiveClassifier.set("forge-relocated")
    dependencies {
        exclude("dev/neuralnexus/taterlib/mixin/v1_20_2/vanilla/**")
    }
    relocate("dev.neuralnexus.taterlib.v1_20_2.vanilla", "dev.neuralnexus.taterlib.v1_20_2.searge")
    relocate("dev.neuralnexus.taterlib.v1_16_1.vanilla", "dev.neuralnexus.taterlib.v1_16_1.searge")
    relocate("dev.neuralnexus.taterlib.v1_14_4.vanilla", "dev.neuralnexus.taterlib.v1_14_4.searge")
}

unimined.minecraft(neoforge) {
    combineWith(sourceSets.main.get())
    neoForge {
        loader(neoForgeVersion)
    }
    defaultRemapJar = true
}

dependencies {
    listOf(":versions:v1_14_4", ":versions:v1_16_1").forEach {
        mainCompileOnly(project(it))
    }
    listOf("api-base", "command-api-v2", "lifecycle-events-v1", "networking-api-v1").forEach {
        fabricModImplementation(fabricApi.fabricModule("fabric-$it", fabricVersion))
    }
    forgeCompileOnly(srcSetAsDep(":versions:v1_20_1", "forge"))
    forgeCompileOnly(srcSetAsDep(":versions:modern-utils", "forge"))
}

tasks.jar {
    dependsOn("relocateFabricJar")
    from(jarToFiles("relocateFabricJar"))
    dependsOn("relocateForgeJar")
    from(jarToFiles("relocateForgeJar"))
    from(neoforge.output)
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}
