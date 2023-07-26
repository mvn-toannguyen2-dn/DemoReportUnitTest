apply(plugin = "jacoco")

tasks.register<JacocoReport>("jacocoTestReport") {
    group = "Reporting"
    description = "Generate Jacoco coverage reports"
    dependsOn("test")

    reports {
        xml.required.set(true)
        html.required.set(true)
    }

    val include = listOf("**/*ViewModel.*", "**/*DataSource.**")
    val exclude = listOf("**/*Dummy*.*")
    val mainSrc = "${project.projectDir}/src/main/java"

    val debugTree = fileTree(
        "dir" to "${buildDir}/intermediates/classes/debug",
        "includes" to include,
        "excludes" to exclude
    )
    val kotlinDebugTree = fileTree(
        "dir" to "${buildDir}/tmp/kotlin-classes/debug",
        "includes" to include,
        "excludes" to exclude
    )

    sourceDirectories.setFrom(files(mainSrc))
    classDirectories.setFrom(files(debugTree, kotlinDebugTree))
    executionData.setFrom(files("${buildDir}/jacoco/test.exec"))
}

tasks.named("check") {
    finalizedBy("jacocoTestReport")
}

tasks.withType<Test> {
    configure<JacocoTaskExtension> {
        setIncludeNoLocationClasses(true)
        excludes = listOf("jdk.internal.*")
    }
}
