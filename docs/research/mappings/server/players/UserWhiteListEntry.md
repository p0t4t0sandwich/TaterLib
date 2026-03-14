# UserWhiteListEntry

- [1.14.4, 1.21.8]: UserWhiteListEntry extends StoredUserEntry<GameProfile>
- [1.21.9,): UserWhiteListEntry extends StoredUserEntry<NameAndId>

| Version         | Mojang                                            | Searge                                                   | MCP                                                      | Calamus                             | Feather                               | Yarn Intermediary          | Yarn                                  |
|-----------------|---------------------------------------------------|----------------------------------------------------------|----------------------------------------------------------|-------------------------------------|---------------------------------------|----------------------------|---------------------------------------|
| 1.7.6 - 1.13.2  |                                                   | Lnet/minecraft/server/management/UserListWhitelistEntry; | Lnet/minecraft/server/management/UserListWhitelistEntry; | Lnet/minecraft/unmapped/C_02369546; | Lnet/minecraft/server/WhitelistEntry; |                            |                                       |
| 1.14 - 1.14.4   |                                                   | Lnet/minecraft/server/management/WhitelistEntry;         | Lnet/minecraft/server/management/WhitelistEntry;         | Lnet/minecraft/unmapped/C_02369546; | Lnet/minecraft/server/WhitelistEntry; | Lnet/minecraft/class_3340; | Lnet/minecraft/server/WhitelistEntry; |
| 1.14.4 - 1.16.5 | Lnet/minecraft/server/players/UserWhiteListEntry; | Lnet/minecraft/server/management/WhitelistEntry;         | Lnet/minecraft/server/management/WhitelistEntry;         |                                     |                                       | Lnet/minecraft/class_3340; | Lnet/minecraft/server/WhitelistEntry; |
| 1.17 - 1.21.11  | Lnet/minecraft/server/players/UserWhiteListEntry; | Lnet/minecraft/src/C_112_;                               |                                                          |                                     |                                       | Lnet/minecraft/class_3340; | Lnet/minecraft/server/WhitelistEntry; |
