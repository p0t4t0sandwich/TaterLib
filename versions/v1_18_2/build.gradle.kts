import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    alias(libs.plugins.shadow)
    id(libs.plugins.unimined.get().pluginId)
}

val (_, forge, _, sponge) = createPlatformSourceSets("forge", "sponge")
val (mainCompileOnly, _, forgeCompileOnly, _, spongeCompileOnly, _
) = createPlatformConfigurations("forge", "sponge")

unimined.minecraft {
    version(minecraftVersion)
    mappings {
        parchment(parchmentMinecraft, parchmentVersion)
        mojmap()
        devFallbackNamespace("official")
    }
    defaultRemapJar = false
}

unimined.minecraft(forge) {
    combineWith(sourceSets.main.get())
    minecraftForge {
        loader(forgeVersion)
        mixinConfig("taterlib.mixins.v1_18_2.forge.json")
    }
    defaultRemapJar = true
}

tasks.register<ShadowJar>("relocateForgeJar") {
    dependsOn("remapForgeJar")
    from(jarToFiles("remapForgeJar"))
    archiveClassifier.set("forge-relocated")
    dependencies {
        exclude("dev/neuralnexus/taterlib/mixin/v1_18_2/vanilla/**")
    }
    relocate("dev.neuralnexus.taterlib.v1_18_2.vanilla", "dev.neuralnexus.taterlib.v1_18_2.searge")
    relocate("dev.neuralnexus.taterlib.v1_16_1.vanilla", "dev.neuralnexus.taterlib.v1_16_1.searge")
    relocate("dev.neuralnexus.taterlib.v1_14_4.vanilla", "dev.neuralnexus.taterlib.v1_14_4.searge")
}

unimined.minecraft(sponge) {
    combineWith(sourceSets.main.get())
    defaultRemapJar = true
}

dependencies {
    listOf(":versions:v1_14_4", ":versions:v1_16_1").forEach {
        mainCompileOnly(project(it))
    }
    forgeCompileOnly(srcSetAsDep(":versions:modern-utils", "forge"))
    spongeCompileOnly("org.spongepowered:spongeapi:${spongeVersion}")
    spongeCompileOnly(srcSetAsDep(":versions:v1_16_5", "sponge"))
}

tasks.jar {
    dependsOn("relocateForgeJar")
    from(jarToFiles("relocateForgeJar"))
    from(sponge.output)
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}
