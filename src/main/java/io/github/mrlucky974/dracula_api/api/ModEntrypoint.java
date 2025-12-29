package io.github.mrlucky974.dracula_api.api;

import io.github.mrlucky974.dracula_api.api.registry.BaseRegistry;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public abstract class ModEntrypoint implements ModInitializer, Initializable {
    private static final Map<String, ModEntrypoint> INSTANCES = new HashMap<>();

    protected final String modId;
    private final Logger logger;
    private final List<Function<ModEntrypoint, BaseRegistry>> registries = new ArrayList<>();

    public ModEntrypoint(String modId, String modName) {
        if (INSTANCES.containsKey(modId)) {
            throw new IllegalStateException("Duplicate modId: " + modId);
        }

        this.modId = modId;
        this.logger = LoggerFactory.getLogger(modName);
        INSTANCES.put(modId, this);
    }

    public void onInitialize() {
        init();
        initializeRegistries();
    }

    public abstract void init();

    public static ModEntrypoint get(String modId) {
        return INSTANCES.get(modId);
    }

    private void initializeRegistries() {
        for (Function<ModEntrypoint, BaseRegistry> factory : registries) {
            BaseRegistry registry = factory.apply(this);
            registry.init();
        }
    }

    public final void addRegistry(Function<ModEntrypoint, BaseRegistry> factory) {
        registries.add(factory);
    }

    public final Identifier id(String name) {
        return Identifier.of(modId, name);
    }

    public final Logger logger() {
        return logger;
    }
}
