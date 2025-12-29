package io.github.mrlucky974.dracula_api.api.registry;

import io.github.mrlucky974.dracula_api.api.ModEntrypoint;
import net.minecraft.component.ComponentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public abstract class ComponentRegistry extends BaseRegistry {
    public ComponentRegistry(ModEntrypoint modEntrypoint) {
        super(modEntrypoint);
    }

    protected final <T extends ComponentType<?>> T register(String name, T component) {
        return Registry.register(Registries.DATA_COMPONENT_TYPE, modEntrypoint.id(name), component);
    }
}
