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

package com.github.crystal0404.mods.crystalcarpetaddition.mixins.rule.ReIntroduceOldVersionRaid;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import com.github.crystal0404.mods.crystalcarpetaddition.CCASettings;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnLocation;
import net.minecraft.entity.SpawnRestriction;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.village.raid.Raid;
import net.minecraft.world.Heightmap;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import java.util.Optional;
import java.util.function.Supplier;

@Mixin(Raid.class)
public abstract class RaidMixin {
    @Shadow
    private BlockPos center;

    @Shadow
    private int preRaidTicks;

    @ModifyArg(
            method = "start",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/server/network/ServerPlayerEntity;" +
                            "getStatusEffect(Lnet/minecraft/registry/entry/RegistryEntry;)" +
                            "Lnet/minecraft/entity/effect/StatusEffectInstance;"
            )
    )
    private RegistryEntry<StatusEffect> startMixin_getStatusEffect(RegistryEntry<StatusEffect> original) {
        return CCASettings.ReIntroduceOldVersionRaid ? StatusEffects.BAD_OMEN : original;
    }

    @ModifyArg(
            method = "tick",
            at = @At(
                    value = "INVOKE",
                    target = "Ljava/util/Optional;orElseGet(Ljava/util/function/Supplier;)Ljava/lang/Object;"
            )
    )
    private Supplier<? extends BlockPos> tickMixin_findRandomRaidersSpawnLocation(
            Supplier<? extends BlockPos> original,
            @Local(ordinal = 1) int j,
            @Local(ordinal = 0, argsOnly = true) ServerWorld serverWorld
    ) {
        return CCASettings.ReIntroduceOldVersionRaid ? () -> this.cca$getRavagerSpawnLocation(serverWorld, j, 20) : original;
    }

    @ModifyExpressionValue(
            method = "tick",
            at = @At(
                    value = "CONSTANT",
                    args = "intValue=5",
                    ordinal = 1
            )
    )
    private int tickMixin_modifyNumberOfAttempts(int original) {
        return CCASettings.ReIntroduceOldVersionRaid ? 3 : original;
    }

    @WrapOperation(
            method = "tick",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/village/raid/Raid;" +
                            "getRaidersSpawnLocation(Lnet/minecraft/server/world/ServerWorld;)Ljava/util/Optional;"
            )
    )
    private Optional<BlockPos> tickMixin_getRaidersSpawnLocation(
            Raid instance,
            ServerWorld serverWorld,
            Operation<Optional<BlockPos>> original
    ) {
        if (CCASettings.ReIntroduceOldVersionRaid) {
            return this.cca$preCalculateRavagerSpawnLocation(serverWorld, this.preRaidTicks < 100 ? 1 : 0);
        } else {
            return original.call(instance, serverWorld);
        }
    }

    // from Minecraft-1.21.1
    @Unique
    @Nullable
    @SuppressWarnings("deprecation")
    private BlockPos cca$getRavagerSpawnLocation(ServerWorld serverWorld, int proximity, int tries) {
        int i = proximity == 0 ? 2 : 2 - proximity;
        BlockPos.Mutable mutable = new BlockPos.Mutable();
        SpawnLocation spawnLocation = SpawnRestriction.getLocation(EntityType.RAVAGER);

        for (int j = 0; j < tries; j++) {
            float f = serverWorld.random.nextFloat() * (float) (Math.PI * 2);
            int k = this.center.getX() + MathHelper.floor(
                    MathHelper.cos(f) * 32.0F * (float) i + serverWorld.random.nextInt(5)
            );
            int l = this.center.getZ() + MathHelper.floor(
                    MathHelper.sin(f) * 32.0F * (float) i + serverWorld.random.nextInt(5)
            );
            int m = serverWorld.getTopY(Heightmap.Type.WORLD_SURFACE, k, l);
            mutable.set(k, m, l);

            if (!serverWorld.isNearOccupiedPointOfInterest(mutable) || proximity >= 2) {
                if (
                        serverWorld.isRegionLoaded(
                                mutable.getX() - 10,
                                mutable.getZ() - 10,
                                mutable.getX() + 10,
                                mutable.getZ() + 10
                        )
                                && serverWorld.shouldTickEntityAt(mutable)
                                && (
                                spawnLocation.isSpawnPositionOk(serverWorld, mutable, EntityType.RAVAGER)
                                        || serverWorld.getBlockState(mutable.down()).isOf(Blocks.SNOW)
                                        && serverWorld.getBlockState(mutable).isAir()
                        )
                ) {
                    return mutable;
                }
            }
        }
        return null;
    }

    // from Minecraft-1.21.1
    @Unique
    private Optional<BlockPos> cca$preCalculateRavagerSpawnLocation(ServerWorld serverWorld, int proximity) {
        for (int i = 0; i < 3; i++) {
            BlockPos blockPos = this.cca$getRavagerSpawnLocation(serverWorld, proximity, 1);
            if (blockPos != null) {
                return Optional.of(blockPos);
            }
        }
        return Optional.empty();
    }
}
