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

import crystal0404.crystalcarpetaddition.CCASettings;
import net.minecraft.block.EndGatewayBlock;
import net.minecraft.world.TeleportTarget;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(EndGatewayBlock.class)
public abstract class EndGatewayBlockMixin {
    @ModifyArg(
            method = "createTeleportTarget",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/TeleportTarget;<init>(Lnet/minecraft/server/world/ServerWorld;" +
                            "Lnet/minecraft/util/math/Vec3d;Lnet/minecraft/util/math/Vec3d;" +
                            "FFLnet/minecraft/world/TeleportTarget$PostDimensionTransition;)V"
            ),
            index = 5
    )
    private TeleportTarget.PostDimensionTransition createTeleportTargetMixin(
            TeleportTarget.PostDimensionTransition original
    ) {
        return CCASettings.GatewayCannotLoadingChunks ? TeleportTarget.NO_OP : original;
    }
}
