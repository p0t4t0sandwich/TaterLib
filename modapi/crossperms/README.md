# CrossPerms

An abstract permission query API that supports all major modding platforms.

## Mappings Shopping List

| Version | Mojang                                            | MCP | SRG | Intermediary               | Yarn                                       | Spigot                                          |
|---------|---------------------------------------------------|-----|-----|----------------------------|--------------------------------------------|-------------------------------------------------|
| 1.21.4  | `net/minecraft/world/entity/player/Player`        | --- | --- | `net/minecraft/class_1657` | `net/minecraft/entity/player/PlayerEntity` | `net/minecraft/world/entity/player/EntityHuman` |
|         | `Player#getGameProfile`                           | --- | --- | `method_7334`              | `getGameProfile`                           | ---                                             |
|         | `Player#hasPermissions`                           | --- | --- | `method_64475`             | `hasPermissionLevel`                       | ---                                             |
|         | `net/minecraft/commands/SharedSuggestionProvider` | --- | --- | `net/minecraft/class_2172` | `net/minecraft/command/CommandSource`      | `net/minecraft/commands/ICompletionProvider`    |
|         | `SharedSuggestionProvider#hasPermission`          | --- | --- | `method_9259`              | `hasPermissionLevel`                       | ---                                             |
|         | ``                                                | --- | --- | ``                         | ``                                         | ---                                             |
|         | ``                                                | --- | --- | ``                         | ``                                         | ---                                             |
|         | ``                                                | --- | --- | ``                         | ``                                         | ---                                             |
|         | ``                                                | --- | --- | ``                         | ``                                         | ---                                             |
|         | ``                                                | --- | --- | ``                         | ``                                         | ---                                             |
|         | ``                                                | --- | --- | ``                         | ``                                         | ---                                             |
|         | ``                                                | --- | --- | ``                         | ``                                         | ---                                             |
|         | ``                                                | --- | --- | ``                         | ``                                         | ---                                             |
|         | ``                                                | --- | --- | ``                         | ``                                         | ---                                             |
|         | ``                                                | --- | --- | ``                         | ``                                         | ---                                             |
|         | ``                                                | --- | --- | ``                         | ``                                         | ---                                             |

| Version  | Mojang                                   | Official                              |
|----------|------------------------------------------|---------------------------------------|
| 1.21.2-4 | `Player#hasPermissions`                  | `s(I)Z`                               |
|          | `SharedSuggestionProvider#hasPermission` | `c(I)Z`                               |



