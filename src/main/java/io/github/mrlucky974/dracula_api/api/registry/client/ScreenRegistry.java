package io.github.mrlucky974.dracula_api.api.registry.client;

import io.github.mrlucky974.dracula_api.api.client.ModClientEntrypoint;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.gui.screen.ingame.ScreenHandlerProvider;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;

public abstract class ScreenRegistry extends BaseClientRegistry {
    public ScreenRegistry(ModClientEntrypoint modEntrypoint) {
        super(modEntrypoint);
    }

    protected final <M extends ScreenHandler, U extends Screen & ScreenHandlerProvider<M>> void register(ScreenHandlerType<? extends M> screenHandlerType, HandledScreens.Provider<M, U> screenHandler) {
        HandledScreens.register(screenHandlerType, screenHandler);
    }
}
