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
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.PortalProcessor;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.entity.boss.enderdragon.EnderDragonPart;
import net.minecraft.world.level.block.EndPortalBlock;
import net.minecraft.world.level.block.Portal;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Mob.class)
public abstract class MobEntityMixin {
    @Inject(
            method = "tick",
            at = @At("TAIL")
    )
    private void tickMixin(CallbackInfo ci) {
        if (!CCASettings.EnderDragonPartCanUseEndPortal) return;
        if ((Mob) ((Object) this) instanceof EnderDragon entity) {
            for (EnderDragonPart part : entity.getSubEntities()) {
                // If it's null, skip it
                PortalProcessor portalManager = part.portalProcess;
                if (portalManager == null) continue;

                // It should not go into other portals
                Portal portal = ((PortalManagerAccessor) portalManager).getPortal();
                if (!(portal instanceof EndPortalBlock)) continue;

                ((EntityInvoker) part).invokeTickPortalTeleportation();
            }
        }
    }
}
