plugins {
    id("java-library")
    id("maven-publish")
}

base {
    archivesName = "taterapi"
}

dependencies {
    // Configurate
    api("org.spongepowered:configurate-hocon:4.2.0-SNAPSHOT")
    api("io.leangen.geantyref:geantyref:1.3.16")

    // bStats
    implementation("org.bstats:bstats-base:3.0.2")
    implementation("org.bstats:bstats-bukkit:3.0.2")
    implementation("org.bstats:bstats-bungeecord:3.0.2")
    implementation("org.bstats:bstats-sponge:3.0.2")
    implementation("org.bstats:bstats-velocity:3.0.2")

    // Gson
    api("com.google.code.gson:gson:2.10.1")

    // MySQL
    implementation("com.zaxxer:HikariCP:4.0.3")
    implementation("com.mysql:mysql-connector-j:8.3.0")

    // Brigadier
    api(libs.brigadier)

    // Mixin
    implementation(libs.mixin)
    implementation(libs.asm.tree)

    // Cross API dependencies
    implementation("net.luckperms:api:5.4")
    implementation("me.lucko:spark-api:0.1-SNAPSHOT")

    // Tooling
    compileOnly(project(":modapi:entrypoint-spoof"))
    api(variantOf(libs.modapi) {
        classifier("downgraded-8")
    })
}

java.disableAutoTargetJvm()
java {
    withSourcesJar()
    withJavadocJar()
    toolchain.languageVersion = JavaLanguageVersion.of(javaVersion)
    sourceCompatibility = JavaVersion.toVersion(javaVersion)
    targetCompatibility = JavaVersion.toVersion(javaVersion)
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
            artifactId = "taterapi"
            from(components["java"])
        }
    }
}
