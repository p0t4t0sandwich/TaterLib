plugins {
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

registerRelocationTask(
    platform = "forge",
    version = minecraftVersion,
    relocate = "vanilla" to "searge",
    depVersions = listOf("1.16.1", "1.14.4")
)

unimined.minecraft(sponge) {
    combineWith(sourceSets.main.get())
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
