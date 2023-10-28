# DevNotes for wiki

## Known gaps in implementations

### Bukkit latest

- `AbstractAdvancementProgress` requires Paper's `PlayerAdvancementCriterionGrantEvent` to fire

### Bukkit 1.7.10

- Doesn't seem to be an `EntitySpawnEvent`
- `BukkitPlayerDeathEvent`'s `hasKeepInventory` and `setKeepInventory` are not implemented
- `PlayerAdvancementProgressEvent` can not be implemented

### Bukkit 1.6.4

- `PlayerAdvancementFinishedEvent` can not be implemented

### Bukkit beta 1.7.3

- `PlayerDeathEvent` can not be implemented
- `onEntityDamageByEntity` and `onEntityDamageByBlock` need a proper listener
- Doesn't support messaging channels

### Fabric 1.20.2

- `EntityDeathEvent` needs `getDrops`, `setDrops`, `clearDrops`, and `setDroppedExp`
