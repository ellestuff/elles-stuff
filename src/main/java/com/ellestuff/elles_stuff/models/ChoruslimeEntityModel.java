package com.ellestuff.elles_stuff.models;

import com.ellestuff.elles_stuff.entities.ChoruslimeEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.util.Identifier;

public class ChoruslimeEntityModel<T extends ChoruslimeEntity> extends SinglePartEntityModel<T> {

	public static final EntityModelLayer CHORUSLIME_MODEL_LAYER = new EntityModelLayer(new Identifier("elles_stuff", "choruslime_entity_model"), "choruslime_entity_model");
	private final ModelPart root;

	public ChoruslimeEntityModel(ModelPart root) {
		this.root = root;
	}

	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		modelPartData.addChild("cube", ModelPartBuilder.create().uv(0, 0).cuboid(-4.0F, 16.0F, -4.0F, 8.0F, 8.0F, 8.0F), ModelTransform.NONE);
		return TexturedModelData.of(modelData, 64, 32);
	}

	public static TexturedModelData getInnerTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		modelPartData.addChild("cube", ModelPartBuilder.create().uv(0, 16).cuboid(-3.0F, 17.0F, -3.0F, 6.0F, 6.0F, 6.0F), ModelTransform.NONE);
		modelPartData.addChild("right_eye", ModelPartBuilder.create().uv(32, 0).cuboid(-3.25F, 18.0F, -3.5F, 2.0F, 2.0F, 2.0F), ModelTransform.NONE);
		modelPartData.addChild("left_eye", ModelPartBuilder.create().uv(32, 4).cuboid(1.25F, 18.0F, -3.5F, 2.0F, 2.0F, 2.0F), ModelTransform.NONE);
		modelPartData.addChild("mouth", ModelPartBuilder.create().uv(32, 8).cuboid(0.0F, 21.0F, -3.5F, 1.0F, 1.0F, 1.0F), ModelTransform.NONE);
		return TexturedModelData.of(modelData, 64, 32);
	}

	public void setAngles(T entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
	}

	public ModelPart getPart() {
		return this.root;
	}
}
