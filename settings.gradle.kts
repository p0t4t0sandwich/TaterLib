pluginManagement {
    repositories {
        maven("https://maven.neuralnexus.dev/mirror")
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version("1.0.0")
}

rootProject.name = "taterlib"

include(
        ":versions:modern-utils",
        ":versions:v1_6_4",
        ":versions:v1_7_10",
        ":versions:v1_8_9",
        ":versions:v1_9_4",
        ":versions:v1_10_2",
        ":versions:v1_11_2",
        ":versions:v1_12_2",
        ":versions:v1_13_2",
        ":versions:v1_14_4",
        ":versions:v1_15_2",
        ":versions:v1_16_1",
        ":versions:v1_16_5",
        ":versions:v1_17_1",
        ":versions:v1_18_2",
        ":versions:v1_19",
        ":versions:v1_19_4",
        ":versions:v1_20_1",
        ":versions:v1_20_2",
        ":versions:v1_20_4",
        ":versions:v1_20_6",
        ":versions:v1_21_1",
        ":versions:v1_21_3",
        ":versions:v1_21_4",

	    // ModAPI libraries
        ":modapi:base",
        ":modapi:core",
        ":modapi:crossperms",
        ":modapi:brigadier-general",
        ":modapi:brigadier-general:bg-api",
        ":modapi:brigadier-general:bg1_14_4",
        ":modapi:brigadier-general:bg1_16_5",
        ":modapi:brigadier-general:bg1_17_1",
        ":modapi:brigadier-general:bg1_19_4",
	    ":modapi:entrypoint-spoof",
        ":modapi:metadata",
        ":modapi:muxins",
        ":modapi",

        // Common
        ":api",
        ":common",
        ":loader",
        ":testmod",

        // Legacy Bukkit
        ":bukkit:bukkit-b1.7.3", // Needs the CraftBukkit Jar in mavenLocal() or access to the private NN repo
        ":bukkit:bukkit-1.2.5", // Needs the CraftBukkit Jar in mavenLocal() or access to the private NN repo
        ":bukkit:bukkit-1.6.4", // Needs the CraftBukkit Jar in mavenLocal() or access to the private NN repo
        ":bukkit:bukkit-1.7.10", // Needs the CraftBukkit Jar in mavenLocal() or access to the private NN repo

        // Bukkit
        ":bukkit:bukkit-1.8.8",
        ":bukkit:bukkit-1.13.2",
        ":bukkit:bukkit-1.15.2",
        ":bukkit:bukkit-1.20",
        ":bukkit:bukkit-utils",

        // Bungee
        ":bungee:bungee-1.4.7",
        ":bungee:bungee-1.8",
        ":bungee:bungee-1.12",
        ":bungee:bungee-1.20",
        ":bungee:bungee-utils",

        // Velocity
        ":velocity:velocity-3"
)
