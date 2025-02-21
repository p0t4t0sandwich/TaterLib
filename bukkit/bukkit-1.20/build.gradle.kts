base {
    archivesName = "${projectId}-bukkit-${minecraftVersion}"
}

dependencies {
    compileOnly("org.spigotmc:spigot-api:${minecraftVersion}-${apiVersion}")
    compileOnly("io.papermc.paper:paper-api:${minecraftVersion}-${apiVersion}")
    compileOnly(project(":api"))
    compileOnly(project(":common"))
    compileOnly(project(":loader"))
    compileOnly(project(":bukkit:bukkit-utils"))
    compileOnly(libs.modapi)
}

java.disableAutoTargetJvm()
