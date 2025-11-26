/*
 * This file is part of the Crystal Carpet Addition project, licensed under the
 * GNU General Public License v3.0
 *
 * Copyright (C) 2025  Crystal0404 and contributors
 *
 * Crystal Carpet Addition is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Crystal Carpet Addition is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Crystal Carpet Addition.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.github.crystal0404.mods.crystalcarpetaddition.mixins.rule.RemoveHighSpeedPearls;

import com.github.crystal0404.mods.crystalcarpetaddition.CCASettings;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.throwableitemprojectile.ThrowableItemProjectile;
import net.minecraft.world.entity.projectile.throwableitemprojectile.ThrownEnderpearl;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ThrownEnderpearl.class)
public abstract class EnderPearlEntityMixin extends ThrowableItemProjectile {
    public EnderPearlEntityMixin(EntityType<? extends @NotNull ThrowableItemProjectile> entityType, Level world) {
        super(entityType, world);
    }

    @Unique
    private int highSpeedTime = 0;

    @Inject(
            method = "tick",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/projectile/throwableitemprojectile/ThrownEnderpearl;isAlive()Z"
            )
    )
    private void tickMixin(CallbackInfo ci) {
        if (!CCASettings.RemoveHighSpeedPearls) return;

        if (this.isHighSpeed()) {
            this.highSpeedTime += 1;
        } else {
            this.highSpeedTime = 0;
        }
        if (this.isAlive() && this.highSpeedTime > CCASettings.RemoveHighSpeedPearlsTime) {
            this.discard();
        }
    }

    @Unique
    private boolean isHighSpeed() {
        return Math.abs(this.getDeltaMovement().x()) > 20d || Math.abs(this.getDeltaMovement().z()) > 20d;
    }
}
