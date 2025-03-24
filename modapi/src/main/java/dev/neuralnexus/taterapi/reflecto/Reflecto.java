/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterapi.reflecto;

import dev.neuralnexus.taterapi.logger.Logger;
import dev.neuralnexus.taterapi.meta.MetaAPI;
import dev.neuralnexus.taterapi.meta.MinecraftVersion;
import dev.neuralnexus.taterapi.meta.MinecraftVersions;

import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Reflecto is a general, cross runtime mappings reflection framework for Minecraft mods.
 * Specifically, Reflecto is designed to only be used for obfuscated/arbitrary minecraft mappings,
 * and not necessarily for general reflection. <br>
 * I'm keeping it as such to avoid using it as a crutch myself.
 */
@ApiStatus.Experimental
public final class Reflecto {
    private static final Logger logger = Logger.create("Reflecto");
    private static final Reflecto INSTANCE = new Reflecto();
    private static final Map<Class<?>, MappingStore> mappingStores = new HashMap<>();

    private Reflecto() {}

    public static Reflecto instance() {
        return INSTANCE;
    }

    private static final Map<String, Class<?>> classCache = new HashMap<>();
    private static final Map<Class<?>, Map<String, Field>> fieldCache = new HashMap<>();
    private static final Map<Class<?>, Map<String, Method>> methodCache = new HashMap<>();

    /**
     * Register a mapping store using a unique class <br>
     * (ie, something from your own codebase that will never collide with another instance)
     *
     * @param clazz The class to register the mapping store with
     * @return The mapping store
     */
    public MappingStore getStore(@NotNull Class<?> clazz) {
        Objects.requireNonNull(clazz, "Class cannot be null");
        if (!mappingStores.containsKey(clazz)) {
            mappingStores.put(clazz, new MappingStore());
        }
        return mappingStores.get(clazz);
    }

    /**
     * Register a mapping store using an instance of a unique class <br>
     * (ie, something from your own codebase that will never collide with another instance)
     *
     * @param instance The instance to register the mapping store with
     * @return The mapping store
     */
    public MappingStore getStore(@NotNull Object instance) {
        Objects.requireNonNull(instance, "Instance cannot be null");
        return this.getStore(instance.getClass());
    }

    /** Mapping store for class, field, and method mappings */
    public static class MappingStore {
        private final Map<String, String> classMappings = new HashMap<>();
        private final Map<String, Map<String, String>> fieldMappings = new HashMap<>();
        private final Map<String, Map<String, Tuple<String, Class<?>[]>>> methodMappings =
                new HashMap<>();

        /**
         * Register a class mapping
         *
         * @param entry The class mapping entry
         * @return The mapping store
         * @throws ClassRegistrationFailedException If the class registration fails
         * @throws NullPointerException If the entry is null
         */
        public MappingStore registerClass(@NotNull MappingEntry entry)
                throws ClassRegistrationFailedException, NullPointerException {
            Objects.requireNonNull(entry, "Class entry cannot be null");

            if (entry.parentEntry().isPresent()) {
                throw new ClassRegistrationFailedException(
                        entry.name(),
                        entry.mappings(),
                        new IllegalStateException(
                                "Class MappingEntry "
                                        + entry.name()
                                        + " is a class, and cannot have a parentEntry (counter intuitive, I know)"));
            }

            // Check version (or version range) and skip registration if the version doesn't match
            MinecraftVersion version = MetaAPI.instance().version();
            if (entry.version() != MinecraftVersions.UNKNOWN && !version.is(entry.version())) {
                return this;
            }
            if (!version.isInRange(entry.minVersion(), entry.maxVersion())) {
                return this;
            }

            String className = entry.name();
            String classMapping = entry.mappings();

            if (classMappings.containsKey(className)) {
                throw new ClassRegistrationFailedException(
                        className,
                        classMapping,
                        new IllegalStateException("Class " + className + " is already registered"));
            }
            classMappings.put(className, classMapping);

            if (!classCache.containsKey(classMapping)) {
                try {
                    classCache.put(classMapping, Class.forName(classMapping));
                } catch (ClassNotFoundException e) {
                    throw new ClassRegistrationFailedException(className, classMapping, e);
                }
            }

            return this;
        }

