package io.github.mrlucky974.dracula_api.api.item;

import net.minecraft.item.Item;

public abstract class SimpleCrossbowProjectileItem extends Item implements CrossbowProjectileItem {
    public SimpleCrossbowProjectileItem(Settings settings) {
        super(settings);
    }
}