pluginManagement {
    repositories {
        maven {
            name = "Fabric"
            url = uri("https://maven.fabricmc.net/")
        }
        // [FUNCTION]
        if (System.getenv("CI") != "true" /* not run in github actions */ ) {
            // If you're not from China, please remove this, it will slow down your downloads
            maven {
                name = "Ali"
                url = uri("https://maven.aliyun.com/repository/gradle-plugin")
                content {
                    excludeGroup("me.modmuss50")
                    excludeGroup("me.fallenbreath")
                }
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}

rootProject.name = "CrystalCarpetAddition"
