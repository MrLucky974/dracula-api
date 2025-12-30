package io.github.mrlucky974.dracula_api.api.registry;

import io.github.mrlucky974.dracula_api.api.ModEntrypoint;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.entry.RegistryEntry;

public abstract class StatusEffectRegistry extends BaseRegistry {
    public StatusEffectRegistry(ModEntrypoint modEntrypoint) {
        super(modEntrypoint);
    }

    protected final <T extends StatusEffect> RegistryEntry<T> register(String name, T statusEffect) {
        return RegistryHelper.registerStatusEffect(modEntrypoint.id(name), statusEffect);
    }
}
