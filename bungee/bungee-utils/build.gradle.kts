base {
    archivesName = "${modId}-bungee-utils"
}

dependencies {
    compileOnly("net.md-5:bungeecord-api:${minecraftVersion}-${bungeecordVersion}")
    compileOnly("io.github.waterfallmc:waterfall-api:${minecraftVersion}-${bungeecordVersion}")
    compileOnly(project(":api"))
    compileOnly(project(":common"))
    compileOnly(project(":loader"))
    compileOnly(libs.modapi)
}
