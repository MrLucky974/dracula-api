package io.github.mrlucky974.dracula_api.client;

import io.github.mrlucky974.dracula_api.DraculaAPI;
import io.github.mrlucky974.dracula_api.api.ModEntrypoint;
import io.github.mrlucky974.dracula_api.api.client.ModClientEntrypoint;
import io.github.mrlucky974.dracula_api.api.client.render.ShaderManager;
import io.github.mrlucky974.dracula_api.api.client.render.ShaderReference;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;

public class DraculaAPIClient extends ModClientEntrypoint {
    private static final Identifier BLUE_COLOR_SHIFT_SHADER = Identifier.of(DraculaAPI.MOD_ID, "blue_color_shift");

    public DraculaAPIClient() {
        super(DraculaAPI.MOD_ID);
    }

    @Override
    public void init() {
        ShaderManager.register(BLUE_COLOR_SHIFT_SHADER, ShaderReference::neverRender);
    }

    @Override
    public Logger logger() {
        return ModEntrypoint.getCommon(modId).logger();
    }
}
