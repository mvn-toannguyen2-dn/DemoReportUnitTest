apply(plugin = "jacoco")

tasks.register("jacocoTestReport", JacocoReport::class) {
    group = "Reporting"
    description = "Generate Jacoco coverage reports"

    reports {
        xml.required.set(true)
        html.required.set(true)
    }

    val include = listOf("**/*ViewModel.*", "**/*DataSource.**")
    val exclude = listOf("**/*Dummy*.*")
    val mainSrc = "${project.projectDir}/src/main/java"

    val debugTree = fileTree(
        "dir" to "${buildDir}/intermediates/classes/atDebug",
        "includes" to include,
        "excludes" to exclude
    )
    val kotlinDebugTree = fileTree(
        "dir" to "${buildDir}/tmp/kotlin-classes/atDebug",
        "includes" to include,
        "excludes" to exclude
    )

    sourceDirectories.setFrom(files(mainSrc))
    classDirectories.setFrom(files(debugTree, kotlinDebugTree))
    executionData.setFrom(files("${buildDir}/jacoco/testAtDebugUnitTest.exec"))
}
