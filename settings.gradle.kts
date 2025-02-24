import groovy.json.JsonSlurper

pluginManagement {
    repositories {
        maven {
            name = "Fabric"
            url = uri("https://maven.fabricmc.net/")
        }
        if (System.getenv("CI") != "true" /* not run in github actions */ ) {
            // If you're not from China, please remove this, it will slow down your downloads
            maven {
                name = "Tencent"
                url = uri("https://mirrors.cloud.tencent.com/nexus/repository/maven-public/")
            }
            maven {
                name = "Ali"
                url = uri("https://maven.aliyun.com/repository/gradle-plugin")
            }
        }
        maven {
            name = "Jitpack"
            url = uri("https://jitpack.io")
        }
        mavenCentral()
        gradlePluginPortal()
    }
    resolutionStrategy {
        eachPlugin {
            when (requested.id.id) {
                "com.replaymod.preprocess" -> {
                    useModule("com.github.Fallen-Breath:preprocessor:${requested.version}")
                }
            }
        }
    }
}

@Suppress("UNCHECKED_CAST")
val settings = JsonSlurper().parseText(file("settings.json").readText()) as Map<String, List<String>>
settings["versions"]?.forEach { version ->
    include(":$version")

    val project = project(":$version")
    project.projectDir = File("versions/$version")
    project.buildFileName = "../../common.gradle.kts"
}