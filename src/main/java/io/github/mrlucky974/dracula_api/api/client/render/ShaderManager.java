package io.github.mrlucky974.dracula_api.api.client.render;

import com.google.gson.JsonSyntaxException;
import io.github.mrlucky974.dracula_api.DraculaAPI;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gl.Framebuffer;
import net.minecraft.client.gl.PostEffectProcessor;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.DefaultFramebufferSet;
import net.minecraft.client.render.FrameGraphBuilder;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.client.util.ObjectAllocator;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ShaderManager {
    private static final MinecraftClient CLIENT = MinecraftClient.getInstance();
    private static final ObjectAllocator ALLOCATOR = ObjectAllocator.TRIVIAL;
    private static final Map<Identifier, ShaderReference> SHADER_REFERENCES = new LinkedHashMap<>();
    private static final List<ShaderInstance> ACTIVE_SHADERS = new ArrayList<>();

    public static void render(RenderTickCounter tickCounter) {
        ClientPlayerEntity player = CLIENT.player;
        if (player == null) {
            return;
        }

        fillActiveShaders(player, tickCounter);

        if (ACTIVE_SHADERS.isEmpty()) {
            return;
        }

        for (ShaderInstance shader : ACTIVE_SHADERS) {
            shader.render(ALLOCATOR, CLIENT);
        }
    }

    public static boolean register(String namespace, String name) {
        return register(ShaderReference.create(namespace, name));
    }

    public static boolean register(String namespace, String name, RenderCondition renderCondition) {
        return register(ShaderReference.create(namespace, name, renderCondition));
    }

    public static boolean register(Identifier identifier) {
        return register(ShaderReference.create(identifier));
    }

    public static boolean register(Identifier identifier, RenderCondition renderCondition) {
        return register(ShaderReference.create(identifier, renderCondition));
    }

    public static boolean register(ShaderReference shaderReference) {
        if (SHADER_REFERENCES.containsKey(shaderReference.id())) {
            return false;
        }
        SHADER_REFERENCES.put(shaderReference.id(), shaderReference);
        return true;
    }

    private static PostEffectProcessor createShaderGroup(ShaderReference shaderReference) {
        Identifier shaderId = shaderReference.id();
        try {
            return CLIENT.getShaderLoader().loadPostEffect(shaderId, DefaultFramebufferSet.MAIN_ONLY);
        } catch (JsonSyntaxException jsonSyntaxException) {
            DraculaAPI.LOGGER.warn("Failed to parse shader: {}", shaderId, jsonSyntaxException);
        }
        return null;
    }

    private static void fillActiveShaders(ClientPlayerEntity player, RenderTickCounter tickCounter) {
        ACTIVE_SHADERS.clear();

        for (ShaderReference shaderReference : SHADER_REFERENCES.values()) {
            if (shaderReference.shouldRender(player, tickCounter)) {
                registerShaderInstance(shaderReference);
            }
        }
    }

    private static void registerShaderInstance(ShaderReference shaderReference) {
        ShaderInstance instance = createShaderInstance(shaderReference);
        ACTIVE_SHADERS.add(instance);
    }

    private static ShaderInstance createShaderInstance(ShaderReference shaderReference) {
        PostEffectProcessor shaderGroup = createShaderGroup(shaderReference);
        return new ShaderInstance(shaderGroup);
    }

    private record ShaderInstance(PostEffectProcessor postEffectProcessor) {
        public void render(ObjectAllocator allocator, MinecraftClient client) {
            if (postEffectProcessor != null) {
                Framebuffer framebuffer = client.getFramebuffer();
                FrameGraphBuilder frameGraphBuilder = new FrameGraphBuilder();
                PostEffectProcessor.FramebufferSet framebufferSet = PostEffectProcessor.FramebufferSet.singleton(PostEffectProcessor.MAIN, frameGraphBuilder.createObjectNode("main", framebuffer));
                postEffectProcessor.render(frameGraphBuilder, framebuffer.textureWidth, framebuffer.textureHeight, framebufferSet);
                frameGraphBuilder.run(allocator);
            }
        }
    }
}
