import xyz.wagyourtail.unimined.api.minecraft.task.RemapJarTask

plugins {
    alias(libs.plugins.unimined)
}

base {
    archivesName = "${projectId}-forge-${minecraftVersion}"
}

unimined.minecraft(sourceSets.main.get()) {
    version(minecraftVersion)
    minecraftForge {
        loader(apiVersion)
        mixinConfig("taterlib.mixins.v1_11_2.forge.json")
    }
    mappings {
        searge()
        mcp(mappingsChannel, mappingsVersion)
    }
}

tasks.named<RemapJarTask>("remapJar").configure {
    prodNamespace("searge")
    mixinRemap {
        disableRefmap()
    }
}

dependencies {
    compileOnly(libs.mixin)
    compileOnly(project(":api"))
    compileOnly(project(":common"))
    compileOnly(project(":loader"))
    compileOnly(variantOf(libs.modapi.muxins) {
        classifier("downgraded-8-all")
    })
}
