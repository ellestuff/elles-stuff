package com.ellestuff.elles_stuff.entity.custom;

import com.ellestuff.elles_stuff.entity.ModEntities;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.EndermanEntity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.SlimeEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;

public class ChoruslimeEntity extends SlimeEntity {
	private boolean isJumping = false;

	private double groundDist = 0;

	public ChoruslimeEntity(EntityType<? extends SlimeEntity> entityType, World world) {
		super(entityType,world);
	}

	public static DefaultAttributeContainer.Builder createChoruslimeAttributes() {
		return HostileEntity.createAttributes().add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.20000000298023224);
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

	protected int getTicksUntilNextJump() {
		return super.getTicksUntilNextJump() * 2;
	}

	protected void updateStretch() {
		this.targetStretch *= 0.9F;
	}

	protected void jump() {
		isJumping = true;
		groundDist = 0;
		super.jump();
	}

	protected boolean canAttack() {
		return this.isServer();
	}

	public boolean hurtByWater() {
		return true;
	}

	protected float getDamageAmount() {
		return super.getDamageAmount() + 2.0F;
	}

	private boolean teleportTo(double x, double y, double z) {
		BlockPos.Mutable mutable = new BlockPos.Mutable(x, y, z);

		while(mutable.getY() > this.getWorld().getBottomY() && !this.getWorld().getBlockState(mutable).blocksMovement()) {
			mutable.move(Direction.DOWN);
		}

		BlockState blockState = this.getWorld().getBlockState(mutable);
		boolean bl = blockState.blocksMovement();
		boolean bl2 = blockState.getFluidState().isIn(FluidTags.WATER);
		if (bl && !bl2) {
			Vec3d vec3d = this.getPos();
			boolean bl3 = this.teleport(x, y + groundDist, z, true);
			if (bl3) {
				this.getWorld().emitGameEvent(GameEvent.TELEPORT, vec3d, GameEvent.Context.create(this));
				if (!this.isSilent()) {
					this.getWorld().playSound((PlayerEntity)null, this.prevX, this.prevY, this.prevZ, SoundEvents.ENTITY_ENDERMAN_TELEPORT, this.getSoundCategory(), 1.0F, 1.0F);
					this.playSound(SoundEvents.ENTITY_ENDERMAN_TELEPORT, 1.0F, 2F);
				}
			}

			return bl3;
		} else {
			return false;
		}
	}

	protected boolean teleportRandomly() {
		if (!this.getWorld().isClient() && this.isAlive()) {
			double d = this.getX() + (this.random.nextDouble() - 0.5) * 32;
			double e = this.getY() + (double)(this.random.nextInt(32) - 32);
			double f = this.getZ() + (this.random.nextDouble() - 0.5) * 32;
			return this.teleportTo(d, e, f);
		} else {
			return false;
		}
	}

	@Override
	protected void mobTick() {
		if (isJumping) {
			double yVel = this.getVelocity().y;
			groundDist += yVel;

			if (yVel < 0) {
				isJumping = false;
				teleportRandomly();
				groundDist = 0;
			}
		}
	}
}
