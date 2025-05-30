# Command Registration

## Fabric

### 1.14.4 - 1.18.2

`net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback`

`CommandRegistrationCallback.EVENT.register((dispatcher, dedicated) -> {});`

### 1.19 - 1.21.5

`net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback`

`CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, dedicated) -> {});`

## Forge

### 1.14.4 - 1.15.2

`net.minecraftforge.common.MinecraftForge`
`net.minecraftforge.fml.event.server.FMLServerStartingEvent`

`MinecraftForge.EVENT_BUS.<FMLServerStartingEvent>addListener((FMLServerStartingEvent event) -> {});`

### 1.16.1 - 1.21.5

`net.minecraftforge.common.MinecraftForge`
`net.minecraftforge.event.RegisterCommandsEvent`

`MinecraftForge.EVENT_BUS.<RegisterCommandsEvent>addListener((RegisterCommandsEvent event) -> {});`
