package com.ellestuff.elles_stuff;

import com.ellestuff.elles_stuff.entities.ChoruslimeEntity;
import com.ellestuff.elles_stuff.items.Magmacicle;
import com.ellestuff.elles_stuff.items.Slimecicle;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.*;
import net.minecraft.entity.effect.*;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.Monster;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.registry.*;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;
import org.quiltmc.qsl.block.extensions.api.QuiltBlockSettings;
import org.quiltmc.qsl.entity.api.QuiltEntityTypeBuilder;
import org.quiltmc.qsl.item.setting.api.QuiltItemSettings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spongepowered.include.com.google.common.collect.ImmutableList;

import java.util.List;

public class EllesStuff implements ModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger("elle's stuff");


	public static final Item SLIME_RESIDUE = new Item(new QuiltItemSettings());
	public static final Item MAGMA_RESIDUE = new Item(new QuiltItemSettings());

	public static final Item SLIMECICLE = new Slimecicle(new QuiltItemSettings());
	public static final Item MAGMACICLE = new Magmacicle(new QuiltItemSettings());

	public static final Block COBBLESTONE_PAVING = new Block(QuiltBlockSettings.copyOf(Blocks.COBBLESTONE));

	//public static final SpawnEggItem CHORUSLIME_SPAWN_EGG = new SpawnEggItem(ChoruslimeEntity.CHORUSLIME_ENTITY, 16382457, 16382457, new Item.Settings().group(ItemGroup.MISC));



	@Override
	public void onInitialize(ModContainer mod) {
		LOGGER.info("Hello Quilt world from {}!", mod.metadata().name());

		{ // Item Registering
			Registry.register(Registries.ITEM, new Identifier(mod.metadata().id(), "slime_residue"), SLIME_RESIDUE);
			Registry.register(Registries.ITEM, new Identifier(mod.metadata().id(), "magma_residue"), MAGMA_RESIDUE);

			Registry.register(Registries.ITEM, new Identifier(mod.metadata().id(), "slimecicle"), SLIMECICLE);
			Registry.register(Registries.ITEM, new Identifier(mod.metadata().id(), "magmacicle"), MAGMACICLE);
		} // Item Registering

		{ // Block Registering
			Registry.register(Registries.BLOCK, new Identifier(mod.metadata().id(), "cobblestone_paving"), COBBLESTONE_PAVING);
			Registry.register(Registries.ITEM, new Identifier(mod.metadata().id(), "cobblestone_paving"), new BlockItem(COBBLESTONE_PAVING, new QuiltItemSettings()));
		} // Block Registering

		{ // Entity Registering

		} // Entity Registering

		{ // Creative menu listings
			ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(content -> {
				content.addAfter(Items.SLIME_BALL, SLIME_RESIDUE);
				content.addAfter(SLIME_RESIDUE, MAGMA_RESIDUE);
			});
			ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINKS).register(content -> {
				content.addAfter(Items.PUMPKIN_PIE, SLIMECICLE);
				content.addAfter(SLIMECICLE, MAGMACICLE);
			});

			ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(content -> {
				content.addBefore(Items.STONE, COBBLESTONE_PAVING.asItem());
			});
		} // Creative menu listings
	}



	/* // Old Choruslime code, don't look (Will delete later)
	public class ChoruslimeEntity extends MobEntity implements Monster {
		public Choruslime(EntityType<? extends Enderslime> entityType, World world) {
			super(entityType, world);
		}
		public static DefaultAttributeContainer.Builder createAttributes() {
			return HostileEntity.createAttributes().add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.20000000298023224);
		}

		public static boolean canChoruslimeSpawn(EntityType<Enderslime> type, WorldAccess world, SpawnReason spawnReason, BlockPos pos, RandomGenerator random) {
			return world.getDifficulty() != Difficulty.PEACEFUL;
		}
		public boolean canSpawn(WorldView world) {
			return world.doesNotIntersectEntities(this) && !world.containsFluid(this.getBoundingBox());
		}

		public void setSize(int size, boolean heal) {
			super.setSize(size, heal);
			this.getAttributeInstance(EntityAttributes.GENERIC_ARMOR).setBaseValue((double)(size * 3));
		}

		public float getLightLevelDependentValue() {
			return 1.0F;
		}

		protected ParticleEffect getParticles() {
			return ParticleTypes.PORTAL;
		}

		public boolean isOnFire() {
			return false;
		}

		protected int getTicksUntilNextJump() {
			return super.getTicksUntilNextJump() * 4;
		}

		protected void updateStretch() {
			this.targetStretch *= 0.9F;
		}

		protected void jump() {
			Vec3d vec3d = this.getVelocity();
			float f = (float)this.getSize() * 0.1F;
			this.setVelocity(vec3d.x, (double)(this.getJumpVelocity() + f), vec3d.z);
			this.velocityDirty = true;
		}

		protected boolean canAttack() {
			return this.isServer();
		}

		protected float getDamageAmount() {
			return super.getDamageAmount() + 2.0F;
		}

	}
	*/ // Old Choruslime code, don't look (Will delete later)



}
