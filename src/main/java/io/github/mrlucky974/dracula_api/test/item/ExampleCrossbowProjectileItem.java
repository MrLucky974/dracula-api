package io.github.mrlucky974.dracula_api.test.item;

import io.github.mrlucky974.dracula_api.api.item.CrossbowProjectileItem;
import net.minecraft.item.CrossbowItem;

public class ExampleCrossbowProjectileItem extends CrossbowProjectileItem {
    public ExampleCrossbowProjectileItem(Settings settings) {
        super(settings);
    }

    @Override
    public CrossbowItem.ChargeType getChargeType() {
        return ExampleCrossbowChargeTypes.TEST.asEnumValue();
    }
}
