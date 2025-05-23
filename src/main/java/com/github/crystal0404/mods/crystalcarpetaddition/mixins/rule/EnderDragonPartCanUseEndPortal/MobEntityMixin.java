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

package com.github.crystal0404.mods.crystalcarpetaddition.mixins.rule.EnderDragonPartCanUseEndPortal;

import com.github.crystal0404.mods.crystalcarpetaddition.CCASettings;
import net.minecraft.block.EndPortalBlock;
import net.minecraft.block.Portal;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.boss.dragon.EnderDragonPart;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.world.dimension.PortalManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MobEntity.class)
public abstract class MobEntityMixin {
    @Inject(
            method = "tick",
            at = @At("TAIL")
    )
    private void tickMixin(CallbackInfo ci) {
        if (!CCASettings.EnderDragonPartCanUseEndPortal) return;
        if ((MobEntity) ((Object) this) instanceof EnderDragonEntity entity) {
            for (EnderDragonPart part : entity.getBodyParts()) {
                // If it's null, skip it
                PortalManager portalManager = ((EntityAccessor) part).getPortalManager();
                if (portalManager == null) continue;

                // It should not go into other portals
                Portal portal = ((PortalManagerAccessor) portalManager).getPortal();
                if (!(portal instanceof EndPortalBlock)) continue;

                ((EntityInvoker) part).invokeTickPortalTeleportation();
            }
        }
    }
}
