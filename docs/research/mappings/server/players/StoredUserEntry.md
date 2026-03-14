# StoredUserEntry

| Version         | Mojang                                         | Searge                                          | MCP                                             | Calamus                             | Feather                                | Yarn Intermediary          | Yarn                                     |
|-----------------|------------------------------------------------|-------------------------------------------------|-------------------------------------------------|-------------------------------------|----------------------------------------|----------------------------|------------------------------------------|
| 1.7.6 - 1.13.2  |                                                | Lnet/minecraft/server/management/UserListEntry; | Lnet/minecraft/server/management/UserListEntry; | Lnet/minecraft/unmapped/C_02601387; | Lnet/minecraft/server/StoredUserEntry; |                            |                                          |
| 1.14 - 1.14.4   |                                                | Lnet/minecraft/server/management/UserListEntry; | Lnet/minecraft/server/management/UserListEntry; | Lnet/minecraft/unmapped/C_02601387; | Lnet/minecraft/server/StoredUserEntry; | Lnet/minecraft/class_3330; | Lnet/minecraft/server/ServerConfigEntry; |
| 1.14.4 - 1.16.5 | Lnet/minecraft/server/players/StoredUserEntry; | Lnet/minecraft/server/management/UserListEntry; | Lnet/minecraft/server/management/UserListEntry; |                                     |                                        | Lnet/minecraft/class_3330; | Lnet/minecraft/server/ServerConfigEntry; |
| 1.17 - 1.21.11  | Lnet/minecraft/server/players/StoredUserEntry; | Lnet/minecraft/src/C_106_;                      |                                                 |                                     |                                        | Lnet/minecraft/class_3330; | Lnet/minecraft/server/ServerConfigEntry; |

# getUser

- Returns Object due to generics

| Version         | Mojang                       | Searge                             | MCP                           | Calamus                         | Feather                      | Yarn Intermediary                 | Yarn                        |
|-----------------|------------------------------|------------------------------------|-------------------------------|---------------------------------|------------------------------|-----------------------------------|-----------------------------|
| 1.7.6 - 1.13.2  |                              | func_152640_f;()Ljava/lang/Object; | getValue;()Ljava/lang/Object; | m_05084164;()Ljava/lang/Object; | getUser;()Ljava/lang/Object; |                                   |                             |
| 1.14 - 1.14.4   |                              | func_152640_f;()Ljava/lang/Object; | getValue;()Ljava/lang/Object; | m_05084164;()Ljava/lang/Object; | getUser;()Ljava/lang/Object; | method_14626;()Ljava/lang/Object; | getKey;()Ljava/lang/Object; |
| 1.14.4 - 1.16.5 | getUser;()Ljava/lang/Object; | func_152640_f;()Ljava/lang/Object; |                               |                                 |                              | method_14626;()Ljava/lang/Object; | getKey;()Ljava/lang/Object; |
| 1.17 - 1.21.11  | getUser;()Ljava/lang/Object; | m_11373_;()Ljava/lang/Object;      |                               |                                 |                              | method_14626;()Ljava/lang/Object; | getKey;()Ljava/lang/Object; |
