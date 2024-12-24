base {
    archivesName = "${projectId}-velocity-3"
}

java.disableAutoTargetJvm()

dependencies {
    compileOnly("com.velocitypowered:velocity-api:${apiVersion}")
    annotationProcessor("com.velocitypowered:velocity-api:${apiVersion}")
    compileOnly(project(":api"))
    compileOnly(project(":common"))
    compileOnly(project(":tooling:loader"))
}
