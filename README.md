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

TaterLib supports: Bukkit, BungeeCord, Fabric, Forge, Sponge, and Velocity

| Server type          | Versions    | Jar Name                      |
|----------------------|-------------|-------------------------------|
| All 1.20 (Sponge11)  | 1.20-1.20.1 | `TaterLib-1.20-<version>.jar` |
| All 1.19 (Sponge10)  | 1.19-1.19.4 | `TaterLib-1.19-<version>.jar` |
| All 1.18 (Sponge9)   | 1.18-1.18.2 | `TaterLib-1.18-<version>.jar` |
| All 1.17 (Sponge9)   | 1.17-1.17.1 | `TaterLib-1.17-<version>.jar` |
| All 1.16 (Sponge8)   | 1.16-1.16.5 | `TaterLib-1.16-<version>.jar` |
| All 1.15 (Sponge8)   | 1.15-1.15.2 | `TaterLib-1.15-<version>.jar` |
| All 1.14             | 1.14-1.14.3 | `TaterLib-1.14-<version>.jar` |
| All 1.13 (no Fabric) | 1.13-1.13.2 | `TaterLib-1.13-<version>.jar` |
| All 1.12 (no Fabric) | 1.12-1.12.2 | `TaterLib-1.12-<version>.jar` |

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

## TODO

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
- Added methods to AbstractEntity
  - getX/Y/Z, getDimension, getBiome
- Fixed FabricEntity#getType
- Added explicit Fabric 1.19 support to avoid some cross-version issues
- Fixed `registerCommand`s that weren't declared static
- Added proper version getters for `TemplateFabricPlugin`
- Ported Sponge to 7, 9, 10, and 11
- Back ported Forge to 1.12.2
- Fixed `SpongeItemStack#getType` to be unjankified
