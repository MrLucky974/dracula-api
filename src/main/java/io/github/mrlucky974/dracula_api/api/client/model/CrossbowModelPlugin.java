package io.github.mrlucky974.dracula_api.api.client.model;

import io.github.mrlucky974.dracula_api.api.item.CrossbowChargeType;
import io.github.mrlucky974.dracula_api.api.item.CrossbowChargeTypeBootstrap;
import net.fabricmc.fabric.api.client.model.loading.v1.ExtraModelKey;
import net.fabricmc.fabric.api.client.model.loading.v1.ModelLoadingPlugin;
import net.fabricmc.fabric.api.client.model.loading.v1.ModelModifier;
import net.fabricmc.fabric.api.client.model.loading.v1.SimpleUnbakedExtraModel;
import net.minecraft.client.data.ItemModels;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.item.model.BasicItemModel;
import net.minecraft.client.render.item.model.ItemModel;
import net.minecraft.client.render.item.model.SelectItemModel;
import net.minecraft.client.render.item.property.select.ChargeTypeProperty;
import net.minecraft.client.render.model.BakedQuad;
import net.minecraft.client.render.model.ModelRotation;
import net.minecraft.client.render.model.ModelSettings;
import net.minecraft.client.render.model.ModelTextures;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import org.jspecify.annotations.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

public class CrossbowModelPlugin implements ModelLoadingPlugin, ModelModifier.AfterBakeItem {
    private static final Logger LOGGER = LoggerFactory.getLogger("Test");

    private CrossbowModelPlugin() {}

    public static void register() {
        ModelLoadingPlugin.register(new CrossbowModelPlugin());
    }

    @Override
    public void initialize(ModelLoadingPlugin.@NonNull Context pluginContext) {
        List<CrossbowChargeType> values = CrossbowChargeTypeBootstrap.values();

        for (CrossbowChargeType type : values) {
            Identifier modelId = getModelId(type);
            SimpleUnbakedExtraModel<ItemModel> model = new SimpleUnbakedExtraModel<>(modelId, (bakedSimpleModel, baker) -> {
                ModelTextures modelTextures = bakedSimpleModel.getTextures();
                List<BakedQuad> list = bakedSimpleModel.bakeGeometry(modelTextures, baker,
                        ModelRotation.IDENTITY).getAllQuads();
                ModelSettings modelSettings = ModelSettings.resolveSettings(baker,
                        bakedSimpleModel, modelTextures);
                Function<ItemStack, RenderLayer> function = BasicItemModel.findRenderLayerGetter(list);
                return new BasicItemModel(Collections.emptyList(), list, modelSettings, function);
            });

            pluginContext.addModel(ExtraModelKey.create(modelId::toString), model);
        }

        pluginContext.modifyItemModelAfterBake()
                .register(this);
    }

    private static Identifier getModelId(CrossbowChargeType type) {
        return type.getIdentifier().withPrefixedPath("item/crossbow_");
    }

    @Override
    public @NonNull ItemModel modifyModelAfterBake(@NonNull ItemModel model,
                                                   ModelModifier.AfterBakeItem.Context context) {
        if (context.itemId().equals(Registries.ITEM.getId(Items.CROSSBOW))) {
            List<CrossbowChargeType> values = CrossbowChargeTypeBootstrap.values();

            List<SelectItemModel.SwitchCase<CrossbowItem.ChargeType>> cases = new ArrayList<>();

            for (CrossbowChargeType type : values) {
                Identifier modelId = getModelId(type);
                ItemModel.Unbaked typeUnbakedModel = ItemModels.basic(modelId);
                cases.add(ItemModels.switchCase(type.asEnumValue(), typeUnbakedModel));
            }

            ItemModel.Unbaked newModel = ItemModels.select(
                    new ChargeTypeProperty(),
                    context.sourceModel(),
                    cases
            );

            return newModel.bake(context.bakeContext());
        }

        return model;
    }
}
