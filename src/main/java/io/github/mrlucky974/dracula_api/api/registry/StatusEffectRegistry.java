package io.github.mrlucky974.dracula_api.api.registry;

import io.github.mrlucky974.dracula_api.api.util.RegistryHelper;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.entry.RegistryEntry;

public abstract class StatusEffectRegistry extends BaseRegistry {
    protected static <T extends StatusEffect> RegistryEntry<T> register(String name, T statusEffect) {
        return RegistryHelper.registerStatusEffect(id(name), statusEffect);
    }
}
