base {
    archivesName = "${projectId}-forge-utils-modern"
}

dependencies {
    compileOnly(project(":api"))
    compileOnly(project(":common"))
    compileOnly(project(":tooling:loader"))
    compileOnly(project(":modapi:entrypoint-spoof"))
}
