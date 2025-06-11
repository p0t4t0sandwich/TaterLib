base {
    archivesName = "${modId}-velocity-3"
}

java.toolchain.languageVersion = JavaLanguageVersion.of(javaVersion)
java.sourceCompatibility = JavaVersion.toVersion(javaVersion)
java.targetCompatibility = JavaVersion.toVersion(javaVersion)

dependencies {
    compileOnly("com.velocitypowered:velocity-api:${velocityVersion}")
    annotationProcessor("com.velocitypowered:velocity-api:${velocityVersion}")
    compileOnly(project(":api"))
    compileOnly(project(":common"))
    compileOnly(project(":loader"))
    compileOnly(variantOf(libs.modapi) {
        classifier("downgraded-8")
    })
}
