package io.github.mrlucky974.dracula_api.api;

import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public abstract class AbstractModEntrypoint<TRegistry, TSelf> {
    private static final Map<String, AbstractModEntrypoint<?, ?>> INSTANCES = new HashMap<>();

    protected final String modId;
    private final List<Function<TSelf, TRegistry>> registries = new ArrayList<>();

    protected AbstractModEntrypoint(String modId) {
        if (INSTANCES.containsKey(modId)) {
            throw new IllegalStateException("Duplicate modId: " + modId);
        }
        this.modId = modId;
        INSTANCES.put(modId, this);
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
    public static <E extends AbstractModEntrypoint<?, ?>> E get(String modId) {
        return (E) INSTANCES.get(modId);
    }

    public String modId() {
        return modId;
    }

    public abstract Logger logger();
}