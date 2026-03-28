# Services

| Version          | Mojang                          | Searge                        | MCP | Calamus | Feather | Yarn Intermediary          | Yarn                             |
|------------------|---------------------------------|-------------------------------|-----|---------|---------|----------------------------|----------------------------------|
| 1.19 - 1.21.8    | Lnet/minecraft/server/Services; | Lnet/minecraft/src/C_212929_; |     |         |         | Lnet/minecraft/class_7497; | Lnet/minecraft/util/ApiServices; |
| 1.21.9 - 1.21.11 | Lnet/minecraft/server/Services; | Lnet/minecraft/src/C_212929_; |     |         |         | Lnet/minecraft/class_7497; | Lnet/minecraft/util/ApiServices; |

## nameToIdCache

| Version          | Mojang                                                              | Searge                                    | MCP | Calamus | Feather | Yarn Intermediary                       | Yarn                                               |
|------------------|---------------------------------------------------------------------|-------------------------------------------|-----|---------|---------|-----------------------------------------|----------------------------------------------------|
| 1.19 - 1.21.8    | profileCache;()Lnet/minecraft/server/players/GameProfileCache;      | f_214336_;()Lnet/minecraft/src/C_90_;     |     |         |         | comp_840;()Lnet/minecraft/class_3312;   | userCache;()Lnet/minecraft/util/UserCache;         |
| 1.21.9 - 1.21.11 | nameToIdCache;()Lnet/minecraft/server/players/UserNameToIdResolver; | f_412775_;()Lnet/minecraft/src/C_411262_; |     |         |         | comp_4407;()Lnet/minecraft/class_11561; | nameToIdCache;()Lnet/minecraft/util/NameToIdCache; |
