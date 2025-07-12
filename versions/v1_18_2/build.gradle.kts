plugins {
    id(libs.plugins.unimined.get().pluginId)
}

val (main, _, forge, _, sponge) = getPlatforms("forge", "sponge")

unimined.minecraft {
    version(minecraftVersion)
    mappings {
        parchment(parchmentMinecraft, parchmentVersion)
        mojmap()
        devFallbackNamespace("official")
    }
    defaultRemapJar = false
}

unimined.minecraft(forge.sourceSet) {
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

unimined.minecraft(sponge.sourceSet) {
    combineWith(sourceSets.main.get())
}

dependencies {
    listOf(":versions:v1_14_4", ":versions:v1_16_1").forEach {
        main.compileOnly(project(it))
    }
    forge.compileOnly(srcSetAsDep(":versions:modern-utils", "forge"))
    sponge.compileOnly("org.spongepowered:spongeapi:${spongeVersion}")
    sponge.compileOnly(srcSetAsDep(":versions:v1_16_5", "sponge"))
}

tasks.jar {
    dependsOn("relocateForgeJar")
    from(jarToFiles("relocateForgeJar"))
    from(sponge.sourceSet.output)
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}
