import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    alias(libs.plugins.shadow)
}

base {
    archivesName = "${projectId}-common"
}

dependencies {
    // Configurate
    compileOnly("org.spongepowered:configurate-hocon:4.2.0-SNAPSHOT")
    compileOnly("io.leangen.geantyref:geantyref:1.3.15")

    // bStats
    compileOnly("org.bstats:bstats-base:3.0.2")

    // MCLogs
    implementation("gs.mclo:api:3.0.1")

    // Gson
    implementation("com.google.code.gson:gson:2.10.1")

    // Mixin
    compileOnly(libs.mixin)
    compileOnly(libs.asm.tree)

    // Mojang AuthLib
    compileOnly("com.mojang:authlib:1.5.25")

    // Tooling
    compileOnly(project(":api"))
    implementation(variantOf(libs.modapi.metadata) {
        classifier("downgraded-8-shaded")
    })
    implementation(libs.modapi.muxins)
    compileOnly(project(":modapi:entrypoint-spoof"))
    implementation(project(":loader"))
}

java.disableAutoTargetJvm()

tasks.named<ShadowJar>("shadowJar") {
    dependencies {
        include(dependency("${group}:${projectId}:${version}"))
        exclude("META-INF", "META-INF/**")
        exclude("LICENSE")
        exclude("INFO_BIN", "INFO_SRC")

        // Tooling
//        include(project(":api"))
//        include(project(":loader"))
        include(dependency("dev.neuralnexus.modapi:metadata"))
        include(dependency("dev.neuralnexus.modapi:muxins"))

        // MCLogs
        include(dependency("gs.mclo:api:3.0.1"))

        // Gson
        include(dependency("com.google.code.gson:gson:2.10.1"))

        // Guava
        include(dependency("com.google.guava:guava:33.0.0-jre"))
    }
    // Configurate
    relocate("org.spongepowered.configurate", "dev.neuralnexus.taterlib.lib.configurate")
    relocate("com.typesafe.config", "dev.neuralnexus.taterlib.lib.typesafe.config")
    relocate("io.leangen.geantyref", "dev.neuralnexus.taterlib.lib.geantyref")

    // bStats
    relocate("org.bstats", "dev.neuralnexus.taterlib.lib.bstats")

    // MCLogs
    relocate("gs.mclo", "dev.neuralnexus.taterlib.lib.mclogs")

    // Gson
    relocate("com.google.gson", "dev.neuralnexus.taterlib.lib.gson")

    minimize {
        exclude(dependency("dev.neuralnexus.modapi:metadata"))
        exclude(dependency("dev.neuralnexus.modapi:muxins"))
    }

    archiveClassifier.set("full")
}

tasks.assemble {
    dependsOn(tasks.shadowJar)
}
