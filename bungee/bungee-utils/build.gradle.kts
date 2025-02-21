base {
    archivesName = "${projectId}-bungee-utils"
}

dependencies {
    compileOnly("net.md-5:bungeecord-api:${minecraftVersion}-${apiVersion}")
    compileOnly("io.github.waterfallmc:waterfall-api:${minecraftVersion}-${apiVersion}")
    compileOnly(project(":api"))
    compileOnly(project(":common"))
    compileOnly(project(":loader"))
    compileOnly(libs.modapi)
}