        /**
         * Register a class mapping
         *
         * @param entry The class mapping entry builder
         * @return The mapping store
         * @throws ClassRegistrationFailedException If the class registration fails
         * @throws NullPointerException If the entry is null
         */
        public MappingStore registerClass(@NotNull MappingEntry.Builder entry)
                throws ClassRegistrationFailedException, NullPointerException {
            return this.registerClass(entry.build());
        }

        /**
         * Register a field mapping
         *
         * @param entry The field mapping entry
         * @return The mapping store
         * @throws FieldRegistrationFailedException If the field registration fails
         * @throws NullPointerException If the entry is null
         */
        public MappingStore registerField(@NotNull MappingEntry entry)
                throws FieldRegistrationFailedException, NullPointerException {
            Objects.requireNonNull(entry, "Field entry cannot be null");

            if (entry.parentEntry().isEmpty()) {
                throw new FieldRegistrationFailedException(
                        entry.name(),
                        entry.mappings(),
                        new IllegalStateException(
                                "Field MappingEntry "
                                        + entry.name()
                                        + " is a field, and must have a parentEntry"));
            }

            // Check version (or version range) and skip registration if the version doesn't match
            MinecraftVersion version = MetaAPI.instance().version();
            if (entry.version() != MinecraftVersions.UNKNOWN && !version.is(entry.version())) {
                return this;
            }
            if (entry.minVersion() != null
                    && !version.isInRange(entry.minVersion(), entry.maxVersion())) {
                return this;
            }

            String fieldName = entry.name();
            String fieldMapping = entry.mappings();
            String parentEntry = entry.parentEntry().get();

            if (!fieldMappings.containsKey(parentEntry)) {
                fieldMappings.put(parentEntry, new HashMap<>());
            }
            if (fieldMappings.get(parentEntry).containsKey(fieldName)) {
                throw new FieldRegistrationFailedException(
                        fieldName,
                        fieldMapping,
                        new IllegalStateException("Field " + fieldName + " is already registered"));
            }
            fieldMappings.get(parentEntry).put(fieldName, fieldMapping);

            String parentMapping = classMappings.get(parentEntry);
            Class<?> parentEntryType = classCache.get(parentMapping);
            if (!fieldCache.containsKey(parentEntryType)) {
                fieldCache.put(parentEntryType, new HashMap<>());
            }
            if (!fieldCache.get(parentEntryType).containsKey(fieldMapping)) {
                try {
                    Field field = parentEntryType.getField(fieldMapping);
                    field.setAccessible(true);
                    fieldCache.get(parentEntryType).put(fieldMapping, field);
                } catch (NoSuchFieldException e) {
                    throw new FieldRegistrationFailedException(fieldName, fieldMapping, e);
                }
            }

            return this;
        }

        /**
         * Register a field mapping
         *
         * @param entry The field mapping entry builder
         * @return The mapping store
         * @throws FieldRegistrationFailedException If the field registration fails
         * @throws NullPointerException If the entry is null
         */
        public MappingStore registerField(@NotNull MappingEntry.Builder entry)
                throws FieldRegistrationFailedException, NullPointerException {
            return this.registerField(entry.build());
        }

