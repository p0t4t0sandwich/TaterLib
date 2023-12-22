# TaterLib

[![License](https://img.shields.io/github/license/p0t4t0sandwich/TaterLib?color=blue)](https://img.shields.io/github/downloads/p0t4t0sandwich/TaterLib/LICENSE)
[![Github](https://img.shields.io/github/stars/p0t4t0sandwich/TaterLib)](https://github.com/p0t4t0sandwich/TaterLib)
[![Github Issues](https://img.shields.io/github/issues/p0t4t0sandwich/TaterLib?label=Issues)](https://github.com/p0t4t0sandwich/TaterLib/issues)
[![Discord](https://img.shields.io/discord/1067482396246683708?color=7289da&logo=discord&logoColor=white)](https://discord.neuralnexus.dev)
[![wakatime](https://wakatime.com/badge/user/fc67ce74-ca69-40a4-912f-61b26dbe3068/project/ba087a5d-fd50-4b54-9723-3effbfda7567.svg)](https://wakatime.com/badge/user/fc67ce74-ca69-40a4-912f-61b26dbe3068/project/ba087a5d-fd50-4b54-9723-3effbfda7567)

A cross API code library for various generalizations used in the Tater* plugins

Please note, some abstractions may not be fully implemented yet, and some may be missing.
If you're looking for a specific abstraction/game event, please open an issue, and we'll get to it as soon as possible,
or feel free to open a PR with an implementation.

Link to our support: [Discord](https://discord.neuralnexus.dev)

## Download

[![Github Releases](https://img.shields.io/github/downloads/p0t4t0sandwich/TaterLib/total?label=Github&logo=github&color=181717)](https://github.com/p0t4t0sandwich/TaterLib/releases)
[![Maven Repo](https://img.shields.io/maven-metadata/v?label=Release&metadataUrl=https%3A%2F%2Fmaven.neuralnexus.dev%2Freleases%2Fdev%2Fneuralnexus%2Ftaterlib-api%2Fmaven-metadata.xml)](https://maven.neuralnexus.dev/#/releases/dev/neuralnexus/taterlib-api)
[![Maven Repo](https://img.shields.io/maven-metadata/v?label=Snapshot&metadataUrl=https%3A%2F%2Fmaven.neuralnexus.dev%2Fsnapshots%2Fdev%2Fneuralnexus%2Ftaterlib-api%2Fmaven-metadata.xml)](https://maven.neuralnexus.dev/#/snapshots/dev/neuralnexus/taterlib-api)

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
    implementation "dev.neuralnexus:taterlib-api:<version>"
}
```

### Compatibility Cheatsheet

TaterLib supports: Bukkit, BungeeCord, Fabric, Forge, Sponge, and Velocity

General notes:

- Sponge is not available on pre 1.8 or on 1.14
- 1.11 and 1.10 only have Sponge support
- No Fabric on 1.13
- No Fabric/Forge below 1.7.10

| Server type     | Versions      | Jar Name                        |
|-----------------|---------------|---------------------------------|
| 1.20.4          | 1.20.3-1.20.4 | `TaterLib-1.20.4-<version>.jar` |
| 1.20.2          | 1.20.2        | `TaterLib-1.20.2-<version>.jar` |
| 1.20            | 1.20-1.20.1   | `TaterLib-1.20-<version>.jar`   |
| 1.19            | 1.19-1.19.4   | `TaterLib-1.19-<version>.jar`   |
| 1.18            | 1.18-1.18.2   | `TaterLib-1.18-<version>.jar`   |
| 1.17            | 1.17-1.17.1   | `TaterLib-1.17-<version>.jar`   |
| 1.16            | 1.16-1.16.5   | `TaterLib-1.16-<version>.jar`   |
| 1.15            | 1.15-1.15.2   | `TaterLib-1.15-<version>.jar`   |
| 1.14            | 1.14-1.14.3   | `TaterLib-1.14-<version>.jar`   |
| 1.13            | 1.13-1.13.2   | `TaterLib-1.13-<version>.jar`   |
| 1.12            | 1.12-1.12.2   | `TaterLib-1.12-<version>.jar`   |
| 1.11            | 1.11-1.11.2   | `TaterLib-1.11-<version>.jar`   |
| 1.10            | 1.10-1.10.2   | `TaterLib-1.10-<version>.jar`   |
| 1.9             | 1.9-1.9.4     | `TaterLib-1.9-<version>.jar`    |
| 1.8             | 1.8-1.8.8     | `TaterLib-1.8-<version>.jar`    |
| 1.7             | 1.7-1.7.10    | `TaterLib-1.7.10-<version>.jar` |
| 1.6.4 (Bukkit)  | 1.6.4         | `TaterLib-1.6.4-<version>.jar`  |
| 1.2.5 (Bukkit)  | 1.2.5         | `TaterLib-1.2.5-<version>.jar`  |
| b1.7.3 (Bukkit) | b1.7.3        | `TaterLib-b1.7.3-<version>.jar` |

## Dependencies

- [Fabric API](https://modrinth.com/mod/fabric-api) - Required on Fabric
- [Legacy Fabric API](https://www.curseforge.com/minecraft/mc-mods/legacy-fabric-api) - Required on Fabric 1.12.2 and
  below

### Optional Dependencies

- [LuckPerms](https://luckperms.net/) - For permissions/prefix/suffix support

## Commands and Permissions

| Command                      | Permission         | Description           |
|------------------------------|--------------------|-----------------------|
| `/taterlib <version/reload>` | `taterlib.command` | Root TaterLib Command |

## Projects that use TaterLib

Feel free to open a PR to add your plugin/mod to this list!

- [BadSpawns](https://github.com/p0t4t0sandwich/BadSpawns)
- [BeeNameGenerator](https://github.com/p0t4t0sandwich/BeeNameGeneratorPlugin)
- [TaterComms](https://github.com/p0t4t0sandwich/TaterComms)
- [TaterUtils](https://github.com/p0t4t0sandwich/TaterUtils)

## Release Notes

### 1.1.0-R0.15

- Fixes to Sponge8-11 component serialization
- Fixed `SpongeEntity.getType` returning a properly formatted entity resource
- Reworked the entire event system
- Added `getEntity` to `AbstractEntity` implementations
- Removed redundant event abstraction
- Reworked TaterLib PlayerListener and reload command
- Implemented `EntitySpawnEvent` for 1.7.10-b1.7.3
- Implemented rudimentary `PlayerDeathEvent` for b1.7.3
- Added support for NeoForge 1.20.2
- Removed the `...taterlib.{platform}.abstractions` package name
- Renamed `...taterlib.{platform}.abstractions.events` to `...taterlib.{platform}.event.api`
- Moved `...taterlib.{platform}.abstractions.item.*` to `...taterlib.{platform}.inventory`
- Moved `...taterlib.{platform}.player.PlayerInventory` to `...taterlib.{platform}.inventory.PlayerInventory`
- Removed the `Abstract` prefix from all common interfaces
- Added the ability to set a player's prefix/suffix
- Added numerical permission checks to `Player` for Forge/Fabric
- Abstracted Brigadier commands
- Abstracted simple commands
- Simplified Plugin abstractions so depending on TaterLib is easier
- Refactored TaterLib helper methods to be wrapped in the `TaterAPI` class
- Added MinecraftVersion and ServerType enums.
- Created `TaterAPI#isBrigadierSupported()`
- Abstracted plugin/mod isLoaded checks
- `Player` now inherits `Entity`
- Added `Entity#teleport(Location)` and `Entity#teleport(Entity)`
- Abstracted brigadier helper into a wrapper class
- Implemented registering simple commands for Forge/Fabric
- Updated database utils
- Added `Server` abstraction and `TaterAPI#getServer()`
- Ported to:
    - 1.20.4
    - Bukkit 1.8.8, 1.13.2
    - BungeeCord 1.4.7, 1.8, 1.12
    - Fabric 1.7.10, 1.8.9, 1.9.4, 1.10.2, 1.11.2, 1.12.2
    - Forge 1.7.10, 1.8.9, 1.9.4, 1.10.2, 1.11.2
    - Sponge 5
- Abstracted `ProxyPlayer`s, specifically adding a `connect` method
- Added `Block` abstraction and `BlockBreakEvent`
- Updated `Server` implementation and added `ProxyServer` for proxies
- Added `ServerEvent#getServer()`
- Fixes to `BukkitPlayerAdvancementEvent`
    - `getPlayer` was returning null
    - `getAdvancement` was returning the wrong string
- Abstracted `TaterAPI#registerChannels(Set<String>)` into `RegisterPluginMessagesEvent`
- Added `PluginEnableEvent` and `PluginDisableEvent`
- Modified TaterAPIProvider to handle multiple API implementations simultaneously
- Added basic hybrid API hooks: Arclight, Ketting, Magma, Mohist
- Renamed `isPluginLoaded` to `isPluginModLoaded` and split it into `isPluginLoaded` and `isModLoaded`, while
  adding helper instantiations from hybrid hooks
- Added `Server#broadcastMessage(String)`
- Renamed path `dev.neuralnexus.taterlib.common` to `dev.neuralnexus.taterlib` to simplify imports
- Improved the hook system to allow for multiple permission managers to be used simultaneously
- Build system overhaul, no sketchy `build.sh` anymore
- Added `GameMode` enum, `Player#getPing()`, `Player#getGameMode()` and `Player#setGameMode()`
- Added `Sender#isPlayer()` and `Sender#getPlayer()`
