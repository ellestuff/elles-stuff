package com.ellestuff.elles_stuff.items;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;

import java.util.List;

public class Chorusicle extends Item {

	public Chorusicle(Settings settings) {
		super(new Item.Settings().food(new FoodComponent.Builder()
				.hunger(1)
				.saturationModifier(0.5f)
				.snack()
				.build())
			.maxCount(16));
	}

	public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
		super.finishUsing(stack, world, user);
		if (stack.isEmpty()) {
			return new ItemStack(Items.STICK);
		} else {
			if (user instanceof PlayerEntity playerEntity && !((PlayerEntity) user).getAbilities().creativeMode) {
				ItemStack itemStack = new ItemStack(Items.STICK);
				if (!playerEntity.getInventory().insertStack(itemStack)) {
					playerEntity.dropItem(itemStack, false);
				}
			}

			return stack;
		}
	}


}