        /**
         * Register a method mapping
         *
         * @param entry The method mapping entry
         * @param parameterTypes The parameter types of the method
         * @return The mapping store
         * @throws MethodRegistrationFailedException If the method registration fails
         * @throws NullPointerException If the entry is null
         */
        public MappingStore registerMethod(
                @NotNull MappingEntry entry, @Nullable Class<?>... parameterTypes)
                throws MethodRegistrationFailedException, NullPointerException {
            Objects.requireNonNull(entry, "Method entry cannot be null");
            if (parameterTypes == null) {
                parameterTypes = new Class<?>[0];
            }

            if (entry.parentEntry().isEmpty()) {
                throw new MethodRegistrationFailedException(
                        entry.name(),
                        entry.mappings(),
                        new IllegalStateException(
                                "Method MappingEntry "
                                        + entry.name()
                                        + " is a method, and must have a parentEntry"));
            }

            // Check version (or version range) and skip registration if the version doesn't match
            MinecraftVersion version = MetaAPI.instance().version();
            if (entry.version() != MinecraftVersions.UNKNOWN && !version.is(entry.version())) {
                return this;
            }
            if (entry.minVersion() != null
                    && !version.isInRange(entry.minVersion(), entry.maxVersion())) {
                return this;
            }

            String methodName = entry.name();
            String methodMapping = entry.mappings();
            String parentEntry = entry.parentEntry().get();

            if (!methodMappings.containsKey(parentEntry)) {
                methodMappings.put(parentEntry, new HashMap<>());
            }
            if (methodMappings.get(parentEntry).containsKey(methodName)) {
                throw new MethodRegistrationFailedException(
                        methodName,
                        methodMapping,
                        new IllegalStateException(
                                "Method " + methodName + " is already registered"));
            }
            methodMappings
                    .get(parentEntry)
                    .put(methodName, new Tuple<>(methodMapping, parameterTypes));

            String parentMapping = classMappings.get(parentEntry);
            Class<?> parentEntryType = classCache.get(parentMapping);
            if (!methodCache.containsKey(parentEntryType)) {
                methodCache.put(parentEntryType, new HashMap<>());
            }
            if (!methodCache.get(parentEntryType).containsKey(methodMapping)) {
                Class<?> clazz = parentEntryType;
                while (clazz != null) {
                    for (Method method : clazz.getDeclaredMethods()) {
                        if (method.getName().equals(methodMapping)) {
                            method.setAccessible(true);
                            methodCache.get(parentEntryType).put(methodMapping, method);
                            return this;
                        }
                    }
                    clazz = clazz.getSuperclass();
                }
            }
            throw new MethodRegistrationFailedException(
                    methodName, methodMapping, new IllegalStateException("No method found"));
        }

        /**
         * Register a method mapping
         *
         * @param entry The method mapping entry builder
         * @param parameterTypes The parameter types of the method
         * @return The mapping store
         * @throws MethodRegistrationFailedException If the method registration fails
         * @throws NullPointerException If the entry is null
         */
        public MappingStore registerMethod(
                @NotNull MappingEntry.Builder entry, @Nullable Class<?>... parameterTypes)
                throws MethodRegistrationFailedException, NullPointerException {
            return this.registerMethod(entry.build(), parameterTypes);
        }

        /**
         * Get a class by its entry name
         *
         * @param entryName The entry name of the class
         * @return The class
         * @throws ClassNotRegisteredException If the class is not registered
         * @throws NullPointerException If the entry name is null
         */
        @SuppressWarnings("unchecked")
        public <T> Class<T> getClass(@NotNull String entryName)
                throws ClassNotRegisteredException, NullPointerException {
            Objects.requireNonNull(entryName, "Entry name cannot be null");
            if (!classMappings.containsKey(entryName)) {
                throw new ClassNotRegisteredException(entryName);
            }
            return (Class<T>) classCache.get(classMappings.get(entryName));
        }

        /**
         * Get a field by its parent entry and entry name
         *
         * @param parentEntry The parent entry of the field
         * @param entryName The entry name of the field
         * @return The field
         * @throws FieldNotRegisteredException If the field is not registered
         * @throws FieldNotAccessableException If the field is not accessible
         * @throws NullPointerException If the parent entry or entry name is null
         */
        @SuppressWarnings("unchecked")
        public <T> T getField(
                @NotNull String parentEntry, @NotNull String entryName, @Nullable Object instance)
                throws FieldNotAccessableException,
                        FieldNotRegisteredException,
                        NullPointerException {
            Objects.requireNonNull(parentEntry, "Parent class cannot be null");
            Objects.requireNonNull(entryName, "Field name cannot be null");
            if (!fieldMappings.containsKey(parentEntry)
                    || !fieldMappings.get(parentEntry).containsKey(entryName)) {
                throw new FieldNotRegisteredException(parentEntry, entryName);
            }
            String parentMapping = classMappings.get(parentEntry);
            Field field =
                    fieldCache
                            .get(classCache.get(parentMapping))
                            .get(fieldMappings.get(parentEntry).get(entryName));
            try {
                return (T) field.get(instance);
            } catch (IllegalAccessException e) {
                throw new FieldNotAccessableException(parentEntry, entryName);
            }
        }

        /**
         * Get a static field by its parent entry and entry name
         *
         * @param parentEntry The parent entry of the field
         * @param entryName The entry name of the field
         * @return The field
         * @throws FieldNotRegisteredException If the field is not registered
         * @throws FieldNotAccessableException If the field is not accessible
         * @throws NullPointerException If the parent entry or entry name is null
         */
        public <T> T getStaticField(@NotNull String parentEntry, @NotNull String entryName)
                throws FieldNotAccessableException,
                        FieldNotRegisteredException,
                        NullPointerException {
            return this.getField(parentEntry, entryName, null);
        }

