package com.ellestuff.elles_stuff;

import com.ellestuff.elles_stuff.entities.ChoruslimeEntity;
import com.ellestuff.elles_stuff.models.ChoruslimeEntityModel;
import com.ellestuff.elles_stuff.renderers.ChoruslimeEntityRenderer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.client.ClientModInitializer;
import org.quiltmc.qsl.entity.api.QuiltEntityTypeBuilder;

@Environment(EnvType.CLIENT)
public class ClientStuff implements ClientModInitializer {
	@Override
	public void onInitializeClient(ModContainer mod) {
		EntityModelLayerRegistry.registerModelLayer(ChoruslimeEntityModel.CHORUSLIME_MODEL_LAYER, ChoruslimeEntityModel::getTexturedModelData);


		EntityRendererRegistry.register(ChoruslimeEntity.CHORUSLIME_ENTITY, ((ChoruslimeEntityRenderer::new)));
	}
}
