import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    alias(libs.plugins.shadow)
}

//base {
//    archivesName = "${projectId}-common-${minecraftVersion}"
//}

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

    // Guava
    implementation("com.google.guava:guava:33.0.0-jre")

    // Mixin
    compileOnly(libs.mixin)
    compileOnly(libs.asm.tree)

    // Mojang AuthLib
    compileOnly("com.mojang:authlib:1.5.25")

    // Tooling
    compileOnly(project(":api"))
    compileOnly(project(":modapi:muxins"))
    compileOnly(project(":modapi:entrypoint-spoof"))
    compileOnly(project(":loader"))
}

java.disableAutoTargetJvm()

tasks.named<Jar>("jar") {
    archiveFileName = "${projectId}-common-${version}.jar"
}

tasks.named<ShadowJar>("shadowJar") {
    dependencies {
        include(dependency("${group}:${projectId}:${version}"))
        exclude("META-INF", "META-INF/**")
        exclude("LICENSE")
        exclude("INFO_BIN", "INFO_SRC")

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

    // Guava
    relocate("com.google.common", "dev.neuralnexus.taterlib.lib.guava")
    relocate("com.google.thirdparty", "dev.neuralnexus.taterlib.lib.google.thirdparty")

    minimize()

    archiveFileName = "${projectId}-common-full-${version}.jar"
}

artifacts {
    archives(tasks.shadowJar)
}
