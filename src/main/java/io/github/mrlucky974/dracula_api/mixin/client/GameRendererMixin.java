package io.github.mrlucky974.dracula_api.mixin.client;

import io.github.mrlucky974.dracula_api.api.client.render.ShaderManager;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.render.RenderTickCounter;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameRenderer.class)
public class GameRendererMixin {
    @Inject(method = "render",
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/client/render/fog/FogRenderer;rotate()V"))
    private void renderProxy(RenderTickCounter tickCounter, boolean tick, CallbackInfo ci) {
        ShaderManager.render(tickCounter);
    }
}