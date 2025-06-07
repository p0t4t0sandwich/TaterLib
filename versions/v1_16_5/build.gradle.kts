import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    alias(libs.plugins.shadow)
    id(libs.plugins.unimined.get().pluginId)
}

val (fabric, forge, _, sponge) = createPlatformSourceSets("fabric", "forge", "sponge")
val (mainCompileOnly, fabricCompileOnly, forgeCompileOnly, _, spongeCompileOnly, fabricModImplementation
) = createPlatformConfigurations("fabric", "forge", "sponge")

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
        exclude("dev/neuralnexus/taterlib/mixin/v1_16_5/vanilla/**")
    }
    relocate("dev.neuralnexus.taterlib.v1_16_5.vanilla", "dev.neuralnexus.taterlib.v1_16_5.y_intmdry")
    relocate("dev.neuralnexus.taterlib.v1_14_4.vanilla", "dev.neuralnexus.taterlib.v1_14_4.y_intmdry")
}

unimined.minecraft(forge) {
    combineWith(sourceSets.main.get())
    minecraftForge {
        loader(forgeVersion)
        mixinConfig("taterlib.mixins.v1_16_5.forge.json")
    }
    defaultRemapJar = true
}

tasks.register<ShadowJar>("relocateForgeJar") {
    dependsOn("remapForgeJar")
    from(jarToFiles("remapForgeJar"))
    archiveClassifier.set("forge-relocated")
    dependencies {
        exclude("dev/neuralnexus/taterlib/mixin/v1_16_5/vanilla/**")
    }
    relocate("dev.neuralnexus.taterlib.v1_16_5.vanilla", "dev.neuralnexus.taterlib.v1_16_5.l_searge")
    relocate("dev.neuralnexus.taterlib.v1_14_4.vanilla", "dev.neuralnexus.taterlib.v1_14_4.l_searge")
}

unimined.minecraft(sponge) {
    combineWith(sourceSets.main.get())
    defaultRemapJar = true
}

dependencies {
    mainCompileOnly(project(":versions:v1_14_4"))
    forgeCompileOnly(srcSetAsDep(":versions:modern-utils", "forge"))
    spongeCompileOnly("org.spongepowered:spongeapi:${spongeVersion}")
}

tasks.jar {
    dependsOn("relocateFabricJar")
    from(jarToFiles("relocateFabricJar"))
    dependsOn("relocateForgeJar")
    from(jarToFiles("relocateForgeJar"))
    from(sponge.output)
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}
