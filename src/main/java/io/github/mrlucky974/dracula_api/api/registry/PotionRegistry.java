package io.github.mrlucky974.dracula_api.api.registry;

import io.github.mrlucky974.dracula_api.api.ModEntrypoint;
import net.minecraft.potion.Potion;

public abstract class PotionRegistry extends BaseRegistry {
    public PotionRegistry(ModEntrypoint modEntrypoint) {
        super(modEntrypoint);
    }

    protected final <T extends Potion> T register(String name, T potion) {
        return RegistryHelper.registerPotion(modEntrypoint.id(name), potion);
    }
}
