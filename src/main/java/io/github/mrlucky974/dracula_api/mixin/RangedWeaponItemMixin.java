package io.github.mrlucky974.dracula_api.mixin;

import io.github.mrlucky974.dracula_api.api.item.CrossbowProjectileItem;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.RangedWeaponItem;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.jspecify.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(RangedWeaponItem.class)
public abstract class RangedWeaponItemMixin {
    @Shadow
    protected abstract ProjectileEntity createArrowEntity(World world, LivingEntity shooter, ItemStack weaponStack, ItemStack projectileStack, boolean critical);

    @Shadow
    protected abstract int getWeaponStackDamage(ItemStack projectile);

    @Shadow
    protected abstract void shoot(LivingEntity var1, ProjectileEntity var2, int var3, float var4, float var5, float var6, @Nullable LivingEntity var7);

    @Inject(
            method = "shootAll",
            at = @At(value = "HEAD"),
            cancellable = true
    )
    private void draculaAPI$conditionalSpawn(ServerWorld world, LivingEntity shooter, Hand hand, ItemStack stack, List<ItemStack> projectiles, float speed, float divergence, boolean critical, @Nullable LivingEntity target, CallbackInfo ci) {
        float f = EnchantmentHelper.getProjectileSpread(world, stack, shooter, 0.0f);
        float g = projectiles.size() == 1 ? 0.0f : 2.0f * f / (float)(projectiles.size() - 1);
        float h = (float)((projectiles.size() - 1) % 2) * g / 2.0f;
        float i = 1.0f;
        for (int projectileIndex = 0; projectileIndex < projectiles.size(); ++projectileIndex) {
            ItemStack itemStack = projectiles.get(projectileIndex);
            if (itemStack.isEmpty()) continue;
            float yaw = h + i * (float)((projectileIndex + 1) / 2) * g;
            i = -i;
            if (shootProjectile(world, shooter, hand, stack, itemStack, projectileIndex, speed, divergence, yaw, critical, target)) {
                break;
            }
        }
        ci.cancel();
    }

    @Inject(
            method = "load",
            at = @At(value = "TAIL")
    )
    private static void draculaAPI$onProjectileLoad(ItemStack stack, ItemStack projectileStack, LivingEntity shooter, CallbackInfoReturnable<List<ItemStack>> cir) {
        if (projectileStack.getItem() instanceof CrossbowProjectileItem crossbowProjectileItem) {
            crossbowProjectileItem.onProjectileLoaded(stack, projectileStack, shooter);
        }
    }

    @Unique
    private boolean shootProjectile(ServerWorld world, LivingEntity shooter, Hand hand,
                                    ItemStack weaponStack, ItemStack projectileStack,
                                    int projectileIndex, float speed, float divergence, float yaw,
                                    boolean critical, @Nullable LivingEntity target) {
        boolean shouldProjectile = true;

        if (projectileStack.getItem() instanceof CrossbowProjectileItem crossbowProjectileItem) {
            shouldProjectile = !crossbowProjectileItem.shoot(world, shooter, hand, weaponStack,
                    projectileStack, projectileIndex, speed, divergence, yaw, critical, target);
        }

        if (shouldProjectile) {
            ProjectileEntity.spawn(createArrowEntity(world, shooter, weaponStack, projectileStack,
                            critical), world, projectileStack,
                    projectile -> shoot(shooter, projectile, projectileIndex, speed,
                            divergence, yaw, target));
        }

        weaponStack.damage(getWeaponStackDamage(projectileStack), shooter, hand.getEquipmentSlot());
        return weaponStack.isEmpty();
    }
}