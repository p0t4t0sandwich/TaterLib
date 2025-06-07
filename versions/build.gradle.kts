import xyz.wagyourtail.unimined.api.minecraft.task.RemapJarTask

subprojects {
    base {
        archivesName = "${projectId}-${minecraftVersion}"
    }

    java.toolchain.languageVersion = JavaLanguageVersion.of(javaVersion)
    java.sourceCompatibility = JavaVersion.toVersion(javaVersion)
    java.targetCompatibility = JavaVersion.toVersion(javaVersion)

    tasks.withType<RemapJarTask>().configureEach {
        mixinRemap {
            enableBaseMixin()
            disableRefmap()
        }
    }

    var mainCompileOnly = configurations.maybeCreate("mainCompileOnly")

    dependencies {
        compileOnly(rootProject.libs.mixin)
        mainCompileOnly(variantOf(rootProject.libs.modapi) { classifier("downgraded-8") })
        listOf(":api", ":common").forEach {
            mainCompileOnly(project(it))
        }
    }
}
