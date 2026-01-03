package io.github.mrlucky974.dracula_api.mixin.client;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import io.github.mrlucky974.dracula_api.api.item.CrossbowProjectileItem;
import net.minecraft.client.render.item.property.select.ChargeTypeProperty;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ChargedProjectilesComponent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemDisplayContext;
import net.minecraft.item.ItemStack;
import org.jspecify.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ChargeTypeProperty.class)
public class ChargeTypePropertyMixin {
    @ModifyReturnValue(
            method = "getValue(Lnet/minecraft/item/ItemStack;Lnet/minecraft/client/world/ClientWorld;Lnet/minecraft/entity/LivingEntity;ILnet/minecraft/item/ItemDisplayContext;)Lnet/minecraft/item/CrossbowItem$ChargeType;",
            at = @At("RETURN")
    )
    private CrossbowItem.ChargeType draculaAPI$useProjectileChargeType(
            CrossbowItem.ChargeType original,
            ItemStack stack,
            @Nullable ClientWorld world,
            @Nullable LivingEntity entity,
            int seed,
            ItemDisplayContext context
    ) {
        ChargedProjectilesComponent charged =
                stack.get(DataComponentTypes.CHARGED_PROJECTILES);

        if (charged == null || charged.isEmpty()) {
            return original;
        }

        ItemStack projectileStack = charged.getProjectiles().getFirst();
        Item item = projectileStack.getItem();

        if (item instanceof CrossbowProjectileItem projectile) {
            return projectile.getChargeType();
        }

        return original;
    }
}
