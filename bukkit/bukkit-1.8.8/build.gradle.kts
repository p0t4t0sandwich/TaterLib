base {
    archivesName = "${projectId}-bukkit-${minecraftVersion}"
}

dependencies {
    compileOnly("org.spigotmc:spigot-api:${minecraftVersion}-${apiVersion}")
    compileOnly("org.github.paperspigot:paperspigot-api:${minecraftVersion}-${apiVersion}")
    compileOnly(project(":api"))
    compileOnly(project(":common"))
    compileOnly(project(":tooling:loader"))
    compileOnly(project(":bukkit:bukkit-utils"))
}
