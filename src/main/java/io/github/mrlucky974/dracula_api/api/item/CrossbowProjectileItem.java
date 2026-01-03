package io.github.mrlucky974.dracula_api.api.item;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.jspecify.annotations.Nullable;

public abstract class CrossbowProjectileItem extends Item {
    public CrossbowProjectileItem(Settings settings) {
        super(settings);
    }

    public boolean shoot(ServerWorld world, LivingEntity shooter, Hand hand,
                                  ItemStack weaponStack, ItemStack projectileStack,
                                  int projectileIndex, float speed, float divergence, float yaw,
                                  boolean critical, @Nullable LivingEntity target) {
        return false;
    }

    public void onProjectileLoaded(ItemStack weaponStack, ItemStack projectileStack, LivingEntity shooter) {

    }

    public boolean hasCustomProjectile() {
        return false;
    }

    public ProjectileEntity createProjectileEntity(World world, ItemStack projectileStack, LivingEntity shooter, double x, double v, double z, boolean b) {
        return null;
    }

    public abstract CrossbowItem.ChargeType getChargeType();
}