base {
    archivesName = "${projectId}-bukkit-${minecraftVersion}"
}

repositories {
    // NeuralNexus Private
    maven("https://maven.neuralnexus.dev/private") {
        name = "NeuralNexusPrivate"
        credentials {
            username = (project.findProperty("neuralNexusUsername") ?: System.getenv("NEURALNEXUS_USERNAME")).toString()
            password = (project.findProperty("neuralNexusPassword") ?: System.getenv("NEURALNEXUS_PASSWORD")).toString()
        }
    }
}

dependencies {
    compileOnly("org.bukkit:craftbukkit:${minecraftVersion}-${apiVersion}")
    compileOnly(project(":api"))
    compileOnly(project(":common"))
    compileOnly(project(":loader"))
    compileOnly(project(":bukkit:bukkit-utils"))
    compileOnly(libs.modapi.metadata)
}
