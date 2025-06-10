import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    id(libs.plugins.unimined.get().pluginId)
}

val (_, forge, _, _) = createPlatformSourceSets("forge")
val (mainCompileOnly, _, forgeCompileOnly, _, _, _) = createPlatformConfigurations("forge")

unimined.minecraft {
    version(minecraftVersion)
    mappings {
        parchment(parchmentMinecraft, parchmentVersion)
        mojmap()
        devFallbackNamespace("official")
    }
    defaultRemapJar = false
}

tasks.register<ShadowJar>("shadeForgeJar") {
    from(sourceSets.main.get().output)
    from(forge.output)
    from(project(":modapi:brigadier-general:bg1_14_4").sourceSets.main.get().output)
    from(project(":modapi:brigadier-general:bg1_16_5").sourceSets.main.get().output)
    archiveClassifier.set("forge-shade")
    dependencies {
        exclude("dev/neuralnexus/modapi/brigadier_general/mixin/v1_17_1/vanilla/**")
        exclude("dev/neuralnexus/modapi/brigadier_general/mixin/v1_16_5/vanilla/**")
        exclude("dev/neuralnexus/modapi/brigadier_general/mixin/v1_14_4/vanilla/**")
    }
    relocate("dev.neuralnexus.modapi.brigadier_general.v1_17_1.vanilla", "dev.neuralnexus.modapi.brigadier_general.v1_17_1.searge")
    relocate("dev.neuralnexus.modapi.brigadier_general.v1_16_5.vanilla", "dev.neuralnexus.modapi.brigadier_general.v1_16_5.searge")
    relocate("dev.neuralnexus.modapi.brigadier_general.v1_14_4.vanilla", "dev.neuralnexus.modapi.brigadier_general.v1_14_4.searge")
}

unimined.minecraft(forge) {
    combineWith(sourceSets.main.get())
    minecraftForge {
        loader(forgeVersion)
        mixinConfig("brigadier_general.mixins.v1_17_1.forge.json")
    }
    remap(tasks.getByName<ShadowJar>("shadeForgeJar"), "remapForgeJar") {
        asJar.archiveClassifier.set("forge")
    }
}

dependencies {
    // Because gradle does things alphabetically. I hate this
    // evaluationDependsOn(":modapi:brigadier-general:bg-api")
    // TODO: Uncomment when JVMDowngrader is applied to this project
}

tasks.jar {
    dependsOn("remapForgeJar")
    from(jarToFiles("remapForgeJar"))
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}
