# ServerOpListEntry

- [1.14.4, 1.21.8]: ServerOpListEntry extends StoredUserEntry<GameProfile>
- [1.21.9,): ServerOpListEntry extends StoredUserEntry<NameAndId>

| Version         | Mojang                                           | Searge                                             | MCP                                                | Calamus                             | Feather                        | Yarn Intermediary          | Yarn                                 |
|-----------------|--------------------------------------------------|----------------------------------------------------|----------------------------------------------------|-------------------------------------|--------------------------------|----------------------------|--------------------------------------|
| 1.7.6 - 1.13.2  |                                                  | Lnet/minecraft/server/management/UserListOpsEntry; | Lnet/minecraft/server/management/UserListOpsEntry; | Lnet/minecraft/unmapped/C_59165424; | Lnet/minecraft/server/OpEntry; |                            |                                      |
| 1.14 - 1.14.4   |                                                  | Lnet/minecraft/server/management/OpEntry;          | Lnet/minecraft/server/management/OpEntry;          | Lnet/minecraft/unmapped/C_59165424; | Lnet/minecraft/server/OpEntry; | Lnet/minecraft/class_3327; | Lnet/minecraft/server/OperatorEntry; |
| 1.14.4 - 1.16.5 | Lnet/minecraft/server/players/ServerOpListEntry; | Lnet/minecraft/server/management/OpEntry;          | Lnet/minecraft/server/management/OpEntry;          |                                     |                                | Lnet/minecraft/class_3327; | Lnet/minecraft/server/OperatorEntry; |
| 1.17 - 1.21.11  | Lnet/minecraft/server/players/ServerOpListEntry; | Lnet/minecraft/src/C_105_;                         |                                                    |                                     |                                | Lnet/minecraft/class_3327; | Lnet/minecraft/server/OperatorEntry; |

# getLevel

| Version         | Mojang       | Searge            | MCP                    | Calamus        | Feather                | Yarn Intermediary | Yarn                   |
|-----------------|--------------|-------------------|------------------------|----------------|------------------------|-------------------|------------------------|
| 1.7.6 - 1.13.2  |              | func_152644_a;()I | getPermissionLevel;()I | m_35425402;()I | getPermissionLevel;()I |                   |                        |
| 1.14 - 1.14.4   |              | func_152644_a;()I | getPermissionLevel;()I | m_35425402;()I | getPermissionLevel;()I | method_14623;()I  | getPermissionLevel;()I |
| 1.14.4 - 1.16.5 | getLevel;()I | func_152644_a;()I | getPermissionLevel;()I |                |                        | method_14623;()I  | getPermissionLevel;()I |
| 1.17 - 1.21.8   | getLevel;()I | m_11363_;()I      |                        |                |                        | method_14623;()I  | getPermissionLevel;()I |

# permissions

| Version          | Mojang                                                                   | Searge                                    | MCP | Calamus | Feather | Yarn Intermediary                          | Yarn                                                                     |
|------------------|--------------------------------------------------------------------------|-------------------------------------------|-----|---------|---------|--------------------------------------------|--------------------------------------------------------------------------|
| 1.21.9 - 1.21.11 | permissions;()Lnet/minecraft/server/permissions/LevelBasedPermissionSet; | m_441800_;()Lnet/minecraft/src/C_432551_; |     |         |         | method_75039;()Lnet/minecraft/class_12086; | getLevel;()Lnet/minecraft/command/permission/LeveledPermissionPredicate; |
