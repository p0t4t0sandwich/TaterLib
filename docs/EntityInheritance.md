# Entity Inheritance

## Overview

I hate this

## Rundown

### Base Interfaces

Connection
Damageable
Identifiable
InventoryHolder
Nameable
Notifiable

### Extended Interfaces

Entity -> Identifiable, Nameable

LivingEntity -> Damageable, Entity

HumanEntity -> LivingEntity, InventoryHolder

User -> Identifiable, Nameable, Notifiable

Player -> HumanEntity, User
* Overrides `inventory()` to return a `PlayerInventory`

ServerPlayer -> Connection, Player

ProxyPlayer -> Connection, User

CommandSender -> Identifiable, Nameable, Notifiable
