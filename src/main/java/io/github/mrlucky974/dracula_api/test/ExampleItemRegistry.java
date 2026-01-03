package io.github.mrlucky974.dracula_api.test;

import io.github.mrlucky974.dracula_api.api.ModEntrypoint;
import io.github.mrlucky974.dracula_api.api.registry.ItemRegistry;
import io.github.mrlucky974.dracula_api.test.item.ExampleCrossbowProjectileItem;
import net.minecraft.item.Item;

public class ExampleItemRegistry extends ItemRegistry {
    public static Item EXAMPLE_ITEM;
    public static Item EXAMPLE_CROSSBOW_PROJECTILE_ITEM;

    public ExampleItemRegistry(ModEntrypoint modEntrypoint) {
        super(modEntrypoint);
    }

    @Override
    public void init() {
        EXAMPLE_ITEM = register("example_item", Item::new, new Item.Settings());
        EXAMPLE_CROSSBOW_PROJECTILE_ITEM = register("example_crossbow_projectile_item",
                ExampleCrossbowProjectileItem::new, new Item.Settings());
    }
}
