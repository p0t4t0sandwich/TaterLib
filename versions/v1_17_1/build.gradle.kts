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
        exclude("dev/neuralnexus/taterlib/mixin/v1_17_1/vanilla/**")
    }
    relocate("dev.neuralnexus.taterlib.v1_17_1.vanilla", "dev.neuralnexus.taterlib.v1_17_1.y_intmdry")
    relocate("dev.neuralnexus.taterlib.v1_16_1.vanilla", "dev.neuralnexus.taterlib.v1_16_1.y_intmdry")
    relocate("dev.neuralnexus.taterlib.v1_14_4.vanilla", "dev.neuralnexus.taterlib.v1_14_4.y_intmdry")
}

tasks.register<ShadowJar>("shadeForgeJar") {
    from(sourceSets.main.get().output)
    from(forge.output)
    from(project(":versions:v1_14_4").sourceSets.main.get().output)
    from(project(":versions:v1_16_1").sourceSets.main.get().output)
    archiveClassifier.set("forge-shade")
    dependencies {
        exclude("dev/neuralnexus/taterlib/mixin/v1_17_1/vanilla/**")
        exclude("dev/neuralnexus/taterlib/mixin/v1_16_1/vanilla/**")
        exclude("dev/neuralnexus/taterlib/mixin/v1_14_4/vanilla/**")
    }
    relocate("dev.neuralnexus.taterlib.v1_17_1.vanilla", "dev.neuralnexus.taterlib.v1_17_1.searge")
    relocate("dev.neuralnexus.taterlib.v1_16_1.vanilla", "dev.neuralnexus.taterlib.v1_16_1.searge")
    relocate("dev.neuralnexus.taterlib.v1_14_4.vanilla", "dev.neuralnexus.taterlib.v1_14_4.searge")
}

unimined.minecraft(forge) {
    combineWith(sourceSets.main.get())
    minecraftForge {
        loader(forgeVersion)
        mixinConfig("taterlib.mixins.v1_17_1.forge.json")
    }
    remap(tasks.getByName<ShadowJar>("shadeForgeJar"), "remapForgeJar") {
        asJar.archiveClassifier.set("forge")
    }
}

dependencies {
    listOf(":versions:v1_14_4", ":versions:v1_16_1").forEach {
        mainCompileOnly(project(it))
    }
    listOf("api-base", "command-api-v1", "lifecycle-events-v1", "networking-api-v1").forEach {
        fabricModImplementation(fabricApi.fabricModule("fabric-$it", fabricVersion))
    }
    forgeCompileOnly(srcSetAsDep(":versions:modern-utils", "forge"))
}

tasks.jar {
    dependsOn("relocateFabricJar")
    from(jarToFiles("relocateFabricJar"))
    dependsOn("remapForgeJar")
    from(jarToFiles("remapForgeJar"))
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}
