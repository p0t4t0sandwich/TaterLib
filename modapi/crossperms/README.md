# CrossPerms

An abstract permission query API that supports all major modding platforms.

## Mappings Shopping List

Forge 1.18.2-1.20.4:
- net/minecraft/commands/CommandSourceStack -> net/minecraft/src/C_2969_
  - getEntity -> m_81373_
- net/minecraft/server/level/ServerPlayer -> net/minecraft/src/C_13_
- net/minecraft/world/entity/Entity -> net/minecraft/src/C_507_
  - getUUID -> m_142081_

Forge 1.10.2-1.18:
- net/minecraft/command/ICommandSender -> net/minecraft/command/ICommandSender
  - getCommandSenderEntity -> func_174793_f

- net/minecraft/entity/player/EntityPlayer -> net/minecraft/entity/player/EntityPlayer
  - getGameProfile -> func_146103_bH
