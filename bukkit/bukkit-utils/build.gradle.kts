base {
    archivesName = "${projectId}-bukkit-utils"
}

dependencies {
    compileOnly("org.spigotmc:spigot-api:${minecraftVersion}-${apiVersion}")
    compileOnly("io.papermc.paper:paper-api:${minecraftVersion}-${apiVersion}")
    compileOnly(project(":api"))
    compileOnly(project(":common"))
    compileOnly(project(":tooling:loader"))
}

java.disableAutoTargetJvm()
