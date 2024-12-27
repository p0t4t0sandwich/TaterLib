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
    compileOnly(variantOf(libs.modapi.metadata) {
        classifier("downgraded-8")
    })
    compileOnly(libs.modapi.muxins)
}