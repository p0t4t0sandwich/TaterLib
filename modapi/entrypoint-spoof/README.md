# Entrypoint Spoof

A simple dependency that will allow you to target multiple Minecraft mod/plugin loaders with a single jar, without the
hassle of using each and every build system.

Credit
to [kosma](https://github.com/kosmolot-mods/minecraft-mysql-jdbc/blob/main/forge-1.12/src/main/java/net/minecraftforge/fml/common/Mod.java")
for inspiring the idea.

## Usage

In most cases, you can just slap your init code into a constructor, and it'll work as long as you don't
try and access timing-sensitive plugin/mod APIs right off the bat. To prevent `NoClassDefFoundError`s, you should have
one class dedicated to each entrypoint, that initializes your common code.

```gradle
repositories {
    maven {
        name = 'NeuralNexus'
        url = 'https://maven.neuralnexus.dev/repository/releases/'
    }
}

dependencies {
    compileOnly('dev.neuralnexus:entrypoint-spoof:<version>')
    // Also included in taterapi for ease of use
}
```

Example usage: https://github.com/p0t4t0sandwich/ExampleTaterLibPlugin/tree/simple

## Background

There's a rats nest of annotations, classes, and interfaces to make this all work smoothly, so here's a rundown on each
platform.

### Bukkit

Only thing that's been added is a `org.bukkit.plugin.java.JavaPlugin` abstract class that implements onEnable and
onDisable.

### BungeeCord

Pretty much the same as Bukkit, but with `net.md_5.bungee.api.plugin.Plugin` instead.

### Forge

In an effort to target both legacy (1.12.2-) and modern (1.13+) versions of Forge, there's a bit of voodoo going on.

Both use the same `net.net.minecraftforge.fml.common.Mod` annotation, but the modern version has a `value` field that
represents the mod ID, while the legacy version has a `modid` field. You can set both, and the legacy/modern
implementations will ignore the other.

### Fabric

Fabric is the easiest of the bunch, as it has a few nearly identical functional
interfaces: `ModInitializer`, `ClientModInitializer`, and `DedicatedServerModInitializer`. All three have a
single `onInitialize` method that's used to initialize the mod.

### NeoForge

Same idea as modern Forge, but with `net.neoforged.fml.common.Mod` instead.
Also adds the `com.mojang.logging.LogUtils` class to allow for logging to the Mojang logger.

### Sponge

Similar to Forge, Sponge has a different annotation for each version, but it's a bit easier since they both have
different package names. `org.spongepowered.api.plugin.Plugin` is used for SpongeAPI 7-,
and `org.spongepowered.plugin.jvm.Plugin` is used for SpongeAPI 8+.
Unless you're planning on using Sponge's gradle plugin, you need to whip up your own `mcmod.info` (Sponge 7-)
and `META-INF/sponge_plugins.json` (Sponge 8-) files manually or by substituting values with gradle properties.

### Velocity

Velocity just has a straightforward `com.velocitypowered.api.plugin.Plugin` annotation. Once again, unless you're
planning on using Velocity's annotation processor, you need to whip up your own `velocity-plugin.json` file manually or
by substituting values with gradle properties.

### Extras

I've included the following annotations that Sponge/Velocity use for dependency injection:

- `com.google.inject.Inject` (Sponge and Velocity)
- `org.spongepowered.api.plugin.PluginContainer` (Sponge 7-)
- `org.spongepowered.plugin.PluginContainer` (Sponge 8+)
- `org.slf4j.Logger` (Sponge 7- and Velocity)
- `org.apache.logging.log4j.Logger` (Sponge 8+)
