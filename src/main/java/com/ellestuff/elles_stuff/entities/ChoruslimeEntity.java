package com.ellestuff.elles_stuff.entities;

import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.Monster;
import net.minecraft.entity.mob.SlimeEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.random.RandomGenerator;
import net.minecraft.world.Difficulty;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import org.quiltmc.qsl.entity.api.QuiltEntityTypeBuilder;

public class ChoruslimeEntity extends SlimeEntity implements Monster {
	public ChoruslimeEntity(EntityType<? extends SlimeEntity> entityType, World world) {super(entityType, world);}

	public static final EntityType<ChoruslimeEntity> CHORUSLIME_ENTITY = Registry.register(Registries.ENTITY_TYPE, new Identifier("elles_stuff", "choruslime"), QuiltEntityTypeBuilder.create(SpawnGroup.MONSTER, ChoruslimeEntity::new).setDimensions(EntityDimensions.changing(0.5F, 1.0F)).build());

	public static DefaultAttributeContainer.Builder createAttributes() {
		return HostileEntity.createAttributes().add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.20000000298023224);
	}

	public static boolean canChoruslimeSpawn(EntityType<ChoruslimeEntity> type, WorldAccess world, SpawnReason spawnReason, BlockPos pos, RandomGenerator random) {
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
		return ParticleTypes.FLAME;
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

	protected void swimUpward(TagKey<Fluid> tag) {
		if (tag == FluidTags.LAVA) {
			Vec3d vec3d = this.getVelocity();
			this.setVelocity(vec3d.x, (double)(0.22F + (float)this.getSize() * 0.05F), vec3d.z);
			this.velocityDirty = true;
		} else {
			super.swimUpward(tag);
		}

	}

	protected boolean canAttack() {
		return this.isServer();
	}

	protected float getDamageAmount() {
		return super.getDamageAmount() + 2.0F;
	}

	protected SoundEvent getHurtSound(DamageSource source) {
		return this.isSmall() ? SoundEvents.ENTITY_MAGMA_CUBE_HURT_SMALL : SoundEvents.ENTITY_MAGMA_CUBE_HURT;
	}

	protected SoundEvent getDeathSound() {
		return this.isSmall() ? SoundEvents.ENTITY_MAGMA_CUBE_DEATH_SMALL : SoundEvents.ENTITY_MAGMA_CUBE_DEATH;
	}

	protected SoundEvent getSquishSound() {
		return this.isSmall() ? SoundEvents.ENTITY_MAGMA_CUBE_SQUISH_SMALL : SoundEvents.ENTITY_MAGMA_CUBE_SQUISH;
	}

	protected SoundEvent getJumpSound() {
		return SoundEvents.ENTITY_MAGMA_CUBE_JUMP;
	}
}
