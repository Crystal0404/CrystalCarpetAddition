package com.github.crystal0404.mods.crystalcarpetaddition.mixins.rule.RemoveHighSpeedPearls;

import com.github.crystal0404.mods.crystalcarpetaddition.CCASettings;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.thrown.EnderPearlEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EnderPearlEntity.class)
public abstract class EnderPearlEntityMixin extends ThrownItemEntity {
    public EnderPearlEntityMixin(EntityType<? extends ThrownItemEntity> entityType, World world) {
        super(entityType, world);
    }

    @Unique
    private int highSpeedTime = 0;

    @Inject(
            method = "tick",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/entity/projectile/thrown/EnderPearlEntity;isAlive()Z"
            )
    )
    private void tickMixin(CallbackInfo ci, @Local(ordinal = 0) Entity entity) {
        if (!CCASettings.RemoveHighSpeedPearls) return;

        if (this.isHighSpeed()) {
            this.highSpeedTime += 1;
        } else {
            this.highSpeedTime = 0;
        }
        if (this.isAlive() && this.highSpeedTime >= CCASettings.RemoveHighSpeedPearlsTime) {
            this.discard();
        }
    }

    @Unique
    private boolean isHighSpeed() {
        return Math.abs(this.getVelocity().getX()) > 20d || Math.abs(this.getVelocity().getZ()) > 20d;
    }
}
