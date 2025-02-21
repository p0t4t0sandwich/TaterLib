import xyz.wagyourtail.unimined.api.minecraft.task.RemapJarTask

plugins {
    alias(libs.plugins.unimined)
}

base {
    archivesName = "${projectId}-fabric-${minecraftVersion}"
}

unimined.minecraft(sourceSets.main.get()) {
    version(minecraftVersion)
    fabric {
        loader(loaderVersion)
    }
    mappings {
        legacyIntermediary()
        legacyYarn(541)
    }
}

tasks.named<RemapJarTask>("remapJar").configure {
    mixinRemap {
        disableRefmap()
    }
}

dependencies {
    val apiModules = listOf(
        "legacy-fabric-api-base",
        "legacy-fabric-command-api-v2",
        "legacy-fabric-lifecycle-events-v1",
        "legacy-fabric-networking-api-v1",
        "legacy-fabric-permissions-api-v1"
    )

    apiModules.forEach {
        "modImplementation"(fabricApi.legacyFabricModule(it, apiVersion))
    }

    compileOnly(libs.mixin)
    compileOnly(project(":api"))
    compileOnly(project(":common"))
    compileOnly(project(":loader"))
    compileOnly(variantOf(libs.modapi) {
        classifier("downgraded-8")
    })
}
