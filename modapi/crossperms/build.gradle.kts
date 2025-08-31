import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import xyz.wagyourtail.unimined.api.minecraft.task.RemapJarTask
import java.time.Instant

plugins {
    id("java-library")
    id("maven-publish")
    id(libs.plugins.unimined.get().pluginId)
    alias(libs.plugins.jvmdowngrader)
}

var groupId: String = findProperty("group").toString()

base {
    archivesName = modId
    version = properties["modlib_version"].toString()
}

java {
    withSourcesJar()
    withJavadocJar()
    toolchain.languageVersion = JavaLanguageVersion.of(javaVersion)
    sourceCompatibility = JavaVersion.toVersion(javaVersion)
    targetCompatibility = JavaVersion.toVersion(javaVersion)
}

val (main, fabric, forge, _, _) = getPlatforms("fabric", "forge")

val forge1171: SourceSet by sourceSets.creating

val common: SourceSet by sourceSets.creating
val commonCompileOnly: Configuration by configurations.getting
val forge1171CompileOnly: Configuration by configurations.getting

listOf(fabric.compileOnly, forge.compileOnly, forge1171CompileOnly).forEach {
    it.extendsFrom(commonCompileOnly)
}

unimined.footgunChecks = false

unimined.minecraft(common) {
    combineWith(sourceSets.main.get())
    if (sourceSet == common) {
        defaultRemapJar = false
    }
    if (sourceSet == common || sourceSet == fabric.sourceSet || sourceSet == forge.sourceSet) {
        version(minecraftVersion)
        mappings {
            parchment(parchmentMinecraft, parchmentVersion)
            mojmap()
            devFallbackNamespace("official")
        }
    }
}

unimined.minecraft(fabric.sourceSet) {
    combineWith(common)
    fabric {
        loader(fabricLoaderVersion)
    }
}

unimined.minecraft(forge.sourceSet) {
    combineWith(common)
    minecraftForge {
        loader(forgeVersion)
    }
}

unimined.minecraft(forge1171) {
    combineWith(common)
    version("1.17.1")
    mappings {
        parchment("1.17.1", "2021.12.12")
        mojmap()
        devFallbackNamespace("official")
    }
    minecraftForge {
        loader("37.1.1")
    }
}

tasks.register<ShadowJar>("relocateFabricJar") {
    dependsOn("remapFabricJar")
    from(jarToFiles("remapFabricJar"))
    archiveClassifier.set("fabric-relocated")
    // relocate("$groupId.$modId.mixin.vanilla", "$groupId.$modId.mixin.y_intmdry")
}

tasks.register<ShadowJar>("relocateForgeJar") {
    dependsOn("remapForgeJar")
    from(jarToFiles("remapForgeJar"))
    archiveClassifier.set("forge-relocated")
    // relocate("$groupId.$modId.mixin.vanilla", "$groupId.$modId.mixin.l_searge")
}

tasks.register<ShadowJar>("relocateForge1171Jar") {
    dependsOn("remapForge1171Jar")
    from(jarToFiles("remapForge1171Jar"))
    archiveClassifier.set("forge1171-relocated")
    // relocate("$groupId.$modId.mixin.vanilla", "$groupId.$modId.mixin.searge")
}

