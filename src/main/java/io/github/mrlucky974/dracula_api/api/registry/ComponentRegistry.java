package io.github.mrlucky974.dracula_api.api.registry;

import io.github.mrlucky974.dracula_api.api.ModEntrypoint;
import net.minecraft.component.ComponentType;

public abstract class ComponentRegistry extends BaseRegistry {
    public ComponentRegistry(ModEntrypoint modEntrypoint) {
        super(modEntrypoint);
    }

    protected final <T extends ComponentType<?>> T register(String name, T component) {
        return RegistryHelper.registerComponent(modEntrypoint.id(name), component);
    }
}
