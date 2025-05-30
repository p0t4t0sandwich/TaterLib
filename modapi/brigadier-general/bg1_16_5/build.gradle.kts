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
    create("fabric") {
        compileClasspath += sourceSets.main.get().output
        runtimeClasspath += sourceSets.main.get().output
    }
    create("forge") {
        compileClasspath += sourceSets.main.get().output
        runtimeClasspath += sourceSets.main.get().output
    }
    create("sponge") {
        compileClasspath += sourceSets.main.get().output
        runtimeClasspath += sourceSets.main.get().output
    }
}

@Suppress("UnstableApiUsage")
configurations {
    val mainCompileOnly by creating
    named("compileOnly") {
        extendsFrom(configurations.getByName("fabricCompileOnly"))
        extendsFrom(configurations.getByName("forgeCompileOnly"))
        extendsFrom(configurations.getByName("spongeCompileOnly"))
    }
    val modImplementation by creating
    named("modImplementation") {
        extendsFrom(configurations.getByName("fabricImplementation"))
    }
}

// ------------------------------------------- Vanilla -------------------------------------------
unimined.minecraft {
    version(minecraftVersion)
    mappings {
        mojmap()
        devFallbackNamespace("official")
    }
    defaultRemapJar = false
}

tasks.jar {
    archiveClassifier.set("vanilla")
}

// ------------------------------------------- Fabric -------------------------------------------
unimined.minecraft(sourceSets.getByName("fabric")) {
    combineWith(sourceSets.main.get())
    fabric {
        loader(fabricLoaderVersion)
    }
    defaultRemapJar = true
}

tasks.named<RemapJarTask>("remapFabricJar") {
    asJar.archiveClassifier.set("fabric-remap")
    mixinRemap {
        disableRefmap()
    }
}

tasks.register<ShadowJar>("relocateFabricJar") {
    dependsOn("remapFabricJar")
    from(jarToFiles("remapFabricJar"))
    archiveClassifier.set("fabric")
    dependencies {
        exclude("dev/neuralnexus/modapi/briggen/mixin/v1_16_5/vanilla/**")
    }
    relocate("dev.neuralnexus.modapi.briggen.v1_16_5.vanilla", "dev.neuralnexus.modapi.briggen.v1_16_5.y_intmdry")
    relocate("dev.neuralnexus.modapi.briggen.v1_14_4.vanilla", "dev.neuralnexus.modapi.briggen.v1_14_4.y_intmdry")
}

// ------------------------------------------- Forge -------------------------------------------
unimined.minecraft(sourceSets.getByName("forge")) {
    combineWith(sourceSets.main.get())
    minecraftForge {
        loader(forgeVersion)
        mixinConfig("brigadier_general.mixins.v1_16_5.forge.json")
    }
    defaultRemapJar = true
}

tasks.named<RemapJarTask>("remapForgeJar") {
    asJar.archiveClassifier.set("forge-remap")
    mixinRemap {
        disableRefmap()
    }
}

tasks.register<ShadowJar>("relocateForgeJar") {
    dependsOn("remapForgeJar")
    from(jarToFiles("remapForgeJar"))
    archiveClassifier.set("forge")
    dependencies {
        exclude("dev/neuralnexus/modapi/briggen/mixin/v1_16_5/vanilla/**")
    }
    relocate("dev.neuralnexus.modapi.briggen.v1_16_5.vanilla", "dev.neuralnexus.modapi.briggen.v1_16_5.l_searge")
    relocate("dev.neuralnexus.modapi.briggen.v1_14_4.vanilla", "dev.neuralnexus.modapi.briggen.v1_14_4.l_searge")
}

// ------------------------------------------- Sponge -------------------------------------------
tasks.register<Jar>("spongeJar") {
    archiveClassifier.set("sponge")
    from(sourceSets.getByName("sponge").output)
}

// ------------------------------------------- Common -------------------------------------------
dependencies {
    // Because gradle does things alphabetically. I hate this
    // evaluationDependsOn(":modapi:brigadier-general:bg-api")
    // TODO: Uncomment when JVMDowngrader is applied to this project

    listOf(
        libs.mixin,
        variantOf(libs.modapi.brigadier) {
            classifier("downgraded-8")
        },
        variantOf(libs.modapi) {
            classifier("downgraded-8")
        }
    ).forEach {
        "mainCompileOnly"(it)
        "fabricCompileOnly"(it)
        "forgeCompileOnly"(it)
        "spongeCompileOnly"(it)
    }

//    listOf(
//        "fabric-api-base",
//        "fabric-command-api-v1",
//        "fabric-lifecycle-events-v1",
//        "fabric-networking-api-v1"
//    ).forEach {
//        "fabricModImplementation"(fabricApi.fabricModule(it, fabricVersion))
//    }

//    "forgeCompileOnly"(srcSetAsDep(":versions:modern-utils", "forge"))
    "spongeCompileOnly"("org.spongepowered:spongeapi:${spongeVersion}")
}

tasks.shadowJar {
    listOf(
        "relocateFabricJar",
        "relocateForgeJar",
        "spongeJar"
    ).forEach {
        dependsOn(it)
        from(jarToFiles(it))
    }
    archiveClassifier.set("")
}

tasks.build.get().dependsOn(tasks.shadowJar)