dependencies {
    compileOnly(libs.mixin)
    compileOnly(libs.asm.tree)

    // CrossPerms
    compileOnly("com.destroystokyo.paper:paper-api:1.15.2-R0.1-SNAPSHOT")
    compileOnly("net.md-5:bungeecord-api:1.21-R0.1-SNAPSHOT")
    compileOnly("net.fabricmc:fabric-loader:0.16.9")
    compileOnly("me.lucko:fabric-permissions-api:0.2-SNAPSHOT")
    compileOnly("net.legacyfabric.legacy-fabric-api:legacy-fabric-api:1.9.0+1.12.2")
//    compileOnly("org.spongepowered:spongeapi:8.1.0")
    compileOnly("com.velocitypowered:velocity-api:3.4.0-SNAPSHOT")

    compileOnly("com.mojang:authlib:3.13.56")

    // CrossPerms Integrations
    compileOnly("net.luckperms:api:5.4")
    compileOnly("com.github.MilkBowl:VaultAPI:1.7")
    compileOnly("ca.stellardrift.permissionsex:permissionsex-core:2.0-SNAPSHOT")
    compileOnly("ca.stellardrift.permissionsex:permissionsex-bukkit:2.0-SNAPSHOT")
    compileOnly("ca.stellardrift.permissionsex:permissionsex-bungee:2.0-SNAPSHOT")
    compileOnly("ca.stellardrift.permissionsex:permissionsex-fabric:2.0-SNAPSHOT")
    compileOnly("ca.stellardrift.permissionsex:permissionsex-sponge:2.0-SNAPSHOT")
    compileOnly("ca.stellardrift.permissionsex:permissionsex-velocity:2.0-SNAPSHOT")

    compileOnly(project(":modapi:entrypoint-spoof"))
    compileOnly(project(":modapi:base"))
    compileOnly(project(":modapi:metadata"))

    commonCompileOnly(libs.mixin)
    commonCompileOnly(project(":modapi:core"))
    commonCompileOnly(project(":modapi:base"))
    commonCompileOnly(project(":modapi:metadata"))
    commonCompileOnly(project(":modapi:muxins"))
    forge.compileOnly(libs.mixin)
}

tasks.withType<RemapJarTask> {
    mixinRemap {
        enableBaseMixin()
        disableRefmap()
    }
}

tasks.withType<ProcessResources> {
    filesMatching(listOf(
        "fabric.mod.json",
        "ignite.mod.json",
        "pack.mcmeta",
        "META-INF/mods.toml",
        "META-INF/neoforge.mods.toml",
        "META-INF/sponge_plugins.json"
    )) {
        expand(project.properties)
    }
}

tasks.jar {
    dependsOn(
        "relocateFabricJar",
        "relocateForgeJar",
        "relocateForge1171Jar"
    )
    from(
        common.output,
        jarToFiles("relocateFabricJar"),
        jarToFiles("relocateForgeJar"),
        jarToFiles("relocateForge1171Jar")
    )
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    manifest {
        attributes(
            mapOf(
                "Specification-Title" to "TaterLib CrossPerms",
                "Specification-Version" to version,
                "Specification-Vendor" to "NeualNexus",
                "Implementation-Version" to version,
                "Implementation-Vendor" to "NeualNexus",
                "Implementation-Timestamp" to Instant.now().toString(),
                "MixinConfigs" to "$modId.mixins.json"
            )
        )
    }
}

tasks.downgradeJar {
    downgradeTo = JavaVersion.VERSION_1_8
    archiveClassifier.set("downgraded-8")
}

tasks.shadeDowngradedApi {
    downgradeTo = JavaVersion.VERSION_1_8
    shadePath = {
        it.substringBefore(".")
            .substringBeforeLast("-")
            .replace(Regex("[.;\\[/]"), "-")
            .replace(modId, "${group?.replace('.', '/')}/$modId/jvmdg")
    }
    archiveClassifier.set("downgraded-8-shaded")
}

tasks.withType<GenerateModuleMetadata> {
    enabled = false
}

tasks.downgradeJar.get().dependsOn(tasks.spotlessApply)
tasks.assemble {
    dependsOn(tasks.downgradeJar)
    dependsOn(tasks.shadeDowngradedApi)
}

publishing {
    repositories {
        mavenLocal()
        maven("https://maven.neuralnexus.dev/releases") {
            name = "NeuralNexusReleases"
            credentials {
                username = project.findProperty("neuralNexusUsername") as? String ?: System.getenv("NEURALNEXUS_USERNAME") ?: ""
                password = project.findProperty("neuralNexusPassword") as? String ?: System.getenv("NEURALNEXUS_PASSWORD") ?: ""
            }
        }
        maven("https://maven.neuralnexus.dev/snapshots") {
            name = "NeuralNexusSnapshots"
            credentials {
                username = project.findProperty("neuralNexusUsername") as? String ?: System.getenv("NEURALNEXUS_USERNAME") ?: ""
                password = project.findProperty("neuralNexusPassword") as? String ?: System.getenv("NEURALNEXUS_PASSWORD") ?: ""
            }
        }
    }

    publications {
        create<MavenPublication>("maven") {
            from(components["java"])
            artifact(tasks.downgradeJar.get())
            artifact(tasks.shadeDowngradedApi.get())
        }
    }
}
