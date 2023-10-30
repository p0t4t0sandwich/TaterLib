# TaterLib

[![License](https://img.shields.io/github/license/p0t4t0sandwich/TaterLib?color=blue)](https://img.shields.io/github/downloads/p0t4t0sandwich/TaterLib/LICENSE)
[![Github](https://img.shields.io/github/stars/p0t4t0sandwich/TaterLib)](https://github.com/p0t4t0sandwich/TaterLib)
[![Github Issues](https://img.shields.io/github/issues/p0t4t0sandwich/TaterLib?label=Issues)](https://github.com/p0t4t0sandwich/TaterLib/issues)
[![Discord](https://img.shields.io/discord/1067482396246683708?color=7289da&logo=discord&logoColor=white)](https://discord.neuralnexus.dev)
[![wakatime](https://wakatime.com/badge/user/fc67ce74-ca69-40a4-912f-61b26dbe3068/project/ba087a5d-fd50-4b54-9723-3effbfda7567.svg)](https://wakatime.com/badge/user/fc67ce74-ca69-40a4-912f-61b26dbe3068/project/ba087a5d-fd50-4b54-9723-3effbfda7567)

A cross API code library for various generalizations used in the Tater* plugins

Please note, some abstractions may not be fully implemented yet, and some may be missing.
If you're looking for a specific abstraction/game event, please open an issue, and we'll get to it as soon as possible, or feel free to open a PR with an implementation.

Link to our support: [Discord](https://discord.neuralnexus.dev)

## Download

[![Github Releases](https://img.shields.io/github/downloads/p0t4t0sandwich/TaterLib/total?label=Github&logo=github&color=181717)](https://github.com/p0t4t0sandwich/TaterLib/releases)
[![Maven Repo](https://img.shields.io/maven-metadata/v?label=Release&metadataUrl=https%3A%2F%2Fmaven.neuralnexus.dev%2Freleases%2Fdev%2Fneuralnexus%2FTaterLib%2Fmaven-metadata.xml)](https://maven.neuralnexus.dev/#/releases/dev/neuralnexus/TaterLib)
[![Maven Repo](https://img.shields.io/maven-metadata/v?label=Snapshot&metadataUrl=https%3A%2F%2Fmaven.neuralnexus.dev%2Fsnapshots%2Fdev%2Fneuralnexus%2FTaterLib%2Fmaven-metadata.xml)](https://maven.neuralnexus.dev/#/snapshots/dev/neuralnexus/TaterLib)

[![Spigot](https://img.shields.io/spiget/downloads/111852?label=Spigot&logo=spigotmc&color=ED8106)](https://www.spigotmc.org/resources/taterlib.111852/)
[![Hangar](https://img.shields.io/badge/Hangar-download-blue)](https://hangar.papermc.io/p0t4t0sandwich/TaterLib)
[![Modrinth](https://img.shields.io/modrinth/dt/taterlib?label=Modrinth&logo=modrinth&color=00AF5C)](https://modrinth.com/mod/taterlib)
[![CurseForge](https://img.shields.io/curseforge/dt/900128?label=CurseForge&logo=curseforge&color=F16436)](https://www.curseforge.com/minecraft/mc-mods/taterlib)
[![Sponge](https://img.shields.io/ore/dt/taterlib?label=Sponge&logo=https%3A%2F%2Fspongepowered.org%2Ffavicon.ico&color=F7CF0D)](https://ore.spongepowered.org/p0t4t0sandwich/TaterLib)

### Adding to your project
```gradle
repositories {
    maven {
        name = "NeuralNexus"
        url = "https://maven.neuralnexus.dev/repository/releases/"
    }
}

dependencies {
    implementation "dev.neuralnexus:TaterLib:<mcversion>-<version>"
}
```

### Compatibility Cheatsheet

TaterLib supports: Bukkit, BungeeCord, Fabric, Forge, Sponge, and Velocity

| Server type                   | Versions     | Jar Name                               |
|-------------------------------|--------------|----------------------------------------|
| 1.20.2 (Sponge11)             | 1.20.2       | `TaterLib-1.20.2-<version>.jar`        |
| 1.20-1.20.1 (Sponge11)        | 1.20-1.20.1  | `TaterLib-1.20-<version>.jar`          |
| All 1.19 (Sponge10)           | 1.19-1.19.4  | `TaterLib-1.19-<version>.jar`          |
| All 1.18 (Sponge9)            | 1.18-1.18.2  | `TaterLib-1.18-<version>.jar`          |
| All 1.17 (Sponge9)            | 1.17-1.17.1  | `TaterLib-1.17-<version>.jar`          |
| All 1.16 (Sponge8)            | 1.16-1.16.5  | `TaterLib-1.16-<version>.jar`          |
| All 1.15 (Sponge8)            | 1.15-1.15.2  | `TaterLib-1.15-<version>.jar`          |
| All 1.14                      | 1.14-1.14.3  | `TaterLib-1.14-<version>.jar`          |
| All 1.13 (no Fabric)          | 1.13-1.13.2  | `TaterLib-1.13-<version>.jar`          |
| All 1.12 (Sponge7, no Fabric) | 1.12-1.12.2  | `TaterLib-1.12-<version>.jar`          |
| Bukkit 1.7.10                 | 1.7.2-1.7.10 | `TaterLib-bukkit-1.7.10-<version>.jar` |
| Bukkit 1.6.4                  | 1.6.1-1.6.4  | `TaterLib-bukkit-1.6.4-<version>.jar`  |
| Bukkit 1.2.5                  | 1.2.5        | `TaterLib-bukkit-1.2.5-<version>.jar`  |
| Bukkit b1.7.3                 | b1.7.3       | `TaterLib-bukkit-b1.7.3-<version>.jar` |

## Dependencies

- [FabricAPI](https://modrinth.com/mod/fabric-api) - Required on Fabric

### Optional Dependencies

- [LuckPerms](https://luckperms.net/) - For permissions/prefix/suffix support

## Commands and Permissions

| Command                      | Permission          | Description           |
|------------------------------|---------------------|-----------------------|
| `/taterlib <version/reload>` | `taterlib.command`  | Root TaterLib Command |

## Projects that use TaterLib

Feel free to open a PR to add your plugin/mod to this list!

- [BadSpawns](https://github.com/p0t4t0sandwich/BadSpawns)
- [BeeNameGenerator](https://github.com/p0t4t0sandwich/BeeNameGeneratorPlugin)
- [TaterComms](https://github.com/p0t4t0sandwich/TaterComms)

## Release Notes

### 1.1.0-R0.1-SNAPSHOT

- Fixes to Sponge8-11 component serialization
- Fixed `SpongeEntity.getType` returning a properly formatted entity resource
- Reworked the entire event system
- Added `getEntity` to `AbstractEntity` implementations