        /**
         * Invoke a method by its parent entry and entry name
         *
         * @param parentEntry The parent entry of the method
         * @param entryName The entry name of the method
         * @param instance The instance to invoke the method on
         * @param args The arguments to pass to the method
         * @return The return value of the method
         * @throws MethodNotRegisteredException If the method is not registered
         * @throws MethodNotAccessableException If the method is not accessible
         * @throws NullPointerException If the parent entry, entry name, or return type is null
         */
        @SuppressWarnings("unchecked")
        public <T> T invokeMethod(
                @NotNull String parentEntry,
                @NotNull String entryName,
                @Nullable Object instance,
                @Nullable Object... args)
                throws NullPointerException,
                        MethodNotAccessableException,
                        MethodNotRegisteredException {
            Objects.requireNonNull(parentEntry, "Parent class cannot be null");
            Objects.requireNonNull(entryName, "Method name cannot be null");

            if (!methodMappings.containsKey(parentEntry)
                    || !methodMappings.get(parentEntry).containsKey(entryName)) {
                throw new MethodNotRegisteredException(parentEntry, entryName);
            }
            Tuple<String, Class<?>[]> methodTuple = methodMappings.get(parentEntry).get(entryName);
            String parentMapping = classMappings.get(parentEntry);
            Method method = methodCache.get(classCache.get(parentMapping)).get(methodTuple.left());
            try {
                return (T) method.invoke(instance, args);
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new MethodNotAccessableException(parentEntry, entryName);
            }
        }

        /**
         * Invoke a static method by its parent entry and entry name
         *
         * @param parentEntry The parent entry of the method
         * @param entryName The entry name of the method
         * @param args The arguments to pass to the method
         * @return The return value of the method
         * @throws MethodNotRegisteredException If the method is not registered
         * @throws MethodNotAccessableException If the method is not accessible
         * @throws NullPointerException If the parent entry, entry name, or return type is null
         */
        public <T> T invokeStaticMethod(
                @NotNull String parentEntry, @NotNull String entryName, @Nullable Object... args)
                throws NullPointerException,
                        MethodNotAccessableException,
                        MethodNotRegisteredException {
            return this.invokeMethod(parentEntry, entryName, null, args);
        }
    }

    private record Tuple<A, B>(A left, B right) {}

    public static class ClassRegistrationFailedException extends RuntimeException {
        public ClassRegistrationFailedException(
                String className, String classMapping, Throwable e) {
            super("Failed to register class: " + className + " with mapping: " + classMapping, e);
        }
    }

    public static class FieldRegistrationFailedException extends RuntimeException {
        public FieldRegistrationFailedException(
                String fieldName, String fieldMapping, Throwable e) {
            super("Failed to register field: " + fieldName + " with mapping: " + fieldMapping, e);
        }
    }

    public static class MethodRegistrationFailedException extends RuntimeException {
        public MethodRegistrationFailedException(
                String methodName, String methodMapping, Throwable e) {
            super(
                    "Failed to register method: " + methodName + " with mapping: " + methodMapping,
                    e);
        }
    }

    public static class ClassNotRegisteredException extends RuntimeException {
        public ClassNotRegisteredException(String className) {
            super("Class " + className + " is not registered");
        }
    }

    public static class FieldNotRegisteredException extends RuntimeException {
        public FieldNotRegisteredException(String className, String fieldName) {
            super("Field " + fieldName + " is not registered in class " + className);
        }
    }

    public static class MethodNotRegisteredException extends RuntimeException {
        public MethodNotRegisteredException(String className, String methodName) {
            super("Method " + methodName + " is not registered in class " + className);
        }
    }

    public static class FieldNotAccessableException extends RuntimeException {
        public FieldNotAccessableException(String className, String fieldName) {
            super("Field " + fieldName + " is not accessible in class " + className);
        }
    }

    public static class MethodNotAccessableException extends RuntimeException {
        public MethodNotAccessableException(String className, String methodName) {
            super("Method " + methodName + " is not accessible in class " + className);
        }
    }
}
