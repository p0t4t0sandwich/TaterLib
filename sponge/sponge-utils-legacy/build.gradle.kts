base {
    archivesName = "${projectId}-sponge-utils-legacy"
}

dependencies {
    compileOnly("org.spongepowered:spongeapi:${apiVersion}")
    compileOnly(project(":api"))
    compileOnly(project(":common"))
    compileOnly(project(":loader"))
    compileOnly(libs.modapi.metadata)
}
