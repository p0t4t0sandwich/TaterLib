# Player
`net.minecraft.world.entity.player.Player`

## 1.14 - 1.15.2
`void sendMessage(net.minecraft.network.chat.Component)`
- indirectly through `net.minecraft.commands.CommandSource`

## 1.16 - 1.18.2
`void sendMessage(net.minecraft.network.chat.Component, UUID)`
- indirectly through `net.minecraft.commands.CommandSource`

## 1.19 - 1.21.5
`void sendSystemMessage(net.minecraft.network.chat.Component)`
- 1.19 - 1.21.1 indirectly through `net.minecraft.commands.CommandSource`
- 1.21.2 - 1.21.5 implemented directly

## 1.14 - 1.21.5
`implements net.minecraft.world.Nameable`
- indirectly through `net.minecraft.world.entity.Entity`

`UUID Entity#getUUID()`

`com.mojang.authlib.GameProfile getGameProfile()`

## 1.14 - 1.21.1
`implements net.minecraft.commands.CommandSource`
- indirectly through `net.minecraft.world.entity.Entity`

## 1.21.2 - 1.21.5
`int getPermissionLevel()`

`boolean hasPermissions(int)`
