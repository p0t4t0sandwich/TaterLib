# TaterAPI

A cross API code library for various generalizations used in the Tater* plugins

Please note, some abstractions may not be fully implemented yet, and some may be missing.
If you're looking for a specific abstraction, please open an issue, and we'll get to it as soon as possible, or feel free to open a PR with an implementation.

Link to our support: [Discord](https://discord.gg/jec2jpdj7A)

## Download

- [GitHub](https://github.com/p0t4t0sandwich/TaterLib/releases)
- [Spigot](https://www.spigotmc.org/resources/taterlib.xxxxxx/)
- [Hangar](https://hangar.papermc.io/p0t4t0sandwich/TaterLib)
- [Modrinth](https://modrinth.com/plugin/taterlib)
- [CurseForge](https://www.curseforge.com/minecraft/mc-mods/taterlib)

### Compatibility Cheatsheet

| Server type     | Versions    | Jar Name                                 |
|-----------------|-------------|------------------------------------------|
| All 1.20        | 1.20-1.20.1 | `TaterLib-<version>-1.20.jar`            |
| Bukkit          | 1.8-1.20    | `TaterLib-<version>-bukkit.jar`          |
| BungeeCord      | 1.20-1.20   | `TaterLib-<version>-bungee.jar`          |
| Velocity        | API v3      | `TaterLib-<version>-velocity.jar`        |
| Fabric 1.20     | 1.20        | `TaterLib-<version>-fabric-1.20.jar`     |
| Forge 1.20      | 1.20        | `TaterLib-<version>-forge-1.20.jar`      |
| NeoForge 1.20.1 | 1.20.1      | `TaterLib-<version>-neoforge-1.20.1.jar` |

## Dependencies

This plugin has no strict dependencies, but it does have optional dependencies.

### Optional Dependencies

- [LuckPerms](https://luckperms.net/) - For permissions/prefix/suffix support

## Commands and Permissions

| Command                      | Permission          | Description           |
|------------------------------|---------------------|-----------------------|
| `/taterlib <version/reload>` | `taterlib.command`  | Root TaterLib Command |

## Configuration

```yaml
---
```

# TODO
- add support for built-in permission APIs
  - Fabric
  - Forge

## Abstractions
- World abstraction
