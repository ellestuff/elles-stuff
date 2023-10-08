package com.ellestuff.elles_stuff.entity.client;

import com.ellestuff.elles_stuff.EllesStuff;
import com.ellestuff.elles_stuff.entity.custom.ChoruslimeEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.mob.MagmaCubeEntity;
import net.minecraft.entity.mob.SlimeEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;

public class ChoruslimeRenderer extends MobEntityRenderer<ChoruslimeEntity, ChoruslimeModel<ChoruslimeEntity>> {
	private static final Identifier TEXTURE = new Identifier(EllesStuff.MOD_ID,"textures/entity/choruslime/choruslime.png");

	public ChoruslimeRenderer(EntityRendererFactory.Context context) {
		super(context, new ChoruslimeModel<>(context.getPart(ModModelLayers.CHORUSLIME)), 0.25f);
	}
	@Override
	public Identifier getTexture(ChoruslimeEntity entity) {
		return TEXTURE;
	}

	@Override
	public void render(ChoruslimeEntity choruslimeEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
		this.shadowRadius = 0.25F * (float)choruslimeEntity.getSize();
		super.render(choruslimeEntity, f, g, matrixStack, vertexConsumerProvider, i);
	}

	protected void scale(ChoruslimeEntity choruslimeEntity, MatrixStack matrixStack, float f) {
		int i = choruslimeEntity.getSize();
		float g = MathHelper.lerp(f, choruslimeEntity.lastStretch, choruslimeEntity.stretch) / ((float)i * 0.5F + 1.0F);
		float h = 1.0F / (g + 1.0F);
		matrixStack.scale(h * (float)i, 1.0F / h * (float)i, h * (float)i);
	}
}
