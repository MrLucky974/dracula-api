package io.github.mrlucky974.dracula_api.api.registry;

import io.github.mrlucky974.dracula_api.api.util.RegistryHelper;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import org.jetbrains.annotations.NotNull;

public abstract class ScreenHandlerRegistry extends BaseRegistry {
    protected static <T extends ScreenHandler> ScreenHandlerType<T> register(String name, ScreenHandlerType.Factory<T> factory) {
        return RegistryHelper.registerScreenHandler(id(name), factory);
    }

    protected static <T extends ScreenHandler, D extends CustomPayload> ExtendedScreenHandlerType<@NotNull T, @NotNull D>
    register(String name,
             ExtendedScreenHandlerType.ExtendedFactory<@NotNull T, @NotNull D> factory,
             PacketCodec<? super RegistryByteBuf, D> codec) {
        return RegistryHelper.registerScreenHandler(id(name), factory, codec);
    }
}
