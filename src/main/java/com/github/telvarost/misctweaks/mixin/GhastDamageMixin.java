package com.github.telvarost.misctweaks.mixin;

import com.github.telvarost.misctweaks.Config;
import net.minecraft.entity.EntityBase;
import net.minecraft.entity.living.FlyingBase;
import net.minecraft.entity.monster.Ghast;
import net.minecraft.entity.monster.MonsterEntityType;
import net.minecraft.entity.projectile.Fireball;
import net.minecraft.level.Level;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(Ghast.class)
public class GhastDamageMixin extends FlyingBase implements MonsterEntityType {

    public GhastDamageMixin(Level arg) {
        super(arg);
        this.texture = "/mob/ghast.png";
        this.setSize(4.0F, 4.0F);
        this.immuneToFire = true;
    }

    @Override
    public boolean damage(EntityBase arg, int i) {
        if (this.level.isServerSide) {
            return false;
        } else {
            this.despawnCounter = 0;
            if (this.health <= 0) {
                return false;
            } else {
                this.limbDistance = 1.5F;
                boolean var3 = true;

                if (Config.config.enableGhastFireballsToInstaKillGhasts) {
                    if (arg == null || arg instanceof Ghast || arg instanceof Fireball) {
                        i = 20;
                    }
                }

                if ((float)this.field_1613 > (float)this.field_1009 / 2.0F) {
                    if (i <= this.field_1058) {
                        return false;
                    }

                    this.applyDamage(i - this.field_1058);
                    this.field_1058 = i;
                    var3 = false;
                } else {
                    this.field_1058 = i;
                    this.field_1037 = this.health;
                    this.field_1613 = this.field_1009;
                    this.applyDamage(i);
                    this.hurtTime = this.field_1039 = 10;
                }

                this.field_1040 = 0.0F;
                if (var3) {
                    this.level.method_185(this, (byte)2);
                    this.method_1336();
                    if (arg != null) {
                        double var4 = arg.x - this.x;

                        double var6;
                        for(var6 = arg.z - this.z; var4 * var4 + var6 * var6 < 1.0E-4; var6 = (Math.random() - Math.random()) * 0.01) {
                            var4 = (Math.random() - Math.random()) * 0.01;
                        }

                        this.field_1040 = (float)(Math.atan2(var6, var4) * 180.0 / 3.1415927410125732) - this.yaw;
                        this.method_925(arg, i, var4, var6);
                    } else {
                        this.field_1040 = (float)((int)(Math.random() * 2.0) * 180);
                    }
                }

                if (this.health <= 0) {
                    if (var3) {
                        this.level.playSound(this, this.getDeathSound(), this.getSoundVolume(), (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F);
                    }

                    this.onKilledBy(arg);
                } else if (var3) {
                    this.level.playSound(this, this.getHurtSound(), this.getSoundVolume(), (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F);
                }

                return true;
            }
        }
    }
}
