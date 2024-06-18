/*
 * This file is part of the Crystal Carpet Addition project, licensed under the
 * GNU General Public License v3.0
 *
 * Copyright (C) 2024  Crystal0404 and contributors
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

package crystal0404.crystalcarpetaddition.mixins.rule.GatewayCannotLoadingChunks;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import crystal0404.crystalcarpetaddition.CCASettings;
import net.minecraft.block.EndGatewayBlock;
import net.minecraft.entity.Entity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.TeleportTarget;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(EndGatewayBlock.class)
public abstract class EndGatewayBlockMixin {
    @ModifyReturnValue(
            method = "createTeleportTarget",
            at = @At(value = "RETURN", ordinal = 1)
    )
    private TeleportTarget createTeleportTargetMixin(
            TeleportTarget original,
            @Local(argsOnly = true, ordinal = 0) ServerWorld world,
            @Local(ordinal = 0) Vec3d vec3d,
            @Local(argsOnly = true, ordinal = 0) Entity entity
            ) {
        if (CCASettings.GatewayCannotLoadingChunks) {
            return new TeleportTarget(
                    world,
                    vec3d,
                    EndGatewayBlockInvoker.invokeGetTeleportVelocity(entity),
                    entity.getYaw(),
                    entity.getPitch(),
                    TeleportTarget.NO_OP
            );
        } else {
            return original;
        }
    }
}
