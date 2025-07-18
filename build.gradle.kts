import java.util.Calendar

plugins {
    id("fabric-loom").version("1.11-SNAPSHOT")
    id("me.fallenbreath.yamlang").version("1.4.1")
    id("com.github.hierynomus.license").version("0.16.1")
    id("me.modmuss50.mod-publish-plugin").version("0.8.4")
    id("maven-publish")
}

base {
    archivesName = "${project.property("archives_base_name")}"
}

fun getModVersion(): String {
    var version = project.property("mod_version") as String
    if (System.getenv("BUILD_RELEASE") != "true" && System.getenv("JITPACK") != "true") {
        val buildNumber = System.getenv("GITHUB_RUN_NUMBER")
        version += if (buildNumber != null) ("-build.$buildNumber") else "-snapshot"
    }
    return version
}

group = "${project.property("maven_group")}"
version = "v${this.getModVersion()}-mc${project.property("minecraft_version")}"

repositories {
    maven {
        name = "Fabric"
        url = uri("https://maven.fabricmc.net/")
    }
    maven {
        name = "Modrinth"
        url = uri("https://api.modrinth.com/maven")
    }
    maven {
        name = "Fallen"
        url = uri("https://maven.fallenbreath.me/releases")
    }
    maven {
        name = "JitPack"
        url = uri("https://jitpack.io")
    }
}

val enableLithium = true
dependencies {
    // To change the versions see the gradle.properties file
    minecraft("com.mojang:minecraft:${project.property("minecraft_version")}")
    mappings("net.fabricmc:yarn:${project.property("yarn_mappings")}:v2")
    modImplementation("net.fabricmc:fabric-loader:${project.property("loader_version")}")

    // dependencies
    modRuntimeOnly("me.fallenbreath:mixin-auditor:0.1.0")
    modImplementation("com.github.gnembon:fabric-carpet:${project.property("carpet_version")}")
    modImplementation("me.fallenbreath:conditional-mixin-fabric:${project.property("conditionalmixin_version")}")?.let {
        include(it)
    }
    if (enableLithium) {
        modImplementation("maven.modrinth:lithium:${project.property("lithium_version")}")
    } else {
        modCompileOnly("maven.modrinth:lithium:${project.property("lithium_version")}")
    }
    annotationProcessor("io.github.llamalad7:mixinextras-fabric:${project.property("mixinextras_version")}")?.let {
        implementation(it)
        include(it)
    }
}

loom {
    accessWidenerPath.set(file("src/main/resources/cca.accesswidener"))

    val commonVmArgs = listOf("-Dmixin.debug.export=true", "-Dcca.enable.debug=true")
    runConfigs.configureEach {
        vmArgs(commonVmArgs)
    }

    // MIXIN_AUDITOR
    runs {
        val auditVmArgs = listOf(*commonVmArgs.toTypedArray(), "-DmixinAuditor.audit=true")
        create("serverMixinAudit") {
            server()
            vmArgs(auditVmArgs)
            ideConfigGenerated(false)
        }
    }
}

yamlang {
    targetSourceSets.set(listOf(sourceSets["main"]))
    inputDir.set("assets/cca/lang")
}

tasks.processResources {
    val modId = project.property("mod_id")
    val modName = project.property("mod_name")
    val modVersion = "${getModVersion()}+mc${project.property("minecraft_version")}"
    val conditionalmixin = project.property("conditionalmixin_version")
    val minecraft = project.property("minecraft_dependency")
    val mixinextras = project.property("mixinextras_version")

    inputs.property("id", modId)
    inputs.property("name", modName)
    inputs.property("version", modVersion)
    inputs.property("conditional_mixin_dependency", conditionalmixin)
    inputs.property("minecraft_dependency", minecraft)
    inputs.property("mixinextras_dependency", mixinextras)

    filesMatching("fabric.mod.json") {
        val valueMap = mapOf(
            "id" to modId,
            "name" to modName,
            "version" to modVersion,
            "conditional_mixin_dependency" to conditionalmixin,
            "minecraft_dependency" to minecraft,
            "mixinextras_dependency" to mixinextras
        )
        expand(valueMap)
    }
}

tasks.withType<JavaCompile>().configureEach {
    options.release.set(21)
}

// ensure that the encoding is set to UTF-8, no matter what the system default is
// this fixes some edge cases with special characters not displaying correctly
// see http://yodaconditions.net/blog/fix-for-java-file-encoding-problems-with-gradle.html
tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

java {
    // Loom will automatically attach sourcesJar to a RemapSourcesJar task and to the "build" task
    // if it is present.
    // If you remove this line, sources will not be generated.
    withSourcesJar()

    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

tasks.jar {
    inputs.property("archivesName", project.base.archivesName)

    from("LICENSE") {
        rename { "${it}_${inputs.properties["archivesName"]}" }
    }
}

license {
    // use "gradle licenseFormat" to apply license headers
    header = rootProject.file("HEADER.txt")
    include("**/*.java")
    skipExistingHeaders = true

    headerDefinitions {
        // ref: https://github.com/mathieucarbou/license-maven-plugin/blob/4c42374bb737378f5022a3a36849d5e23ac326ea/license-maven-plugin/src/main/java/com/mycila/maven/plugin/license/header/HeaderType.java#L48
        // modification: add a newline at the end
        create("SLASHSTAR_STYLE_NEWLINE") {
            firstLine = "/*"
            beforeEachLine = " * "
            endLine = " */" + System.lineSeparator()
            afterEachLine = ""
            skipLinePattern = null
            firstLineDetectionPattern = "(\\s|\\t)*/\\*.*\$"
            lastLineDetectionPattern = ".*\\*/(\\s|\\t)*\$"
            allowBlankLines = false
            isMultiline = true
            padLines = false
        }
    }
    mapping("java", "SLASHSTAR_STYLE_NEWLINE")

    ext {
        set("name", project.property("mod_name") as String)
        set("author", "Crystal0404")
        set("year", Calendar.getInstance().get(Calendar.YEAR).toString())
    }
}
tasks.named("classes").configure {
    dependsOn(tasks.named("licenseFormatMain"))
}
tasks.named("testClasses").configure {
    dependsOn(tasks.named("licenseFormatTest"))
}

publishMods {
    val debug = providers.environmentVariable("BUILD_RELEASE").orNull == null
    dryRun = debug
    file = tasks.remapJar.get().archiveFile
    displayName = "${project.property("mod_name")} v${getModVersion()} for mc${project.property("minecraft_version")}"
    version = "v${getModVersion()}-mc${project.property("minecraft_version")}"
    changelog = if (debug) "#Test" else providers.environmentVariable("CHANGELOG").get()
    modLoaders.add("fabric")
    type = when {
        getModVersion().endsWith("alpha") -> ALPHA
        getModVersion().endsWith("beta") -> BETA
        else -> STABLE
    }
    modrinth {
        accessToken = providers.environmentVariable("MODRINTH_API_KEY")
        projectId = "G26sLP13"
        minecraftVersionRange {
            val range = project.property("minecraft_version_range").toString().split("-")
            start = range.first()
            end = range.last()
        }
        requires("carpet")
    }
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            artifactId = project.base.archivesName.get()
            from(components["java"])
        }
    }

    // See https://docs.gradle.org/current/userguide/publishing_maven.html for information on how to set up publishing.
    repositories {
        // Add repositories to publish to here.
        // Notice: This block does NOT have the same function as the block in the top level.
        // The repositories here will be used for publishing your artifact, not for
        // retrieving dependencies.
    }
}