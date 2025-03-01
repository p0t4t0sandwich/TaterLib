import org.gradle.api.Project

object Tater

val Project.projectName: String get() = properties["project_name"].toString()
val Project.projectId: String get() = properties["project_id"].toString()
val Project.projectDescription: String get() = properties["project_description"].toString()
val Project.projectLicense: String get() = properties["license"].toString()

val Project.homepageUrl: String get() = properties["homepage_url"].toString()
val Project.issueUrl: String get() = properties["issue_url"].toString()
val Project.sourceUrl: String get() = properties["source_url"].toString()

val Project.apiVersion: String get() = properties["api_version"].toString()
val Project.fabricVersion: String get() = properties["fabric_version"].toString()
val Project.fabricLoaderVersion: String get() = properties["fabric_loader_version"].toString()
val Project.forgeVersion: String get() = properties["forge_version"].toString()
val Project.neoForgeVersion: String get() = properties["neoforge_version"].toString()
val Project.loaderVersion: String get() = properties["loader_version"].toString()
val Project.minecraftVersion: String get() = properties["minecraft_version"].toString()

val Project.mappingsChannel: String get() = properties["mapping_channel"].toString()
val Project.mappingsVersion: String get() = properties["mapping_version"].toString()

val Project.javaVersion: String get() = properties["java_version"].toString()
