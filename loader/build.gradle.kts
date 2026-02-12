base {
    archivesName = "${modId}-loader"
}

dependencies {
    compileOnly(libs.mixin)
    compileOnly(libs.asm.tree)
    compileOnly(project(":api"))
    compileOnly(project(":modapi:entrypoint-spoof"))
}

tasks.withType<ProcessResources>().configureEach {
    filesMatching(
        listOf(
            "${modId}.mixins.json",
            "plugin.yml",
            "bungee.yml",
            "ignite.mod.json",
            "fabric.mod.json",
            "mcmod.info",
            "velocity-plugin.json",
            "META-INF/mods.toml",
            "META-INF/neoforge.mods.toml",
            "META-INF/sponge_plugins.json"
        )
    ) {
        expand(project.properties)
    }
}
