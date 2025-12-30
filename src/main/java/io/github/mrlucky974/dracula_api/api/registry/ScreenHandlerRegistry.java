package io.github.mrlucky974.dracula_api.api.registry;

import io.github.mrlucky974.dracula_api.api.ModEntrypoint;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.resource.featuretoggle.FeatureSet;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;

public abstract class ScreenHandlerRegistry extends BaseRegistry {
    public ScreenHandlerRegistry(ModEntrypoint modEntrypoint) {
        super(modEntrypoint);
    }

    protected final <T extends ScreenHandler> ScreenHandlerType<T> register(String name, ScreenHandlerType.Factory<T> factory) {
        return RegistryHelper.registerScreenHandler(modEntrypoint.id(name), factory);
    }

    protected final <T extends ScreenHandler, D extends CustomPayload> ExtendedScreenHandlerType<T, D>
    register(String name,
             ExtendedScreenHandlerType.ExtendedFactory<T, D> factory,
             PacketCodec<? super RegistryByteBuf, D> codec) {
        return RegistryHelper.registerScreenHandler(modEntrypoint.id(name), factory, codec);
    }
}
