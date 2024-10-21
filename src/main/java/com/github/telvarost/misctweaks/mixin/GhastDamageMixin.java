package com.github.telvarost.misctweaks.mixin;

import com.github.telvarost.misctweaks.Config;
import net.minecraft.entity.Entity;
import net.minecraft.entity.FlyingEntity;
import net.minecraft.entity.Monster;
import net.minecraft.entity.mob.GhastEntity;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(GhastEntity.class)
public class GhastDamageMixin extends FlyingEntity implements Monster {

    public GhastDamageMixin(World arg) {
        super(arg);
        this.texture = "/mob/ghast.png";
        this.setBoundingBoxSpacing(4.0F, 4.0F);
        this.fireImmune = true;
    }

    @Override
    public boolean damage(Entity arg, int i) {
        if (this.world.isRemote) {
            return false;
        } else {
            this.despawnCounter = 0;
            if (this.health <= 0) {
                return false;
            } else {
                this.walkAnimationSpeed = 1.5F;
                boolean var3 = true;

                if (Config.config.enableGhastFireballsToInstaKillGhasts) {
                    if (arg == null || arg instanceof GhastEntity || arg instanceof FireballEntity) {
                        i = 20;
                    }
                }

                if ((float)this.hearts > (float)this.maxHealth / 2.0F) {
                    if (i <= this.prevHealth) {
                        return false;
                    }

                    this.applyDamage(i - this.prevHealth);
                    this.prevHealth = i;
                    var3 = false;
                } else {
                    this.prevHealth = i;
                    this.lastHealth = this.health;
                    this.hearts = this.maxHealth;
                    this.applyDamage(i);
                    this.hurtTime = this.damagedTime = 10;
                }

                this.damagedSwingDir = 0.0F;
                if (var3) {
                    this.world.broadcastEntityEvent(this, (byte)2);
                    this.scheduleVelocityUpdate();
                    if (arg != null) {
                        double var4 = arg.x - this.x;

                        double var6;
                        for(var6 = arg.z - this.z; var4 * var4 + var6 * var6 < 1.0E-4; var6 = (Math.random() - Math.random()) * 0.01) {
                            var4 = (Math.random() - Math.random()) * 0.01;
                        }

                        this.damagedSwingDir = (float)(Math.atan2(var6, var4) * 180.0 / 3.1415927410125732) - this.yaw;
                        this.applyKnockback(arg, i, var4, var6);
                    } else {
                        this.damagedSwingDir = (float)((int)(Math.random() * 2.0) * 180);
                    }
                }

                if (this.health <= 0) {
                    if (var3) {
                        this.world.playSound(this, this.getDeathSound(), this.getSoundVolume(), (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);
                    }

                    this.onKilledBy(arg);
                } else if (var3) {
                    this.world.playSound(this, this.getHurtSound(), this.getSoundVolume(), (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);
                }

                return true;
            }
        }
    }
}
