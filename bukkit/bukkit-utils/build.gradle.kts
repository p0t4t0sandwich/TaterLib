base {
    archivesName = "${modId}-bukkit-utils"
}

dependencies {
    compileOnly("org.spigotmc:spigot-api:${minecraftVersion}-${spigotVersion}")
    compileOnly("io.papermc.paper:paper-api:${minecraftVersion}-${spigotVersion}")
    compileOnly(project(":api"))
    compileOnly(project(":common"))
    compileOnly(project(":loader"))
    compileOnly(libs.modapi)
}

java.disableAutoTargetJvm()
