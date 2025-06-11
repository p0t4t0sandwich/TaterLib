base {
    archivesName = "${modId}-bukkit-${minecraftVersion}"
}

dependencies {
    compileOnly("org.spigotmc:spigot-api:${minecraftVersion}-${spigotVersion}")
    compileOnly("org.github.paperspigot:paperspigot-api:${minecraftVersion}-${spigotVersion}")
    compileOnly(project(":api"))
    compileOnly(project(":common"))
    compileOnly(project(":loader"))
    compileOnly(project(":bukkit:bukkit-utils"))
    compileOnly(libs.modapi)
}
