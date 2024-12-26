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

//    Set<String> apiModules = [
//            'legacy-fabric-api-base',
//            'legacy-fabric-command-api-v2',
//            'legacy-fabric-lifecycle-events-v1',
//            'legacy-fabric-networking-api-v1',
//            'legacy-fabric-permissions-api-v1'
//    ]
//    apiModules.forEach {
//        modImplementation(legacy.apiModule(it, apiVersion))
//    }
    modImplementation("net.legacyfabric.legacy-fabric-api:legacy-fabric-api:${apiVersion}")

    compileOnly(project(":api"))
    compileOnly(project(":common"))
    compileOnly(project(":loader"))
    compileOnly(project(":modapi:conditional-mixins"))
}
