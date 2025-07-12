plugins {
    id(libs.plugins.unimined.get().pluginId)
}

val (main, _, _, _, sponge) = getPlatforms("sponge")

unimined.minecraft {
    version(minecraftVersion)
    mappings {
        parchment(parchmentMinecraft, parchmentVersion)
        mojmap()
        devFallbackNamespace("official")
    }
    defaultRemapJar = false
}

unimined.minecraft(sponge.sourceSet) {
    combineWith(sourceSets.main.get())
}

dependencies {
    sponge.compileOnly("org.spongepowered:spongeapi:${spongeVersion}")
    sponge.compileOnly(srcSetAsDep(":versions:v1_16_5", "sponge"))
    sponge.compileOnly(srcSetAsDep(":versions:v1_19_4", "sponge"))
    sponge.compileOnly(srcSetAsDep(":versions:v1_20_6", "sponge"))
}

tasks.jar {
    from(sponge.sourceSet.output)
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}
