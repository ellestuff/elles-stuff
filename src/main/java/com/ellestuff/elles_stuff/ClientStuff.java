package com.ellestuff.elles_stuff;

import com.ellestuff.elles_stuff.entity.ModEntities;
import com.ellestuff.elles_stuff.entity.client.ChoruslimeModel;
import com.ellestuff.elles_stuff.entity.client.ChoruslimeRenderer;
import com.ellestuff.elles_stuff.entity.client.ModModelLayers;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.client.ClientModInitializer;

public class ClientStuff implements ClientModInitializer {
	@Override
	public void onInitializeClient(ModContainer mod) {
		EntityRendererRegistry.register(ModEntities.CHORUSLIME, ChoruslimeRenderer::new);
		EntityModelLayerRegistry.registerModelLayer(ModModelLayers.CHORUSLIME, ChoruslimeModel::getInnerTexturedModelData);
	}
}
