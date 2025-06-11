base {
    archivesName = "${modId}-bukkit-${minecraftVersion}"
}

dependencies {
    compileOnly("org.bukkit:craftbukkit:${minecraftVersion}-${bukkitVersion}")
    compileOnly("org.github.paperspigot:paperspigot-api:${minecraftVersion}-${project.properties["paperspigot_version"]}")
    compileOnly(project(":api"))
    compileOnly(project(":common"))
    compileOnly(project(":loader"))
    compileOnly(project(":bukkit:bukkit-utils"))
    compileOnly(libs.modapi)
}
