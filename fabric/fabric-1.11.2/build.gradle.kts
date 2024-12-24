plugins {
    alias(libs.plugins.loom)
}

base {
    archivesName = "${projectId}-fabric-${minecraftVersion}"
}

loom.mixin.useLegacyMixinAp = false

dependencies {
    minecraft("com.mojang:minecraft:${minecraftVersion}")
    mappings("net.legacyfabric:yarn:${mappingsVersion}:v2")
    modImplementation("net.fabricmc:fabric-loader:${loaderVersion}")
    modImplementation("net.legacyfabric.legacy-fabric-api:legacy-fabric-api:${apiVersion}")
    compileOnly(project(":api"))
    compileOnly(project(":common"))
    compileOnly(project(":tooling:loader"))
    compileOnly(project(":modapi:conditional-mixins"))
}
