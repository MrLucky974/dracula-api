package io.github.mrlucky974.dracula_api.api.client.render;

import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.RenderTickCounter;

@FunctionalInterface
public interface RenderCondition {
    boolean shouldRender(Context context);

    record Context(ClientPlayerEntity player, RenderTickCounter tickCounter) {
    }
}