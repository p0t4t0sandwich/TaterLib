base {
    archivesName = "${modId}-bungee-${minecraftVersion}"
}

dependencies {
    compileOnly("net.md-5:bungeecord-api:${minecraftVersion}-${bungeecordVersion}")
    compileOnly(project(":api"))
    compileOnly(project(":common"))
    compileOnly(project(":loader"))
    compileOnly(project(":bungee:bungee-utils"))
    compileOnly(libs.modapi)
}
