# TaterLib

A cross API code library for various generalizations used in the Tater* plugins

Please note, some abstractions may not be fully implemented yet, and some may be missing.
If you're looking for a specific abstraction, please open an issue, and we'll get to it as soon as possible, or feel free to open a PR with an implementation.

Link to our support: [Discord](https://discord.neuralnexus.dev)

## Download

- [GitHub](https://github.com/p0t4t0sandwich/TaterLib/releases)
- [Maven Repo](https://maven.neuralnexus.dev/#/releases/dev/neuralnexus/TaterLib)
- [Spigot](https://www.spigotmc.org/resources/taterlib.111852/)
- [Hangar](https://hangar.papermc.io/p0t4t0sandwich/TaterLib)
- [Modrinth](https://modrinth.com/plugin/taterlib)
- [CurseForge](https://www.curseforge.com/minecraft/mc-mods/taterlib)
- Sponge

### Adding to your project
```gradle
repositories {
    maven {
        name = "NeuralNexus"
        url = "https://maven.neuralnexus.dev/repository/releases/"
    }
}

dependencies {
    implementation "dev.neuralnexus:TaterLib:<version>-<mcversion>"
}
```

### Compatibility Cheatsheet

| Server type     | Versions    | Jar Name                                 |
|-----------------|-------------|------------------------------------------|
| All 1.20        | 1.20-1.20.1 | `TaterLib-<version>-1.20.jar`            |
| All 1.19        | 1.19-1.19.4 | `TaterLib-<version>-1.19.jar`            |
| All 1.18        | 1.18-1.18.2 | `TaterLib-<version>-1.18.jar`            |
| All 1.17        | 1.17-1.17.1 | `TaterLib-<version>-1.17.jar`            |
| All 1.16        | 1.16-1.16.5 | `TaterLib-<version>-1.16.jar`            |
| All 1.15        | 1.15-1.15.2 | `TaterLib-<version>-1.15.jar`            |
| All 1.14        | 1.14-1.14.3 | `TaterLib-<version>-1.14.jar`            |
| Bukkit          | 1.8-1.20    | `TaterLib-<version>-bukkit.jar`          |
| BungeeCord      | 1.12-1.20   | `TaterLib-<version>-bungee.jar`          |
| Velocity        | API v3      | `TaterLib-<version>-velocity.jar`        |

## Dependencies

- [FabricAPI](https://modrinth.com/mod/fabric-api) - Required on Fabric

### Optional Dependencies

- [LuckPerms](https://luckperms.net/) - For permissions/prefix/suffix support

## Commands and Permissions

| Command                      | Permission          | Description           |
|------------------------------|---------------------|-----------------------|
| `/taterlib <version/reload>` | `taterlib.command`  | Root TaterLib Command |

## TODO
- add support for built-in permission APIs
  - Fabric
  - Forge

### Abstractions
- Command abstraction
- World abstraction
- Server abstraction
  - `AbstractServer`, `AbstractModdedServer`, `AbstractProxyServer`
- Improved player abstraction
  - Split into `AbstractOfflinePlayer`, `AbstractPlayer` and `AbstractServerPlayer`
  - Add teleport methods to `AbstractPlayer`

## Release Notes
- Added a method to PlayerCache to return a list of all the players in the cache
- Added a static method parseSectionSign in the PlaceholderParser class
- Finished v1 of the cross API event bus
- Updated the MessageRelay
- Added basic implementations of the Player Advancement and Player Death events (String only)
