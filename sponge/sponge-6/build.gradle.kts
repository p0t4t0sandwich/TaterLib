base {
    archivesName = "${projectId}-sponge-6"
}

dependencies {
    compileOnly("org.spongepowered:spongeapi:${apiVersion}")
    compileOnly(project(":api"))
    compileOnly(project(":common"))
    compileOnly(project(":loader"))
    compileOnly(project(":sponge:sponge-utils-legacy"))
    compileOnly(libs.modapi)
}
