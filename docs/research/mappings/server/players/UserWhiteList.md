# UserWhiteList

- [1.14.4, 1.21.8]: UserWhiteList extends StoredUserList<GameProfile, UserWhiteListEntry>
- [1.21.9,): UserWhiteList extends StoredUserList<NameAndId, UserWhiteListEntry>

| Version         | Mojang                                       | Searge                                              | MCP                                                 | Calamus                             | Feather                          | Yarn Intermediary          | Yarn                             |
|-----------------|----------------------------------------------|-----------------------------------------------------|-----------------------------------------------------|-------------------------------------|----------------------------------|----------------------------|----------------------------------|
| 1.7.6 - 1.13.2  |                                              | Lnet/minecraft/server/management/UserListWhitelist; | Lnet/minecraft/server/management/UserListWhitelist; | Lnet/minecraft/unmapped/C_28507727; | Lnet/minecraft/server/Whitelist; |                            |                                  |
| 1.14 - 1.14.4   |                                              | Lnet/minecraft/server/management/WhiteList;         | Lnet/minecraft/server/management/WhiteList;         | Lnet/minecraft/unmapped/C_28507727; | Lnet/minecraft/server/Whitelist; | Lnet/minecraft/class_3337; | Lnet/minecraft/server/Whitelist; |
| 1.14.4 - 1.16.5 | Lnet/minecraft/server/players/UserWhiteList; | Lnet/minecraft/server/management/WhiteList;         | Lnet/minecraft/server/management/WhiteList;         |                                     |                                  | Lnet/minecraft/class_3337; | Lnet/minecraft/server/Whitelist; |
| 1.17 - 1.21.11  | net/minecraft/server/players/UserWhitelist;  | Lnet/minecraft/src/C_111_;                          |                                                     |                                     |                                  | Lnet/minecraft/class_3337; | Lnet/minecraft/server/Whitelist; |

## getEntries

Inherited from StoredUserList
- [1.7.6, 1.12.2]: getEntries;()Map<String, UserWhiteListEntry>;
- [1.13.2,): getEntries;()Collection<UserWhiteListEntry>;
