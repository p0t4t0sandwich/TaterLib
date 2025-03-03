import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import xyz.wagyourtail.unimined.api.minecraft.task.RemapJarTask

plugins {
    alias(libs.plugins.shadow)
    id(libs.plugins.unimined.get().pluginId)
}

base {
    archivesName = "${projectId}-${minecraftVersion}"
}

java.toolchain.languageVersion = JavaLanguageVersion.of(javaVersion)
java.sourceCompatibility = JavaVersion.toVersion(javaVersion)
java.targetCompatibility = JavaVersion.toVersion(javaVersion)

sourceSets {
    create("forge") {
        compileClasspath += sourceSets.main.get().output
        runtimeClasspath += sourceSets.main.get().output
    }
}

@Suppress("UnstableApiUsage")
configurations {
    val mainCompileOnly by creating
    named("compileOnly") {
        extendsFrom(configurations.getByName("forgeCompileOnly"))
    }
}

// ------------------------------------------- Vanilla -------------------------------------------
unimined.minecraft {
    version(minecraftVersion)
    mappings {
        searge()
        mcp(mappingsChannel, mappingsVersion)
    }
    defaultRemapJar = false
}

tasks.jar {
    archiveClassifier.set("vanilla")
}

// ------------------------------------------- Forge -------------------------------------------
unimined.minecraft(sourceSets.getByName("forge")) {
    combineWith(sourceSets.main.get())
    minecraftForge {
        loader(forgeVersion)
//        mixinConfig("taterlib.mixins.v1_13_2.forge.json")
    }
    defaultRemapJar = true
}

tasks.named<RemapJarTask>("remapForgeJar") {
    asJar.archiveClassifier.set("forge-remap")
//    mixinRemap {
//        disableRefmap()
//    }
}

tasks.register<ShadowJar>("relocateForgeJar") {
    dependsOn("remapForgeJar")
    from(jarToFiles("remapForgeJar"))
    archiveClassifier.set("forge")
    dependencies {
//        exclude("dev/neuralnexus/taterlib/mixin/v1_13_2/vanilla/**")
    }
    relocate("dev.neuralnexus.taterlib.v1_13_2.vanilla", "dev.neuralnexus.taterlib.v1_13_2.l_searge")
}

// ------------------------------------------- Common -------------------------------------------
dependencies {
    listOf(
        libs.mixin,
        project(":api"),
        project(":common"),
        variantOf(libs.modapi) {
            classifier("downgraded-8")
        }
    ).forEach {
        "mainCompileOnly"(it)
        "forgeCompileOnly"(it)
    }

    "forgeCompileOnly"(srcSetAsDep(":versions:modern-utils", "forge"))
}

tasks.shadowJar {
    dependsOn("relocateForgeJar")
    from(jarToFiles("relocateForgeJar"))
    archiveClassifier.set("")
}

tasks.build.get().dependsOn(tasks.shadowJar)
