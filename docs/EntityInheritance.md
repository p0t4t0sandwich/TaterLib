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
ServerAware

### Extended Interfaces

Entity -> Actor, Identifiable, Nameable
* TODO: Locatable and Teleportable
* TODO: Make it ServerAware

LivingEntity -> Damageable, Entity

HumanEntity -> LivingEntity, InventoryHolder

User -> Subject, Identifiable, Nameable, Notifiable, ServerAware

Player -> HumanEntity, User
* Overrides `inventory()` to return a `PlayerInventory`

ServerPlayer -> Connection, Player

ProxyPlayer -> Connection, User

#### ModAPI

Actor -> Identifiable, Nameable

Subject -> Identifiable, Nameable, Notifiable, ServerAware

#### Brigadier General

CommandSource -> Identifiable, Nameable, Notifiable, ServerAware
