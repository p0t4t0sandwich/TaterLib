# Player
`net.minecraft.world.entity.player.Player`

# sendMessage

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

# net.minecraft.world.Nameable

## 1.14 - 1.21.5
- indirectly through `net.minecraft.world.entity.Entity`

# getGameProfile

## 1.14 - 1.21.5
`com.mojang.authlib.GameProfile getGameProfile()`

# net.minecraft.commands.CommandSource

## 1.14 - 1.21.1
- indirectly through `net.minecraft.world.entity.Entity`

# getPermissionLevel, hasPermissions

## 1.21.2 - 1.21.5
`int getPermissionLevel()`

`boolean hasPermissions(int)`
