package io.github.mrlucky974.dracula_api.api.item;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.jspecify.annotations.Nullable;

public interface CrossbowProjectileItem {
    default boolean shoot(ServerWorld world, LivingEntity shooter, Hand hand,
                         ItemStack weaponStack, ItemStack projectileStack,
                         int projectileIndex, float speed, float divergence, float yaw,
                         boolean critical, @Nullable LivingEntity target) {
        return false;
    }

    default void onProjectileLoaded(ItemStack weaponStack, ItemStack projectileStack, LivingEntity shooter)
    {

    }

    default ProjectileEntity createProjectileEntity(World world, ItemStack projectileStack, ItemStack weaponStack, LivingEntity shooter, double x, double y, double z, boolean critical) {
        return null;
    }

    CrossbowItem.ChargeType getChargeType();
}
