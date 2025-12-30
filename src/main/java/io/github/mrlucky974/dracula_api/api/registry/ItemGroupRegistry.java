package io.github.mrlucky974.dracula_api.api.registry;

import io.github.mrlucky974.dracula_api.api.ModEntrypoint;
import net.minecraft.item.ItemGroup;

public abstract class ItemGroupRegistry extends BaseRegistry {
    public ItemGroupRegistry(ModEntrypoint modEntrypoint) {
        super(modEntrypoint);
    }

    protected final <T extends ItemGroup> T register(String name, T itemGroup) {
        return RegistryHelper.registerItemGroup(modEntrypoint.id(name), itemGroup);
    }
}
