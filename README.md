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

### Supported Platforms and Versions

#### Bukkit

- Spigot/Paper/etc
- Hybrid support included
- b1.7.3, 1.2.5, 1.6.4-1.20.x

#### BungeeCord

- Waterfall/Travertine/etc
- 1.4.7-1.20.x

#### Fabric

- Quilt
- 1.7.10-1.12.2, 1.14-1.20.x
- Legacy Fabric API currently doesn't support 1.13, and I don't feel like writing *that* many mixins by hand at the moment

#### Forge

- 1.7.10-1.20.x

#### NeoForge

- 1.20.2-1.20.x

#### Sponge

- SpongeForge/SpongeVanilla
- APIs 4-11

#### Velocity

- v3

## Dependencies

- [Fabric API](https://modrinth.com/mod/fabric-api) - Required on Fabric
- [Legacy Fabric API](https://www.curseforge.com/minecraft/mc-mods/legacy-fabric-api) - Required on Fabric 1.12.2 and below

### Optional Dependencies

- [LuckPerms](https://luckperms.net/) - For permissions/prefix/suffix support
- [Spark](https://spark.lucko.me/) - For simple TPS/System info

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
- [Switchboard](https://github.com/p0t4t0sandwich/Switchboard)
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
