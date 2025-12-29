package io.github.mrlucky974.dracula_api.api.registry;

import io.github.mrlucky974.dracula_api.api.ModEntrypoint;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;

public abstract class StatusEffectRegistry extends BaseRegistry {
    public StatusEffectRegistry(ModEntrypoint modEntrypoint) {
        super(modEntrypoint);
    }

    protected final <T extends StatusEffect> RegistryEntry<T> register(String name, T statusEffect) {
        return Registry.registerReference(Registries.STATUS_EFFECT, modEntrypoint.id(name), statusEffect);
    }
}
