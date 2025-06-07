plugins {
    alias(libs.plugins.shadow)
    id(libs.plugins.unimined.get().pluginId)
}

val (_, _, _, sponge) = createPlatformSourceSets("sponge")
val (mainCompileOnly, _, _, _, spongeCompileOnly, _) = createPlatformConfigurations("sponge")

unimined.minecraft {
    version(minecraftVersion)
    mappings {
        parchment(parchmentMinecraft, parchmentVersion)
        mojmap()
        devFallbackNamespace("official")
    }
    defaultRemapJar = false
}

unimined.minecraft(sponge) {
    combineWith(sourceSets.main.get())
    defaultRemapJar = true
}

dependencies {
    spongeCompileOnly("org.spongepowered:spongeapi:${spongeVersion}")
    spongeCompileOnly(srcSetAsDep(":versions:v1_16_5", "sponge"))
    spongeCompileOnly(srcSetAsDep(":versions:v1_19_4", "sponge"))
    spongeCompileOnly(srcSetAsDep(":versions:v1_20_6", "sponge"))
}

tasks.jar {
    from(sponge.output)
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}
