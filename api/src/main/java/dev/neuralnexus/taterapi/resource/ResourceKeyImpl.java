package dev.neuralnexus.taterapi.resource;

/**
 * An abstraction for resource locations.
 */
public class ResourceKeyImpl implements ResourceKey {
    private final String namespace;
    private final String value;

    /**
     * Constructor.
     *
     * @param namespace The namespace.
     * @param value The value.
     */
    public ResourceKeyImpl(String namespace, String value) {
        this.namespace = namespace;
        this.value = value;
    }

    /**
     * Constructor
     *
     * @param full The full resource location.
     */
    public ResourceKeyImpl(String full) {
        if (full.contains(":")) {
            this.namespace = full.split(":")[0];
            this.value = full.split(":")[1];
        } else {
            this.namespace = "";
            this.value = full;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ResourceKey) {
            return asString().equals(((ResourceKey) obj).asString());
        }
        return false;
    }

    @Override
    public String value() {
        return value;
    }

    @Override
    public String namespace() {
        return namespace;
    }

    public static class Factory implements ResourceKey.Factory {
        @Override
        public ResourceKey of(String namespace, String value) {
            return new ResourceKeyImpl(namespace, value);
        }

        @Override
        public ResourceKey of(String full) {
            return new ResourceKeyImpl(full);
        }

        @Override
        public ResourceKey unsafeOf(String full) {
            return new ResourceKeyImpl(full);
        }
    }

    public static class Builder implements ResourceKey.Builder {
        private String namespace;
        private String value;

        @Override
        public ResourceKey.Builder namespace(String namespace) {
            this.namespace = namespace;
            return this;
        }

        @Override
        public ResourceKey.Builder value(String value) {
            this.value = value;
            return this;
        }

        @Override
        public ResourceKey build() {
            return new ResourceKeyImpl(namespace, value);
        }
    }
}
