# TaterAPI

A cross API code library for various generalizations used in the Tater* plugins

## How to use

1. Refactor each `ca.sperrer.p0t4t0sandwich.template.platform` package to your desired package name
2. Refactor the `...template.common.Template.java` file to your base cass name
3. Edit the variables in `./build.sh` to match
4. Go through top to bottom and double check gradle and inside each Main class

Link to our support: [Discord](https://discord.gg/jec2jpdj7A)

## Download

- [GitHub](https://github.com/p0t4t0sandwich/Template/releases)
- [Spigot](https://www.spigotmc.org/resources/template.xxxxxx/)
- [Hangar](https://hangar.papermc.io/p0t4t0sandwich/Template)
- [Modrinth](https://modrinth.com/plugin/template)
- [CurseForge](https://www.curseforge.com/minecraft/mc-mods/template)

### Compatibility Cheatsheet

| Server type | Versions    | Jar Name                             |
|-------------|-------------|--------------------------------------|
| All 1.20    | 1.20-1.20.1 | `Template-<version>-1.20.jar`        |
| Bukkit      | 1.8-1.20    | `Template-<version>-bukkit.jar`      |
| BungeeCord  | 1.20-1.20   | `Template-<version>-bungee.jar`      |
| Velocity    | API v3      | `Template-<version>-velocity.jar`    |
| Fabric 1.20 | 1.20        | `Template-<version>-fabric-1.20.jar` |
| Forge 1.20  | 1.20        | `Template-<version>-forge-1.20.jar`  |

## Dependencies

This plugin requires [Dependency]() to function.

## Commands and Permissions

| Command                                    | Permission         | Description                  |
|--------------------------------------------|--------------------|------------------------------|
| `/command`                                 | `template.command` | Template command             |

## Configuration

```yaml
# Database configuration
# Supported storage types: mongodb, mysql
storage:
  type: mongodb
  config:
    host: localhost
    port: 27017
    database: template
    username: root
    password: password
```
