import xyz.wagyourtail.unimined.api.minecraft.task.RemapJarTask

plugins {
    id(libs.plugins.unimined.get().pluginId)
}

base {
    archivesName = "${projectId}-forge-${minecraftVersion}"
}

java.toolchain.languageVersion = JavaLanguageVersion.of(javaVersion)
java.sourceCompatibility = JavaVersion.toVersion(javaVersion)
java.targetCompatibility = JavaVersion.toVersion(javaVersion)

unimined.minecraft(sourceSets.main.get()) {
    version(minecraftVersion)
    minecraftForge {
        loader(apiVersion)
        mixinConfig("taterlib.mixins.v1_7_10.forge.json")
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
    compileOnly(variantOf(libs.modapi) {
        classifier("downgraded-8")
    })
}
