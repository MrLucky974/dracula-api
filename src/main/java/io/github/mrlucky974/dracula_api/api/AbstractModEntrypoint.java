package io.github.mrlucky974.dracula_api.api;

import io.github.mrlucky974.dracula_api.api.client.ModClientEntrypoint;
import io.github.mrlucky974.dracula_api.api.registry.Entrypoint;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;

import java.util.*;
import java.util.function.Function;

public abstract class AbstractModEntrypoint<TRegistry, TSelf> {
    private static final Map<EntryKey, AbstractModEntrypoint<?, ?>> INSTANCES = new HashMap<>();

    protected final String modId;
    protected final Entrypoint entrypoint;
    private final List<Function<TSelf, TRegistry>> registries = new ArrayList<>();

    protected AbstractModEntrypoint(String modId, Entrypoint entrypoint) {
        this.modId = modId;
        this.entrypoint = entrypoint;
        EntryKey key = new EntryKey(modId, entrypoint);
        if (INSTANCES.containsKey(key)) {
            throw new IllegalStateException(
                    "Duplicate entrypoint for modId '" + modId + "' and entrypoint '" + entrypoint + "'"
            );
        }

        INSTANCES.put(key, this);
    }

    @SuppressWarnings("unchecked")
    protected final void initializeRegistries() {
        for (Function<TSelf, TRegistry> factory : registries) {
            TRegistry registry = factory.apply((TSelf) this);
            initRegistry(registry);
        }
    }

    protected abstract void initRegistry(TRegistry registry);

    public final void addRegistry(Function<TSelf, TRegistry> factory) {
        registries.add(factory);
    }

    public final Identifier id(String name) {
        return Identifier.of(modId, name);
    }

    @SuppressWarnings("unchecked")
    public static <E extends AbstractModEntrypoint<?, ?>> E get(String modId, Entrypoint entrypoint) {
        return (E) INSTANCES.get(modId);
    }

    public static ModEntrypoint getCommon(String modId) {
        return (ModEntrypoint) INSTANCES.get(new EntryKey(modId, Entrypoint.COMMON));
    }

    public static ModClientEntrypoint getClient(String modId) {
        return (ModClientEntrypoint) INSTANCES.get(new EntryKey(modId, Entrypoint.CLIENT));
    }

    @SuppressWarnings("unchecked")
    public static <E extends AbstractModEntrypoint<?, ?>> E getServer(String modId) {
        return (E) INSTANCES.get(new EntryKey(modId, Entrypoint.SERVER));
    }

    public String modId() {
        return modId;
    }

    public abstract Logger logger();

    private static final class EntryKey {
        private final String modId;
        private final Entrypoint entrypoint;

        private EntryKey(String modId, Entrypoint entrypoint) {
            this.modId = Objects.requireNonNull(modId);
            this.entrypoint = Objects.requireNonNull(entrypoint);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof EntryKey)) return false;
            EntryKey that = (EntryKey) o;
            return modId.equals(that.modId) && entrypoint == that.entrypoint;
        }

        @Override
        public int hashCode() {
            return Objects.hash(modId, entrypoint);
        }
    }
}