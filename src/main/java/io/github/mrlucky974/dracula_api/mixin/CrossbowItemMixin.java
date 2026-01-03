package io.github.mrlucky974.dracula_api.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import io.github.mrlucky974.dracula_api.api.item.CrossbowProjectileItem;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.function.Predicate;

@Mixin(CrossbowItem.class)
public abstract class CrossbowItemMixin {
    @ModifyReturnValue(
            method = "getHeldProjectiles",
            at = @At("RETURN")
    )
    private static Predicate<ItemStack> draculaAPI$allowCustomAmmo(
            Predicate<ItemStack> original
    ) {
        return original.or(itemStack ->
                itemStack.getItem() instanceof CrossbowProjectileItem);
    }

    @Inject(
            method = "createArrowEntity",
            at = @At("HEAD"),
            cancellable = true)
    private static void draculaAPI$shootCustomProjectileEntity(
            World world,
            LivingEntity shooter,
            ItemStack weaponStack,
            ItemStack projectileStack,
            boolean critical,
            CallbackInfoReturnable<ProjectileEntity> cir) {
        if (projectileStack.getItem() instanceof CrossbowProjectileItem projectileItem) {
            if (projectileItem.hasCustomProjectile()) {
                cir.setReturnValue(projectileItem.createProjectileEntity(world, projectileStack, shooter, shooter.getX(), shooter.getEyeY() - 0.15F, shooter.getZ(), true));
            }
        }
    }
}
