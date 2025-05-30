# CommandSource

# sendMessage

## 1.14 - 1.15.2
`void net.minecraft.commands.CommandSource#sendMessage(net.minecraft.network.chat.Component)`

## 1.16 - 1.18.2
`void net.minecraft.commands.CommandSourceStack#sendMessage(net.minecraft.network.chat.Component, UUID)`

## 1.19 - 1.21.5
`void net.minecraft.commands.CommandSourceStack#sendSystemMessage(net.minecraft.network.chat.Component)`

# acceptsSuccess, acceptsFailure, shouldInformAdmins

## 1.14 - 1.21.5
`boolean net.minecraft.commands.CommandSource#acceptsSuccess()`

`boolean net.minecraft.commands.CommandSource#acceptsFailure()`

`boolean net.minecraft.commands.CommandSource#shouldInformAdmins()`

# alwaysAccepts

## 1.17 - 1.21.5
`boolean net.minecraft.commands.CommandSource#alwaysAccepts() default false`
