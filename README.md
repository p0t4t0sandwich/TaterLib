# TaterLib

[![License](https://img.shields.io/github/license/p0t4t0sandwich/TaterLib?color=blue)](https://github.com/p0t4t0sandwich/TaterLib/blob/main/LICENSE)
[![Github](https://img.shields.io/github/stars/p0t4t0sandwich/TaterLib)](https://github.com/p0t4t0sandwich/TaterLib)
[![Github Issues](https://img.shields.io/github/issues/p0t4t0sandwich/TaterLib?label=Issues)](https://github.com/p0t4t0sandwich/TaterLib/issues)
[![Discord](https://img.shields.io/discord/1067482396246683708?color=7289da&logo=discord&logoColor=white)](https://discord.neuralnexus.dev)
[![wakatime](https://wakatime.com/badge/user/fc67ce74-ca69-40a4-912f-61b26dbe3068/project/ba087a5d-fd50-4b54-9723-3effbfda7567.svg)](https://wakatime.com/badge/user/fc67ce74-ca69-40a4-912f-61b26dbe3068/project/ba087a5d-fd50-4b54-9723-3effbfda7567)

A cross API code library that allows developers to write code that works across multiple modding platforms, and across a
wide range of Minecraft versions, all with one JAR file. If TaterLib runs on it, so can your plugin/mod.

Please note, some abstractions may not be fully implemented yet, and some may be missing.
If you're looking for a specific abstraction/game event, please open an issue, and we'll get to it as soon as possible,
or feel free to open a PR with an implementation.
[Contributions are always welcome!](https://github.com/p0t4t0sandwich/TaterLib/blob/main/.github/CONTRIBUTING.md)

Let's cut to the chase, why should you use and/or contribute to TaterLib? Well, let's say you have a mod/plugin that you
want to port to a different modding API. You could go through the laborious task of implementing all the events,
commands, etc. on each platform and create all your own cool fancy abstractions for each game object, or you could use
TaterLib and save yourself a lot of time. (which is why I started this project in the first place)

There's two ways to use TaterLib, you can depend on the general API, then implement any missing features yourself on
each platform, or if something's missing, you can start a PR with either a basic or full implementation, and we can
improve TaterLib and save you a lot of time in the future. (a bit of a win-win)

Link to our support: [Discord](https://discord.neuralnexus.dev)

## Download

[![Github Releases](https://img.shields.io/github/downloads/p0t4t0sandwich/TaterLib/total?label=Github&logo=github&color=181717)](https://github.com/p0t4t0sandwich/TaterLib/releases)

[![Spigot](https://img.shields.io/spiget/downloads/111852?label=Spigot&logo=spigotmc&color=ED8106)](https://www.spigotmc.org/resources/taterlib.111852/)
[![Hangar](https://img.shields.io/badge/Hangar-download-blue)](https://hangar.papermc.io/p0t4t0sandwich/TaterLib)
[![CurseForge](https://img.shields.io/curseforge/dt/980381?label=Bukkit&logo=https%3A%2F%2Fbukkit.org%2favicon.ico&color=1E6AB7)](https://www.curseforge.com/minecraft/bukkit-plugins/taterlib)

[![Modrinth](https://img.shields.io/modrinth/dt/taterlib?label=Modrinth&logo=modrinth&color=00AF5C)](https://modrinth.com/mod/taterlib)
[![CurseForge](https://img.shields.io/curseforge/dt/900128?label=CurseForge&logo=curseforge&color=F16436)](https://www.curseforge.com/minecraft/mc-mods/taterlib)
[![Sponge](https://img.shields.io/ore/dt/taterlib?label=Sponge&logo=https%3A%2F%2Fspongepowered.org%2Ffavicon.ico&color=F7CF0D)](https://ore.spongepowered.org/p0t4t0sandwich/TaterLib)

[![BuiltByBit](https://img.shields.io/badge/BuiltByBit-download-blue?logo=https%3A%2F%2Fbuiltbybit.com%2Ffavicon.ico)](https://builtbybit.com/resources/taterlib.40265/)
[![PolyMart](https://img.shields.io/badge/PolyMart-download-cyan?logo=https%3A%2F%2Fpolymart.org%2Ffavicon.ico)](https://polymart.org/resource/taterlib.5552)
[![Craftaro](https://img.shields.io/badge/Craftaro-download-orange?logo=https%3A%2F%2Fcraftaro.com%2Ffavicon.ico)](https://craftaro.com/marketplace/product/taterlib.2771)

[![Maven Snapshots Repo](https://img.shields.io/maven-metadata/v?label=Snapshot&metadataUrl=https%3A%2F%2Fmaven.neuralnexus.dev%2Fsnapshots%2Fdev%2Fneuralnexus%2Ftaterlib-api%2Fmaven-metadata.xml)](https://maven.neuralnexus.dev/#/snapshots/dev/neuralnexus/taterlib-api)[![Jenkins Dev Build](https://img.shields.io/jenkins/build?jobUrl=https%3A%2F%2Fjenkins.neuralnexus.dev%2Fjob%2FTaterLibDev%2F)](https://jenkins.neuralnexus.dev/job/TaterLibDev/)

[![Maven Repo](https://img.shields.io/maven-metadata/v?label=Release&metadataUrl=https%3A%2F%2Fmaven.neuralnexus.dev%2Freleases%2Fdev%2Fneuralnexus%2Ftaterlib-api%2Fmaven-metadata.xml)](https://maven.neuralnexus.dev/#/releases/dev/neuralnexus/taterlib-api)[![Jenkins Builds](https://img.shields.io/jenkins/build?jobUrl=https%3A%2F%2Fjenkins.neuralnexus.dev%2Fjob%2FTaterLib%2F)](https://jenkins.neuralnexus.dev/job/TaterLib/)

### Adding to your project

```gradle
repositories {
    maven {
        name = 'NeuralNexus'
        url = 'https://maven.neuralnexus.dev/releases'
    }
}

dependencies {
    compileOnly('dev.neuralnexus:taterlib-api:<version>')
}
```

There's also a snapshot repository available at `https://maven.neuralnexus.dev/repository/snapshots`

### Compatibility Cheatsheet

TaterLib supports: Bukkit, BungeeCord, Fabric, Forge, Sponge, and Velocity

General notes:

- No Fabric on 1.13
- No Fabric/Forge below 1.7.10

| Server type | Versions    | Jar Name                        |
|-------------|-------------|---------------------------------|
| 1.20.x      | 1.20-1.20.4 | `TaterLib-1.20.x-<version>.jar` |
| 1.19.4      | 1.19.4      | `TaterLib-1.19.4-<version>.jar` |
| 1.19.2      | 1.19.2      | `TaterLib-1.19.2-<version>.jar` |
| 1.19        | 1.19        | `TaterLib-1.19-<version>.jar`   |
| 1.18.2      | 1.18.2      | `TaterLib-1.18.2-<version>.jar` |
| 1.18        | 1.18        | `TaterLib-1.18-<version>.jar`   |
| 1.17        | 1.17-1.17.1 | `TaterLib-1.17-<version>.jar`   |
| 1.16        | 1.16-1.16.5 | `TaterLib-1.16-<version>.jar`   |
| 1.15        | 1.15-1.15.2 | `TaterLib-1.15-<version>.jar`   |
| 1.14        | 1.14-1.14.3 | `TaterLib-1.14-<version>.jar`   |
| 1.13        | 1.13-1.13.2 | `TaterLib-1.13-<version>.jar`   |
| 1.12        | 1.12-1.12.2 | `TaterLib-1.12-<version>.jar`   |
| 1.11        | 1.11-1.11.2 | `TaterLib-1.11-<version>.jar`   |
| 1.10        | 1.10-1.10.2 | `TaterLib-1.10-<version>.jar`   |
| 1.9         | 1.9-1.9.4   | `TaterLib-1.9-<version>.jar`    |
| 1.8         | 1.8-1.8.8   | `TaterLib-1.8-<version>.jar`    |
| 1.7         | 1.7-1.7.10  | `TaterLib-1.7.10-<version>.jar` |
| 1.6.4       | 1.6.4       | `TaterLib-1.6.4-<version>.jar`  |
| 1.2.5       | 1.2.5       | `TaterLib-1.2.5-<version>.jar`  |
| b1.7.3      | b1.7.3      | `TaterLib-b1.7.3-<version>.jar` |

## Dependencies

- [Fabric API](https://modrinth.com/mod/fabric-api) - Required on Fabric
- [Legacy Fabric API](https://www.curseforge.com/minecraft/mc-mods/legacy-fabric-api) - Required on Fabric 1.12.2 and
  below

### Optional Dependencies

- [LuckPerms](https://luckperms.net/) - For permissions/prefix/suffix support

## Commands and Permissions

| Command              | Permission                  | Description              |
|----------------------|-----------------------------|--------------------------|
| `/taterlib version`  | `taterlib.command.version`  | Get the TaterLib version |
| `/taterlib reload`   | `taterlib.command.reload`   | Reload TaterLib config   |
| `/taterlib dump`     | `taterlib.command.dump`     | Dump TaterLib info       |
| `/taterlib fulldump` | `taterlib.command.fulldump` | Dump TaterLib info       |

## Projects that use TaterLib

Feel free to open a PR to add your plugin/mod to this list!

- [BadSpawns](https://github.com/p0t4t0sandwich/BadSpawns)
- [BeeNameGenerator](https://github.com/p0t4t0sandwich/BeeNameGeneratorPlugin)
- [TaterComms](https://github.com/p0t4t0sandwich/TaterComms)
- [TaterUtils](https://github.com/p0t4t0sandwich/TaterUtils)

## Metrics

### Bukkit

![image](https://bstats.org/signatures/bukkit/TaterLib.svg)

### BungeeCord

![image](https://bstats.org/signatures/bungeecord/TaterLib.svg)

### Sponge

![image](https://bstats.org/signatures/sponge/TaterLib.svg)

### Velocity

![image](https://bstats.org/signatures/velocity/TaterLib.svg)

## Release Notes

### 1.1.0-R0.16

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
    - Forge 1.7.10, 1.8.9, 1.9.4, 1.10.2, 1.11.2, 1.18.2, 1.19.2, 1.19.4
    - Sponge 4, 5
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
- Added `Sender#isPlayer()`
- Fixed `Entity#teleport(Location)` and `Entity#teleport(Entity)` in cross-dimensional cases
- Added `Player#getIPAddress()`
- Created a custom loader implementation, to allow for better compatibility with hybrids, Sinytra Connector, and
  SpongeForge
- Added `SpongeForge` server type
- Added Brigadier support for Sponge and Bukkit
- Renamed `Sender` to `CommandSender`
- Split `Server` into `SimpleServer` and `ProxyServer`
- Added some tests and more `MinecraftVersion` utilities
- Renamed `Event` to `EventManager` and created a base `Event` interface
- Added `Event#getName()`
- Created generic event handler
- Renamed `PluginMessageEvents` to `NetworkEvents`
- Split out `SimplePlayer` and `ProxyPlayer`
- Created `Connection` interface for kick/disconnect/ping/pluginMessage
- Loader can now detect and register more plugin instances in multi-API environments
- Added `ServerType.NEOFORGE_HYBRID`
- Events should no longer register twice in multi-API environments
- Pulled `CommonPluginEnableEvent` into the loader
- Added `Damageable` and `LivingEntity` interfaces
- Added dummy entity interfaces all set up for adding new Entity interfaces
- Added `Player.allowFlight`/`canFly`/`isFlying`/`setFlying`
- Added single-version support for `MinecraftVersion.parseRange`
- Used configurate to create config system
- Refactored TaterLib common logic into CommonModule, added TaterModuleLoader, and overall improved module support
- Reformatted getters to be more idiomatic (e.g. `getInventory` -> `inventory`)
- Refactor `Player.serverName` into `Player.server().name()` and add `Server#name()`
- Added `Permissible` to `CommandSender`, refactored permission hooks to use `Permissible`
- Added `ModInfo` and `PluginInfo` abstractions
- Added `DumpInfo` and `FullDumpInfo`, along with the `/taterlib dump | fulldump` commands
- Added bStats support for Bukkit, Bungee, Sponge, and Velocity
- Added Arclight Fabric server type
- Merged `ItemMeta` into `ItemStack`
- Created player metadata API
- Added `Server.currentTPS()`, `SimpleServer.getPlayer(String)`, and `SimpleServer.getPlayer(UUID)`
- `PlayerServerSwitchEvent.toServer`/`fromServer` now return a `Server` rather than a `String`
- Overhauled the `Inventory`/`PlayerInventory` interfaces
- Created `World` and `ServerWorld` abstractions
