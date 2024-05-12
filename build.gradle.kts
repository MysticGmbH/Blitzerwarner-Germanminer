plugins {
    id("java-library")
    id("net.labymod.gradle")
    id("net.labymod.gradle.addon")
}

group = "de.germanimer.blackthonderjr"
version = "1.0.0"

labyMod {
    defaultPackageName = "de.germanminer.blackthonderjr" //change this to your main package name (used by all modules)
    addonInfo {
        namespace = "blitzerwarner"
        displayName = "Blitzer Warner Germanminer"
        author = "BlackThonder_Jr/AndreChan2008"
        description = "Dieses Addon wurde speziell für den Server GermanMiner.DE entwickelt. Es bietet einen Blitzer-Warner für den Server GermanMiner.de. Sie können nahezu alle Einstellungen selbst konfigurieren und anpassen, genau nach Ihren Bedürfnissen. Das Addon informiert Sie im Chat, mit Sound und einer großflächigen Bildschirmeinblendung, sobald Sie sich in der Nähe eines Blitzers befinden."
        minecraftVersion = "1.12.2"
        version = System.getenv().getOrDefault("VERSION", "0.0.1")
    }

    minecraft {
        registerVersions(
                "1.12.2"
        ) { version, provider ->
            configureRun(provider, version)
        }

        subprojects.forEach {
            if (it.name != "game-runner") {
                filter(it.name)
            }
        }
    }

    addonDev {
        productionRelease()
    }
}

subprojects {
    plugins.apply("java-library")
    plugins.apply("net.labymod.gradle")
    plugins.apply("net.labymod.gradle.addon")

    repositories {
        maven("https://libraries.minecraft.net/")
        maven("https://repo.spongepowered.org/repository/maven-public/")
    }
}

fun configureRun(provider: net.labymod.gradle.core.minecraft.provider.VersionProvider, gameVersion: String) {
    provider.runConfiguration {
        mainClass = "net.minecraft.launchwrapper.Launch"
        jvmArgs("-Dnet.labymod.running-version=${gameVersion}")
        jvmArgs("-Dmixin.debug=true")
        jvmArgs("-Dnet.labymod.debugging.all=true")
        jvmArgs("-Dmixin.env.disableRefMap=true")

        args("--tweakClass", "net.labymod.core.loader.vanilla.launchwrapper.LabyModLaunchWrapperTweaker")
        args("--labymod-dev-environment", "true")
        args("--addon-dev-environment", "true")
    }

    provider.javaVersion = when (gameVersion) {
        else -> {
            JavaVersion.VERSION_21
        }
    }

    provider.mixin {
        val mixinMinVersion = when (gameVersion) {
            "1.8.9", "1.12.2", "1.16.5" -> {
                "0.6.6"
            }

            else -> {
                "0.8.2"
            }
        }

        minVersion = mixinMinVersion
    }
}
