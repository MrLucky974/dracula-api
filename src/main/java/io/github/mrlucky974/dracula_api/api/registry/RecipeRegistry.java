package io.github.mrlucky974.dracula_api.api.registry;

import io.github.mrlucky974.dracula_api.api.ModEntrypoint;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public abstract class RecipeRegistry extends BaseRegistry {
    private final TypeRegistry typeRegistry;
    private final SerializerRegistry serializerRegistry;

    public RecipeRegistry(ModEntrypoint modEntrypoint) {
        super(modEntrypoint);
        this.typeRegistry = new TypeRegistry(modEntrypoint);
        this.serializerRegistry = new SerializerRegistry(modEntrypoint);
    }

    @Override
    public void init() {
        typeRegistry.init();
        serializerRegistry.init();
    }

//    protected final <T extends Recipe<?>, U extends RecipeType<? extends T>, I extends RecipeSerializer<? extends T>> void registerRecipe(String name, U type, I serializer) {
//
//    }

    protected final <T extends RecipeType<?>> T registerType(String name, T type) {
        return typeRegistry.register(name, type);
    }

    protected final <T extends RecipeSerializer<?>> T registerSerializer(String name, T serializer) {
        return serializerRegistry.register(name, serializer);
    }

    protected static final class TypeRegistry extends BaseRegistry {
        public TypeRegistry(ModEntrypoint modEntrypoint) {
            super(modEntrypoint);
        }

        @Override
        public void init() {
            modEntrypoint.logger().info("Registering recipe types...");
        }

        protected <T extends RecipeType<?>> T register(String name, T type) {
            return RegistryHelper.registerRecipeType(modEntrypoint.id(name), type);
        }
    }

    protected static final class SerializerRegistry extends BaseRegistry {
        public SerializerRegistry(ModEntrypoint modEntrypoint) {
            super(modEntrypoint);
        }

        @Override
        public void init() {
            modEntrypoint.logger().info("Registering recipe serializers...");
        }

        protected <T extends RecipeSerializer<?>> T register(String name, T serializer) {
            return RegistryHelper.registerRecipeSerializer(modEntrypoint.id(name), serializer);
        }
    }
}
