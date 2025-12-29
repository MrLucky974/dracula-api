package io.github.mrlucky974.dracula_api.api.registry;

import io.github.mrlucky974.dracula_api.api.ModEntrypoint;
import net.minecraft.potion.Potion;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public abstract class PotionRegistry extends BaseRegistry {
    public PotionRegistry(ModEntrypoint modEntrypoint) {
        super(modEntrypoint);
    }

    protected final <T extends Potion> T register(String name, T potion) {
        return Registry.register(Registries.POTION, modEntrypoint.id(name), potion);
    }
}
