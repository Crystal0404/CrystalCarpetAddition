/*
 * This file is part of the Crystal Carpet Addition project, licensed under the
 * GNU General Public License v3.0
 *
 * Copyright (C) 2024  Crystal_0404 and contributors
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

package crystal0404.crystalcarpetaddition.mixin.Rule.CEnderPearlChunkLoading;

import com.llamalad7.mixinextras.sugar.Local;
import crystal0404.crystalcarpetaddition.CCASettings;
import crystal0404.crystalcarpetaddition.carpet_extra.utils.ChunkUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.thrown.EnderPearlEntity;
import net.minecraft.entity.projectile.thrown.ThrownEntity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@SuppressWarnings("all")  // If you need to debug, you should delete this line
@Mixin(ThrownEntity.class)
public abstract class CEnderPearlChunkLoadingMixin extends Entity {

    public CEnderPearlChunkLoadingMixin(EntityType<?> type, World world) {
        super(type, world);
    }
    @Inject(
            method = "tick",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/projectile/thrown/ThrownEntity;updateRotation()V")
    )
    private void ThrownEntityMixin(CallbackInfo ci, @Local Vec3d vec3d){
        if (CCASettings.CEnderPearlChunkLoading && ((Object) this) instanceof EnderPearlEntity) {
            ChunkUtils.addEnderPearlChunkTicket(this, vec3d);
        }
    }

    @Inject(
            method = "tick",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/projectile/thrown/ThrownEntity;setPosition(DDD)V")
    )
    private void ThrownEntityMixin(CallbackInfo ci) {
        if (CCASettings.CEnderPearlChunkLoading && ((Object) this) instanceof EnderPearlEntity) {
            Vec3d velocity = this.getVelocity();
            ChunkUtils.addEnderPearlChunkTicket(this, velocity);
        }
    }
}
