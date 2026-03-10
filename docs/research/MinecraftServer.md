# MinecraftServer

| Version | Mojang                                | Searge | MCP | Calamus | Feather | Yarn Intermediary | Yarn |
|---------|---------------------------------------|--------|-----|---------|---------|-------------------|------|
| All     | Lnet/minecraft/server/MinecraftServer | ""     | ""  | ""      | ""      | ""                | ""   |

## isDedicatedServer

| Version         | Mojang                | Searge           | MCP                   | Calamus        | Feather         | Yarn Intermediary | Yarn            |
|-----------------|-----------------------|------------------|-----------------------|----------------|-----------------|-------------------|-----------------|
| 1.7.2 - 1.13.2  |                       | func_71262_S;()Z | isDedicatedServer;()Z | m_45654766;()Z | isDedicated;()Z |                   |                 |
| 1.14 - 1.14.4   |                       | func_71262_S;()Z | isDedicatedServer;()Z | m_45654766;()Z | isDedicated;()Z | method_3816;()Z   | isDedicated;()Z |
| 1.14.4 - 1.16.5 | isDedicatedServer;()Z | func_71262_S;()Z | isDedicatedServer;()Z | m_45654766;()Z | isDedicated;()Z | method_3816;()Z   | isDedicated;()Z |
| 1.17 - 1.21.11  | isDedicatedServer;()Z | m_6982_;()Z      |                       |                |                 | method_3816;()Z   | isDedicated;()Z |

## getPlayerList

| Version         | Mojang                                                    | Searge                                                                       | MCP                                                                                    | Calamus                                          | Feather                                                 | Yarn Intermediary                        | Yarn                                                    |
|-----------------|-----------------------------------------------------------|------------------------------------------------------------------------------|----------------------------------------------------------------------------------------|--------------------------------------------------|---------------------------------------------------------|------------------------------------------|---------------------------------------------------------|
| 1.7.2 - 1.8.9   |                                                           | func_71203_ab;()Lnet/minecraft/server/management/ServerConfigurationManager; | getConfigurationManager;()Lnet/minecraft/server/management/ServerConfigurationManager; | m_49852985;()Lnet/minecraft/unmapped/C_29639016; | getPlayerManager;()Lnet/minecraft/server/PlayerManager; |                                          |                                                         |
| 1.9 - 1.13.2    |                                                           | func_184103_al;()Lnet/minecraft/server/management/PlayerList;                | getPlayerList;()Lnet/minecraft/server/management/PlayerList;                           | m_49852985;()Lnet/minecraft/unmapped/C_29639016; | getPlayerManager;()Lnet/minecraft/server/PlayerManager; |                                          |                                                         |
| 1.14 - 1.14.4   |                                                           | func_184103_al;()Lnet/minecraft/server/management/PlayerList;                | getPlayerList;()Lnet/minecraft/server/management/PlayerList;                           | m_49852985;()Lnet/minecraft/unmapped/C_29639016; | getPlayerManager;()Lnet/minecraft/server/PlayerManager; | method_3760;()Lnet/minecraft/class_3324; | getPlayerManager;()Lnet/minecraft/server/PlayerManager; |
| 1.14.4 - 1.16.5 | getPlayerList;()Lnet/minecraft/server/players/PlayerList; | func_184103_al;()Lnet/minecraft/server/management/PlayerList;                | getPlayerList;()Lnet/minecraft/server/management/PlayerList;                           | m_49852985;()Lnet/minecraft/unmapped/C_29639016; | getPlayerManager;()Lnet/minecraft/server/PlayerManager; | method_3760;()Lnet/minecraft/class_3324; | getPlayerManager;()Lnet/minecraft/server/PlayerManager; |
| 1.17 - 1.21.11  | getPlayerList;()Lnet/minecraft/server/players/PlayerList; | m_6846_;()Lnet/minecraft/src/C_102_;                                         |                                                                                        |                                                  |                                                         | method_3760;()Lnet/minecraft/class_3324; | getPlayerManager;()Lnet/minecraft/server/PlayerManager; |
