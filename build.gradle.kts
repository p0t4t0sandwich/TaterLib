import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import xyz.wagyourtail.jvmdg.gradle.task.DowngradeJar
import xyz.wagyourtail.jvmdg.gradle.task.ShadeJar
import java.time.Instant

plugins {
    id("java")
    id("maven-publish")
    id("idea")
    id("eclipse")
    alias(libs.plugins.jvmdowngrader)
    //alias(libs.plugins.shadow)
    alias(libs.plugins.spotless)
    //alias(libs.plugins.unimined) apply(false)
}

subprojects {
    apply(plugin = "java")
    apply(plugin = "idea")
    apply(plugin = "eclipse")
    apply(plugin = rootProject.libs.plugins.spotless.get().pluginId)

    java.toolchain.languageVersion = JavaLanguageVersion.of(javaVersion)
    //java.sourceCompatibility = JavaVersion.toVersion(javaVersion)
    java.targetCompatibility = JavaVersion.toVersion(javaVersion)
    //java.disableAutoTargetJvm()

    tasks.withType<JavaCompile> {
        options.encoding = "UTF-8"
    }

    repositories {
        mavenLocal()
        mavenCentral()
        maven("https://maven.neuralnexus.dev/releases")
        maven("https://maven.neuralnexus.dev/snapshots")
        maven("https://maven.neuralnexus.dev/mirror")
        maven("https://libraries.minecraft.net/")
        maven("https://api.modrinth.com/maven")

        // Local Libs
        flatDir {
            dirs("${rootProject.projectDir}/libs")
        }
    }

    val mainCompileOnly: Configuration = configurations.maybeCreate("mainCompileOnly")
    configurations.compileOnly.get().extendsFrom(mainCompileOnly)

    dependencies {
        compileOnly(rootProject.libs.annotations)
        listOf(
                rootProject.libs.jspecify,
                rootProject.libs.taterlib.lite.base,
                rootProject.libs.taterlib.lite.core,
                rootProject.libs.taterlib.lite.metadata,
                rootProject.libs.taterlib.lite.muxins,
                rootProject.libs.taterlib.lite.network
        ).forEach {
            mainCompileOnly(it)
        }
    }

    spotless {
        format("misc") {
            target("*.gradle.kts", ".gitattributes", ".gitignore")
            trimTrailingWhitespace()
            leadingTabsToSpaces()
            endWithNewline()
        }
        java {
            target("src/**/*.java", "src/**/*.java.peb")
            toggleOffOn()
            importOrder()
            removeUnusedImports()
            cleanthat()
            googleJavaFormat("1.24.0")
                    .aosp()
                    .formatJavadoc(true)
                    .reorderImports(true)
            formatAnnotations()
            trimTrailingWhitespace()
            leadingTabsToSpaces()
            endWithNewline()
            licenseHeader("""/**
 * Copyright (c) 2025 $author
 * The project is Licensed under <a href="$sourceUrl/blob/dev/LICENSE">$license</a>
 */""") // TODO: Change "2025" to 2026", "The" to "This", and "dev" to "main"
        }
    }

    tasks.assemble.get().dependsOn(tasks.spotlessApply)
}

val vers = listOf(
        "v1_6_4", "v1_7_10", "v1_8_9", "v1_9_4", "v1_10_2", "v1_11_2", "v1_12_2",
        "v1_13_2", "v1_14_4", "v1_15_2", "v1_16_1", "v1_16_5",
        "v1_17_1", "v1_18_2", "v1_19", "v1_19_4", "v1_20_1",
        "v1_20_2", "v1_20_4", "v1_20_6", "v1_21_1", "v1_21_3", "v1_21_4"
)

val projs = vers.map { "versions:${it}" }.toMutableList()

var platformVersions = mutableMapOf<String, List<String>>()
platformVersions["bukkit"] = listOf(
        "utils", "b1.7.3", "1.2.5", "1.6.4", "1.7.10", "1.8.8", "1.13.2", "1.15.2", "1.20")
platformVersions["bungee"] = listOf("utils", "1.4.7", "1.8", "1.12", "1.20")
platformVersions["velocity"] = listOf("3")

platformVersions.forEach { (platform, versions) ->
    versions.forEach { mcVersion ->
        projs.add("${platform}:${platform}-${mcVersion}")
    }
}

