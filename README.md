# TaterLib

A cross API code library for various generalizations used in the Tater* plugins

Please note, some abstractions may not be fully implemented yet, and some may be missing.
If you're looking for a specific abstraction/game event, please open an issue, and we'll get to it as soon as possible, or feel free to open a PR with an implementation.

Link to our support: [Discord](https://discord.neuralnexus.dev)

## Download

- [GitHub](https://github.com/p0t4t0sandwich/TaterLib/releases)
- [Maven Repo](https://maven.neuralnexus.dev/#/releases/dev/neuralnexus/TaterLib)
- [Spigot](https://www.spigotmc.org/resources/taterlib.111852/)
- [Hangar](https://hangar.papermc.io/p0t4t0sandwich/TaterLib)
- [Modrinth](https://modrinth.com/plugin/taterlib)
- [CurseForge](https://www.curseforge.com/minecraft/mc-mods/taterlib)
- [Sponge](https://ore.spongepowered.org/p0t4t0sandwich/TaterLib)

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

TaterLib supports: Bukkit, BungeeCord, Fabric, Forge, Sponge (some versions), and Velocity

| Server type        | Versions    | Jar Name                      |
|--------------------|-------------|-------------------------------|
| All 1.20           | 1.20-1.20.1 | `TaterLib-<version>-1.20.jar` |
| All 1.19           | 1.19-1.19.4 | `TaterLib-<version>-1.19.jar` |
| All 1.18           | 1.18-1.18.2 | `TaterLib-<version>-1.18.jar` |
| All 1.17           | 1.17-1.17.1 | `TaterLib-<version>-1.17.jar` |
| All 1.16 (Sponge8) | 1.16-1.16.5 | `TaterLib-<version>-1.16.jar` |
| All 1.15           | 1.15-1.15.2 | `TaterLib-<version>-1.15.jar` |
| All 1.14           | 1.14-1.14.3 | `TaterLib-<version>-1.14.jar` |

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

- [BeeNameGenerator](https://github.com/p0t4t0sandwich/BeeNameGeneratorPlugin/)

## TODO
- Generic-ify the Forge abstractions to allow for Type inputs?
  - Or just create a wonky loader

### Abstractions
- Command abstraction
- World abstraction
- Server abstraction
  - `AbstractServer`, `AbstractModdedServer`, `AbstractProxyServer`
- Improved player abstraction
  - Split into `AbstractOfflinePlayer`, `AbstractPlayer` and `AbstractServerPlayer`
  - Add teleport methods to `AbstractPlayer`

### Maybes
- Implement modded permission checks that can use numerical values
- Implement the Forge permissions API

## Release Notes
- Created a GenericLogger class for better cross compatibility
- Added getX/Y/Z methods to AbstractEntity
