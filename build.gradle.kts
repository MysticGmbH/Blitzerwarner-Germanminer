plugins {
    id("net.labymod.labygradle")
    id("net.labymod.labygradle.addon")
}

val versions = providers.gradleProperty("net.labymod.minecraft-versions").get().split(";")

group = "de.germanminer.blackthonderjr"
version = providers.environmentVariable("VERSION").getOrElse("1.0.0")

labyMod {
    defaultPackageName = "de.germanminer.blackthonderjr" //change this to your main package name (used by all modules)

    minecraft {
        registerVersion(versions.toTypedArray()) {
            runs {
                getByName("client") {
                    // When the property is set to true, you can log in with a Minecraft account
                    // devLogin = true
                }
            }
        }
    }

    addonInfo {
        namespace = "blitzerwarner"
        displayName = "GM Utils"
        author = "BlackThonder_Jr/AndreChan2008"
        description = "Dieses Addon ist für den Server GermanMiner.DE entwickelt worden, es hat Features, die das Spielen auf dem Server Angenehmer macht."
        minecraftVersion = "1.12.2"
        version = System.getenv().getOrDefault("VERSION", "0.0.1")
    }
}

subprojects {
    plugins.apply("net.labymod.labygradle")
    plugins.apply("net.labymod.labygradle.addon")

    group = rootProject.group
    version = rootProject.version
}