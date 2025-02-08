import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    alias(libs.plugins.shadow)
}

base {
    archivesName = "taterapi"
}

dependencies {
    // Configurate
    implementation("org.spongepowered:configurate-hocon:4.2.0-SNAPSHOT")
    implementation("io.leangen.geantyref:geantyref:1.3.16")

    // bStats
    implementation("org.bstats:bstats-base:3.0.2")
    implementation("org.bstats:bstats-bukkit:3.0.2")
    implementation("org.bstats:bstats-bungeecord:3.0.2")
    implementation("org.bstats:bstats-sponge:3.0.2")
    implementation("org.bstats:bstats-velocity:3.0.2")

    // MySQL
    implementation("com.zaxxer:HikariCP:4.0.3")
    implementation("com.mysql:mysql-connector-j:8.3.0")

    // Brigadier
    compileOnly(libs.brigadier)

    // Mixin
    compileOnly(libs.mixin)
    compileOnly(libs.asm.tree)

    // Cross API dependencies
    compileOnly("net.luckperms:api:5.4")
    compileOnly("me.lucko:spark-api:0.1-SNAPSHOT")

    // Tooling
    compileOnly(project(":modapi:entrypoint-spoof"))
    compileOnly(variantOf(libs.modapi.crossperms) {
        classifier("downgraded-8")
    })
    compileOnly(variantOf(libs.modapi.metadata) {
        classifier("downgraded-8")
    })
}

java.disableAutoTargetJvm()

tasks.named<ShadowJar>("shadowJar") {
    dependencies {
        include(dependency("${group}:${projectId}:${version}"))
        exclude("META-INF", "META-INF/**")
        exclude("LICENSE")
        exclude("INFO_BIN", "INFO_SRC")

        // Config
        include(dependency("org.spongepowered:configurate-core:4.2.0-SNAPSHOT"))
        include(dependency("org.spongepowered:configurate-hocon:4.2.0-SNAPSHOT"))
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
    }

    minimize {
        exclude(dependency("org.spongepowered:configurate-core:4.2.0-SNAPSHOT"))
        exclude(dependency("org.spongepowered:configurate-hocon:4.2.0-SNAPSHOT"))
        exclude(dependency("org.bstats:bstats-base:3.0.2"))
    }

    // Configurate
    relocate("org.spongepowered.configurate", "dev.neuralnexus.taterlib.lib.configurate")
    relocate("com.typesafe.config", "dev.neuralnexus.taterlib.lib.typesafe.config")
    relocate("io.leangen.geantyref", "dev.neuralnexus.taterlib.lib.geantyref")

    // bStats
    relocate("org.bstats", "dev.neuralnexus.taterlib.lib.bstats") {
        exclude("org.bstats.sponge.*")
    }

    // MySQL
    relocate("com.zaxxer.hikari", "dev.neuralnexus.taterlib.lib.hikari")
    relocate("com.mysql", "dev.neuralnexus.taterlib.lib.mysql")

    archiveClassifier.set("full")
}

tasks.assemble {
    dependsOn(tasks.shadowJar)
}
