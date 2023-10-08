package com.ellestuff.elles_stuff.entity;

import com.ellestuff.elles_stuff.EllesStuff;
import com.ellestuff.elles_stuff.entity.custom.ChoruslimeEntity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.quiltmc.qsl.entity.api.QuiltEntityTypeBuilder;

public class ModEntities {
	public static final EntityType<ChoruslimeEntity> CHORUSLIME = Registry.register(Registries.ENTITY_TYPE,
		new Identifier(EllesStuff.MOD_ID, "choruslime"),
		QuiltEntityTypeBuilder.create(SpawnGroup.MONSTER, ChoruslimeEntity::new).setDimensions(EntityDimensions.fixed(1f,1f)).build());
}
