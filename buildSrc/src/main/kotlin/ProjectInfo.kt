import org.gradle.api.Project

object ProjectInfo

val Project.author: String get() = properties["author"].toString()
val Project.modName: String get() = properties["mod_name"].toString()
val Project.modId: String get() = properties["mod_id"].toString()
val Project.description: String get() = properties["description"].toString()
val Project.license: String get() = properties["license"].toString()

val Project.homepageUrl: String get() = properties["homepage_url"].toString()
val Project.issueUrl: String get() = properties["issue_url"].toString()
val Project.sourceUrl: String get() = properties["source_url"].toString()

val Project.minecraftVersion: String get() = properties["minecraft_version"].toString()
val Project.parchmentMinecraft: String get() = properties["parchment_minecraft"].toString()
val Project.parchmentVersion: String get() = properties["parchment_version"].toString()

val Project.mcpChannel: String get() = properties["mcp_channel"].toString()
val Project.mcpVersion: String get() = properties["mcp_version"].toString()

val Project.bukkitVersion: String get() = properties["bukkit_version"].toString()
val Project.bungeecordVersion: String get() = properties["bungeecord_version"].toString()
val Project.fabricVersion: String get() = properties["fabric_version"].toString()
val Project.fabricLoaderVersion: String get() = properties["fabric_loader_version"].toString()
val Project.forgeVersion: String get() = properties["forge_version"].toString()
val Project.neoForgeVersion: String get() = properties["neoforge_version"].toString()
val Project.paperVersion: String get() = properties["paper_version"].toString()
val Project.spigotVersion: String get() = properties["spigot_version"].toString()
val Project.spongeVersion: String get() = properties["sponge_version"].toString()
val Project.velocityVersion: String get() = properties["velocity_version"].toString()

val Project.javaVersion: String get() = properties["java_version"].toString()