val mergeMixins = tasks.register<MergeMixinConfigs>("mergeMixins") {
    dependsOn(":common:build")
    projs.forEach { dependsOn("$it:build") }

    val jars = mutableListOf<RegularFile>()
    jars.add(rootProject.project(":common").tasks.named<ShadowJar>("shadowJar").get().archiveFile.get())
    projs.forEach { jars.add(rootProject.project(it).tasks.jar.get().archiveFile.get()) }
    inputFiles.set(jars)
    outputFile.set(layout.buildDirectory.file("tmp/$modId.mixins.json"))

    config.set(mapOf(
            "compatibilityLevel" to "JAVA_8",
            "minVersion" to "0.8",
            "injectors" to mapOf("defaultRequire" to 1),
            "required" to true,
            "plugin" to "dev.neuralnexus.taterlib.mixin.plugin.TaterLibMixinPlugin",
            "package" to "dev.neuralnexus.taterlib.mixin"
    ))
    match.set("taterlib.mixins.*")
}

val shadeAndRelocate = tasks.register<ShadowJar>("shadeAndRelocate") {
    //relocate("dev.neuralnexus.taterapi", "org.adde0109.pcf.lib.taterapi")
    var mcVersion = "b1.7.3-1.21.4"
    archiveFileName = "$modId-${version}-mono.jar"
    destinationDirectory = file("./build/libs")
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE

    manifest {
        attributes(
                mapOf(
                        "Specification-Title" to "$modName $mcVersion",
                        "Specification-Version" to version,
                        "Specification-Vendor" to "NeuralNexus",
                        "Implementation-Version" to version,
                        "Implementation-Vendor" to "NeuralNexus",
                        "Implementation-Timestamp" to Instant.now().toString(),
                        "FMLCorePluginContainsFMLMod" to "true",
                        "TweakClass" to "org.spongepowered.asm.launch.MixinTweaker",
                        "MixinConfigs" to "$modId.mixins.json,crossperms.mixins.json"
                )
        )
    }

    from(listOf("README.md", "LICENSE")) {
        into("META-INF")
    }

    val jarTasks = mutableListOf<Task>()

    evaluationDependsOn(":api")
    evaluationDependsOn(":common")
    dependsOn(":api:build")
    dependsOn(":common:build")
    jarTasks.add(rootProject.project(":common").tasks.named<ShadowJar>("shadowJar").get())
    jarTasks.add(rootProject.project(":api").tasks.named<Jar>("jar").get())
    projs.forEach {
        evaluationDependsOn(it)
        dependsOn("$it:build")
        jarTasks.add(rootProject.project(it).tasks.jar.get())
    }

    from(bundleJars(jarTasks))
    from(mergeMixins.map { project.fileTree(it.outputFile) })
}

val customDowngrade = tasks.register<DowngradeJar>("customDowngrade") {
    inputFile.set(shadeAndRelocate.get().archiveFile)
    downgradeTo = JavaVersion.VERSION_1_8
    classpath = sourceSets.main.get().compileClasspath
    archiveClassifier = "downgraded-8"
}

val customShadeDowngradedApi = tasks.register<ShadeJar>("customShadeDowngradedApi") {
    inputFile.set(customDowngrade.get().archiveFile)
    shadePath = {
        it.substringBefore(".")
                .substringBeforeLast("-")
                .replace(Regex("[.;\\[/]"), "-")
                .replace(modId, "dev/neuralnexus/taterlib/lib/jvmdg")
    }
    archiveClassifier = "downgraded-8-shaded"
}

val renameJar = tasks.register<Copy>("renameJar") {
    from(customShadeDowngradedApi.get().archiveFile)
    into(file("${rootProject.projectDir}/build/libs"))
    rename { "$modId-$version.jar" }

    doLast {
        file("${rootProject.projectDir}/build/libs/$modId-$version-mono.jar").delete()
        file("${rootProject.projectDir}/build/libs/$modId-$version-downgraded-8.jar").delete()
        file("${rootProject.projectDir}/build/libs/$modId-$version-downgraded-8-shaded.jar").delete()
    }
}

tasks.assemble.get().dependsOn(renameJar)
//tasks.build.get().dependsOn(renameJar)
