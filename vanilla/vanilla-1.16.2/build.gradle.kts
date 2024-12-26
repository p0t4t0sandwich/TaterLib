plugins {
    alias(libs.plugins.vanillagradle)
}

base {
    archivesName = "${projectId}-vanilla-${minecraftVersion}"
}

minecraft {
    version(minecraftVersion)
}

dependencies {
    compileOnly(libs.brigadier)
    compileOnly(libs.mixin)
    compileOnly(project(":api"))
    compileOnly(project(":common"))
    compileOnly(project(":loader"))
    compileOnly(libs.modapi.metadata)
    compileOnly(libs.modapi.muxins)
    compileOnly(project(":vanilla:vanilla-1.16"))
}
