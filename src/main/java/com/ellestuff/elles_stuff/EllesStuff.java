package com.ellestuff.elles_stuff;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.registry.*;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.stat.Stats;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;
import org.quiltmc.qsl.item.setting.api.QuiltItemSettings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class EllesStuff implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod name as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger("elle's stuff");

	public static final Item SLIME_RESIDUE = new Item(new QuiltItemSettings());
	public static final Item MAGMA_RESIDUE = new Item(new QuiltItemSettings());
	public static final Item SLIMECICLE = new Slimecicle(new QuiltItemSettings());

	public static final Item MAGMACICLE = new Magmacicle(new QuiltItemSettings());

	@Override
	public void onInitialize(ModContainer mod) {
		LOGGER.info("Hello Quilt world from {}!", mod.metadata().name());

		Registry.register(Registries.ITEM, new Identifier(mod.metadata().id(), "slime_residue"), SLIME_RESIDUE);
		Registry.register(Registries.ITEM, new Identifier(mod.metadata().id(), "magma_residue"), MAGMA_RESIDUE);
		Registry.register(Registries.ITEM, new Identifier(mod.metadata().id(), "slimecicle"), SLIMECICLE);
		Registry.register(Registries.ITEM, new Identifier(mod.metadata().id(), "magmacicle"), MAGMACICLE);

		ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(content -> {
			content.addAfter(Items.SLIME_BALL, SLIME_RESIDUE);
			content.addAfter(SLIME_RESIDUE, MAGMA_RESIDUE);
		});
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINKS).register(content -> {
			content.addAfter(Items.PUMPKIN_PIE, SLIMECICLE);
		});
	}

	public static class Slimecicle extends Item {

		public Slimecicle(Settings settings) {
			super(new Item.Settings().food(new FoodComponent.Builder()
				.hunger(2)
				.saturationModifier(0.25f)
				.snack()
				.build()));
		}

		@Override
		public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext) {
			super.appendTooltip(itemStack, world, tooltip, tooltipContext);
			tooltip.add(Text.translatable("item.elles_stuff.slimecicle.tooltip").formatted(Formatting.GRAY));
			tooltip.add(Text.translatable("item.elles_stuff.slimecicle.tooltip2").formatted(Formatting.DARK_GRAY, Formatting.ITALIC));
		}

		public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
			super.finishUsing(stack, world, user);
			if (stack.isEmpty()) {
				return new ItemStack(Items.STICK);
			} else {
				if (user instanceof PlayerEntity && !((PlayerEntity)user).getAbilities().creativeMode) {
					ItemStack itemStack = new ItemStack(Items.STICK);
					PlayerEntity playerEntity = (PlayerEntity)user;
					if (!playerEntity.getInventory().insertStack(itemStack)) {
						playerEntity.dropItem(itemStack, false);
					}
				}

				return stack;
			}
		}
	}

	public static class Magmacicle extends Item {

		public Magmacicle(Settings settings) {
			super(new Item.Settings().food(new FoodComponent.Builder()
				.hunger(1)
				.saturationModifier(0.5f)
				.snack()
				.statusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 60, 0), 1.0f)
				.build()));
		}

		@Override
		public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext) {
			super.appendTooltip(itemStack, world, tooltip, tooltipContext);
			tooltip.add(Text.translatable("item.elles_stuff.magmacicle.tooltip").formatted(Formatting.GRAY));
		}

		public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
			super.finishUsing(stack, world, user);
			if (stack.isEmpty()) {
				return new ItemStack(Items.STICK);
			} else {
				if (user instanceof PlayerEntity && !((PlayerEntity)user).getAbilities().creativeMode) {
					ItemStack itemStack = new ItemStack(Items.STICK);
					PlayerEntity playerEntity = (PlayerEntity)user;
					if (!playerEntity.getInventory().insertStack(itemStack)) {
						playerEntity.dropItem(itemStack, false);
					}
				}

				return stack;
			}
		}
	}

}

