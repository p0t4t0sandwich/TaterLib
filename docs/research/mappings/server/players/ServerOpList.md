# ServerOpList

- [1.14.4, 1.21.8]: ServerOpList extends StoredUserList<GameProfile, ServerOpListEntry>
- [1.21.9,): ServerOpList extends StoredUserList<NameAndId, ServerOpListEntry>

| Version         | Mojang                                      | Searge                                        | MCP                                           | Calamus                             | Feather                    | Yarn Intermediary          | Yarn                                |
|-----------------|---------------------------------------------|-----------------------------------------------|-----------------------------------------------|-------------------------------------|----------------------------|----------------------------|-------------------------------------|
| 1.7.6 - 1.13.2  |                                             | Lnet/minecraft/server/management/UserListOps; | Lnet/minecraft/server/management/UserListOps; | Lnet/minecraft/unmapped/C_48150417; | Lnet/minecraft/server/Ops; |                            |                                     |
| 1.14 - 1.14.4   |                                             | Lnet/minecraft/server/management/OpList;      | Lnet/minecraft/server/management/OpList;      | Lnet/minecraft/unmapped/C_48150417; | Lnet/minecraft/server/Ops; | Lnet/minecraft/class_3326; | Lnet/minecraft/server/OperatorList; |
| 1.14.4 - 1.16.5 | Lnet/minecraft/server/players/ServerOpList; | Lnet/minecraft/server/management/OpList;      | Lnet/minecraft/server/management/OpList;      |                                     |                            | Lnet/minecraft/class_3326; | Lnet/minecraft/server/OperatorList; |
| 1.17 - 1.21.11  | Lnet/minecraft/server/players/ServerOpList; | Lnet/minecraft/src/C_104_;                    |                                               |                                     |                            | Lnet/minecraft/class_3326; | Lnet/minecraft/server/OperatorList; |

## getEntries

Inherited from StoredUserList
- [1.7.6, 1.12.2]: getEntries;()Map<String, ServerOpListEntry>;
- [1.13.2,): getEntries;()Collection<ServerOpListEntry>;
