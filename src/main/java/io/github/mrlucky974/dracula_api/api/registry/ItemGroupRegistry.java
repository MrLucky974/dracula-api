package io.github.mrlucky974.dracula_api.api.registry;

import io.github.mrlucky974.dracula_api.api.util.RegistryHelper;
import net.minecraft.item.ItemGroup;

public abstract class ItemGroupRegistry extends BaseRegistry {
    protected static <T extends ItemGroup> T register(String name, T itemGroup) {
        return RegistryHelper.registerItemGroup(id(name), itemGroup);
    }
}
