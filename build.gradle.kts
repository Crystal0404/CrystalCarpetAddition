plugins {
    id("maven-publish")
    id("com.github.hierynomus.license") version "0.16.1" apply false
    id("fabric-loom") version "1.10-SNAPSHOT" apply false

    // https://github.com/ReplayMod/preprocessor
    // https://github.com/Fallen-Breath/preprocessor
    id("com.replaymod.preprocess") version "9d21b334a7"

    // https://github.com/Fallen-Breath/yamlang
    id("me.fallenbreath.yamlang") version "1.4.1" apply false
}

preprocess {
    strictExtraMappings = false

    val mc12006 = createNode("1.20.6", 1_20_06, "")
    val mc12101 = createNode("1.21.1", 1_21_01, "")
    val mc12104 = createNode("1.21.4", 1_21_04, "")
    val mc12105 = createNode("1.21.5", 1_21_05, "")

    mc12006.link(mc12101, null)
    mc12101.link(mc12104, null)
    mc12104.link(mc12105, null)
}

tasks.register("buildAndGather") {
    subprojects {
        dependsOn(project.tasks.named("build").get())
    }

    doFirst {
        println("Gathering builds")

        val buildLibs = {
            it: Project -> it.layout.buildDirectory.dir("libs").get().asFile.toPath()
        }

        delete(fileTree(buildLibs(rootProject)) {
            include("*")
        })

        subprojects {
            copy {
                from(buildLibs(project)) {
                    include("*.jar")
                    exclude("*-dev.jar", "*-sources.jar", "*-shadow.jar")
                }
                into(buildLibs(rootProject))
                duplicatesStrategy = DuplicatesStrategy.INCLUDE
            }
        }
    }
}