import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    alias(libs.plugins.shadow)
    id(libs.plugins.unimined.get().pluginId)
}

val (fabric, _, neoforge, sponge) = createPlatformSourceSets("fabric", "neoforge", "sponge")
val (mainCompileOnly, fabricCompileOnly, _, neoforgeCompileOnly, spongeCompileOnly, fabricModImplementation
) = createPlatformConfigurations("fabric", "neoforge", "sponge")

unimined.minecraft {
    version(minecraftVersion)
    mappings {
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
        exclude("dev/neuralnexus/taterlib/mixin/v1_20_6/vanilla/**")
    }
    relocate("dev.neuralnexus.taterlib.v1_20_6.vanilla", "dev.neuralnexus.taterlib.v1_20_6.y_intmdry")
    relocate("dev.neuralnexus.taterlib.v1_20_2.vanilla", "dev.neuralnexus.taterlib.v1_20_2.y_intmdry")
    relocate("dev.neuralnexus.taterlib.v1_16_1.vanilla", "dev.neuralnexus.taterlib.v1_16_1.y_intmdry")
    relocate("dev.neuralnexus.taterlib.v1_14_4.vanilla", "dev.neuralnexus.taterlib.v1_14_4.y_intmdry")
}

unimined.minecraft(neoforge) {
    combineWith(sourceSets.main.get())
    neoForge {
        loader(neoForgeVersion)
    }
    defaultRemapJar = true
}

unimined.minecraft(sponge) {
    combineWith(sourceSets.main.get())
    defaultRemapJar = true
}

dependencies {
    listOf(":versions:v1_14_4", ":versions:v1_16_1", ":versions:v1_20_2").forEach {
        mainCompileOnly(project(it))
    }
    neoforgeCompileOnly(srcSetAsDep(":versions:v1_20_2", "neoforge"))
    spongeCompileOnly("org.spongepowered:spongeapi:${spongeVersion}")
    spongeCompileOnly(srcSetAsDep(":versions:v1_16_5", "sponge"))
    spongeCompileOnly(srcSetAsDep(":versions:v1_19_4", "sponge"))
}

tasks.jar {
    dependsOn("relocateFabricJar")
    from(jarToFiles("relocateFabricJar"))
    from(neoforge.output)
    from(sponge.output)
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}
