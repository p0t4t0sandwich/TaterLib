import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import xyz.wagyourtail.unimined.api.minecraft.task.RemapJarTask

plugins {
    alias(libs.plugins.shadow)
    alias(libs.plugins.unimined)
}

base {
    archivesName = "${projectId}-modern-utils"
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
}

@Suppress("UnstableApiUsage")
configurations {
    val mainCompileOnly by creating
    named("compileOnly") {
        extendsFrom(configurations.getByName("fabricCompileOnly"))
        extendsFrom(configurations.getByName("forgeCompileOnly"))
    }
    val modImplementation by creating
    named("modImplementation") {
        extendsFrom(configurations.getByName("fabricImplementation"))
    }
}

// ------------------------------------------- Vanilla -------------------------------------------
unimined.minecraft {
    version("1.14.4")
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

// ------------------------------------------- Forge -------------------------------------------
unimined.minecraft(sourceSets.getByName("forge")) {
    combineWith(sourceSets.main.get())
    minecraftForge {
        loader(forgeVersion)
    }
    defaultRemapJar = true
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
        "fabricCompileOnly"(it)
        "forgeCompileOnly"(it)
    }
}

tasks.named<ShadowJar>("shadowJar") {
    from(tasks.getByName("remapFabricJar").outputs)
    from(tasks.getByName("remapForgeJar").outputs)
    archiveClassifier.set("")
}

tasks.build.get().dependsOn(tasks.shadowJar)
