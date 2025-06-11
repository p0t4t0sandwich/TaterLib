base {
    archivesName = "${modId}-loader"
}

dependencies {
    compileOnly(libs.mixin)
    compileOnly(libs.asm.tree)
    compileOnly(project(":api"))
    compileOnly(project(":modapi:entrypoint-spoof"))
    compileOnly(variantOf(libs.modapi) {
        classifier("downgraded-8")
    })
}

tasks.withType<ProcessResources>().configureEach {
    filesMatching(
        listOf(
            "plugin.yml",
            "bungee.yml",
            "fabric.mod.json",
            "${modId}.mixins.json",
            "META-INF/mods.toml",
            "META-INF/neoforge.mods.toml",
            "mcmod.info",
            "META-INF/sponge_plugins.json",
            "velocity-plugin.json"
        )
    ) {
        expand(project.properties)
    }
}
