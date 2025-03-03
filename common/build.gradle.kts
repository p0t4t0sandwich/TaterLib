import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    alias(libs.plugins.shadow)
}

base {
    archivesName = "${projectId}-common"
}

dependencies {
    // Configurate
    implementation("org.spongepowered:configurate-hocon:4.2.0")
    implementation("io.leangen.geantyref:geantyref:1.3.15")

    // bStats
    implementation("org.bstats:bstats-base:3.0.2")
    implementation("org.bstats:bstats-bukkit:3.0.2")
    implementation("org.bstats:bstats-bungeecord:3.0.2")
    implementation("org.bstats:bstats-sponge:3.0.2")
    implementation("org.bstats:bstats-velocity:3.0.2")

    // Gson
    implementation("com.google.code.gson:gson:2.10.1")

    // MySQL
    implementation("com.zaxxer:HikariCP:4.0.3")
    implementation("com.mysql:mysql-connector-j:8.3.0")

    // MCLogs
    implementation("gs.mclo:api:3.0.1")

    // Mixin
    compileOnly(libs.mixin)
    compileOnly(libs.asm.tree)

    // Mojang AuthLib
    compileOnly("com.mojang:authlib:1.5.25")

    // Tooling
    implementation(project(":api"))
    implementation(project(":loader"))
    implementation(variantOf(libs.modapi) {
        classifier("downgraded-8-shaded")
    })
    compileOnly(project(":modapi:entrypoint-spoof"))
}

java.disableAutoTargetJvm()

tasks.shadowJar {
    dependencies {
        include(dependency("${group}:${projectId}:${version}"))
        exclude("META-INF", "META-INF/**")
        exclude("LICENSE")
        exclude("INFO_BIN", "INFO_SRC")

        // Tooling
        include(project(":api"))
        include(project(":loader"))
        include(dependency("dev.neuralnexus.modapi:crossperms"))
        include(dependency("dev.neuralnexus.modapi:metadata"))
        include(dependency("dev.neuralnexus.modapi:muxins"))

        // Config
        include(dependency("org.spongepowered:configurate-core:4.2.0"))
        include(dependency("org.spongepowered:configurate-hocon:4.2.0"))
        include(dependency("com.typesafe:config:1.4.1"))
        include(dependency("io.leangen.geantyref:geantyref:1.3.16"))

        // bStats
        include(dependency("org.bstats:bstats-base:3.0.2"))
        include(dependency("org.bstats:bstats-bukkit:3.0.2"))
        include(dependency("org.bstats:bstats-bungeecord:3.0.2"))
        include(dependency("org.bstats:bstats-sponge:3.0.2"))
        include(dependency("org.bstats:bstats-velocity:3.0.2"))

        // MySQL
        include(dependency("com.zaxxer:HikariCP:4.0.3"))
        include(dependency("com.mysql:mysql-connector-j:8.3.0"))

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
    relocate("org.bstats", "dev.neuralnexus.taterlib.lib.bstats") {
        exclude("org.bstats.sponge.*")
    }

    // MCLogs
    relocate("gs.mclo", "dev.neuralnexus.taterlib.lib.mclogs")

    // MySQL
    relocate("com.zaxxer.hikari", "dev.neuralnexus.taterlib.lib.hikari")
    relocate("com.mysql", "dev.neuralnexus.taterlib.lib.mysql")

    // Gson
    relocate("com.google.gson", "dev.neuralnexus.taterlib.lib.gson")

    minimize {
        exclude(project(":api"))
        exclude(project(":loader"))
        exclude(dependency("dev.neuralnexus.modapi:crossperms"))
        exclude(dependency("dev.neuralnexus.modapi:metadata"))
        exclude(dependency("dev.neuralnexus.modapi:muxins"))
    }

    archiveClassifier.set("full")
}

tasks.assemble {
    dependsOn(tasks.shadowJar)
}
