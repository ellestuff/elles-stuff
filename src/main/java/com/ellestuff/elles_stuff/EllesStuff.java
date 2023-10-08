package com.ellestuff.elles_stuff;

import com.ellestuff.elles_stuff.entity.ModEntities;
import com.ellestuff.elles_stuff.entity.custom.ChoruslimeEntity;
import com.ellestuff.elles_stuff.items.Chorusicle;
import com.ellestuff.elles_stuff.items.Magmacicle;
import com.ellestuff.elles_stuff.items.Slimecicle;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.attribute.DefaultAttributeRegistry;
import net.minecraft.item.*;
import net.minecraft.registry.*;
import net.minecraft.util.Identifier;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;
import org.quiltmc.qsl.block.extensions.api.QuiltBlockSettings;
import org.quiltmc.qsl.item.setting.api.QuiltItemSettings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EllesStuff implements ModInitializer {
	public static final String MOD_ID = "elles_stuff";
	public static final Logger LOGGER = LoggerFactory.getLogger("elle's stuff");

	public static final Item CHORUSLIME_CHUNK = new Item(new QuiltItemSettings());

	public static final Item SLIME_RESIDUE = new Item(new QuiltItemSettings());
	public static final Item MAGMA_RESIDUE = new Item(new QuiltItemSettings());
	public static final Item CHORUSLIME_RESIDUE = new Item(new QuiltItemSettings());

	public static final Item SLIMECICLE = new Slimecicle(new QuiltItemSettings());
	public static final Item MAGMACICLE = new Magmacicle(new QuiltItemSettings());
	public static final Item CHORUSICLE = new Chorusicle(new QuiltItemSettings());

	public static final Block COBBLESTONE_PAVING = new Block(QuiltBlockSettings.copyOf(Blocks.COBBLESTONE));

	@Override
	public void onInitialize(ModContainer mod) {
		LOGGER.info("Hello Quilt world from {}!", mod.metadata().name());

		{ // Item Registering
			Registry.register(Registries.ITEM, new Identifier(mod.metadata().id(), "choruslime_chunk"), CHORUSLIME_CHUNK);

			Registry.register(Registries.ITEM, new Identifier(mod.metadata().id(), "slime_residue"), SLIME_RESIDUE);
			Registry.register(Registries.ITEM, new Identifier(mod.metadata().id(), "magma_residue"), MAGMA_RESIDUE);
			Registry.register(Registries.ITEM, new Identifier(mod.metadata().id(), "choruslime_residue"), CHORUSLIME_RESIDUE);

			Registry.register(Registries.ITEM, new Identifier(mod.metadata().id(), "slimecicle"), SLIMECICLE);
			Registry.register(Registries.ITEM, new Identifier(mod.metadata().id(), "magmacicle"), MAGMACICLE);
			Registry.register(Registries.ITEM, new Identifier(mod.metadata().id(), "chorusicle"), CHORUSICLE);
		} // Item Registering

		{ // Block Registering
			Registry.register(Registries.BLOCK, new Identifier(mod.metadata().id(), "cobblestone_paving"), COBBLESTONE_PAVING);
			Registry.register(Registries.ITEM, new Identifier(mod.metadata().id(), "cobblestone_paving"), new BlockItem(COBBLESTONE_PAVING, new QuiltItemSettings()));
		} // Block Registering

		{ // Entity Registering
			// Choruslime
			DefaultAttributeRegistry.DEFAULT_ATTRIBUTE_REGISTRY.put(ModEntities.CHORUSLIME, ChoruslimeEntity.createChoruslimeAttributes().build());
			Registry.register(Registries.ITEM, new Identifier(mod.metadata().id(), "choruslime_spawn_egg"), // Spawn Egg
				new SpawnEggItem(ModEntities.CHORUSLIME, 0xD2668C, 0x5B345B, new QuiltItemSettings()));
		} // Entity Registering

		{ // Creative menu listings
			ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(content -> {
				content.addAfter(Items.SLIME_BALL, CHORUSLIME_CHUNK);

				content.addAfter(CHORUSLIME_CHUNK, SLIME_RESIDUE);
				content.addAfter(SLIME_RESIDUE, MAGMA_RESIDUE);
				content.addAfter(MAGMA_RESIDUE, CHORUSLIME_RESIDUE);
			});
			ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINKS).register(content -> {
				content.addAfter(Items.PUMPKIN_PIE, SLIMECICLE);
				content.addAfter(SLIMECICLE, MAGMACICLE);
				content.addAfter(MAGMACICLE, CHORUSICLE);
			});

			ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(content -> {
				content.addBefore(Items.STONE, COBBLESTONE_PAVING.asItem());
			});
		} // Creative menu listings
	}



}
