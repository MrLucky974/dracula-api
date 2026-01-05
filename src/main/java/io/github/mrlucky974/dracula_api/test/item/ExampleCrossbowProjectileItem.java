package io.github.mrlucky974.dracula_api.test.item;

import io.github.mrlucky974.dracula_api.api.item.SimpleCrossbowProjectileItem;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.entity.projectile.thrown.SnowballEntity;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ExampleCrossbowProjectileItem extends SimpleCrossbowProjectileItem {
    public ExampleCrossbowProjectileItem(Settings settings) {
        super(settings);
    }

    @Override
    public ProjectileEntity createProjectileEntity(World world, ItemStack projectileStack, ItemStack weaponStack, LivingEntity shooter, double x, double y, double z, boolean critical) {
        return new SnowballEntity(world, shooter, projectileStack);
    }

    @Override
    public CrossbowItem.ChargeType getChargeType() {
        return ExampleCrossbowChargeTypes.TEST.asEnumValue();
    }
}
