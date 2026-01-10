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

import com.github.crystal0404.mods.crystalcarpetaddition.CCASettings;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.SpawnPlacementType;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.raid.Raid;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Heightmap;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
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
    private int raidCooldownTicks;

    @Shadow
    @Final
    private RandomSource random;

    @ModifyArg(
            method = "absorbRaidOmen",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/server/level/ServerPlayer;getEffect(" +
                            "Lnet/minecraft/core/Holder;" +
                            ")Lnet/minecraft/world/effect/MobEffectInstance;"
            )
    )
    private Holder<@NotNull MobEffect> startMixin_getStatusEffect(Holder<@NotNull MobEffect> original) {
        return CCASettings.ReIntroduceOldVersionRaid ? MobEffects.BAD_OMEN : original;
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
            @Local(ordinal = 0, argsOnly = true) ServerLevel serverWorld
    ) {
        return CCASettings.ReIntroduceOldVersionRaid ? () -> this.getRavagerSpawnLocation(serverWorld, j, 20) : original;
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
                    target = "Lnet/minecraft/world/entity/raid/Raid;getValidSpawnPos(" +
                            "Lnet/minecraft/server/level/ServerLevel;" +
                            ")Ljava/util/Optional;"
            )
    )
    private Optional<BlockPos> tickMixin_getRaidersSpawnLocation(
            Raid instance,
            ServerLevel serverWorld,
            Operation<Optional<BlockPos>> original
    ) {
        if (CCASettings.ReIntroduceOldVersionRaid) {
            return this.preCalculateRavagerSpawnLocation(serverWorld, this.raidCooldownTicks < 100 ? 1 : 0);
        } else {
            return original.call(instance, serverWorld);
        }
    }

    // from Minecraft-1.21.1
    @Unique
    @SuppressWarnings("deprecation")
    private @Nullable BlockPos getRavagerSpawnLocation(ServerLevel serverWorld, int proximity, int tries) {
        int i = proximity == 0 ? 2 : 2 - proximity;
        BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos();
        SpawnPlacementType spawnLocation = SpawnPlacements.getPlacementType(EntityType.RAVAGER);

        for (int j = 0; j < tries; j++) {
            float f = this.random.nextFloat() * (float) (Math.PI * 2);
            int k = this.center.getX() + Mth.floor(
                    Mth.cos(f) * 32.0F * (float) i + this.random.nextInt(5)
            );
            int l = this.center.getZ() + Mth.floor(
                    Mth.sin(f) * 32.0F * (float) i + this.random.nextInt(5)
            );
            int m = serverWorld.getHeight(Heightmap.Types.WORLD_SURFACE, k, l);
            mutable.set(k, m, l);

            if (!serverWorld.isVillage(mutable) || proximity >= 2) {
                if (
                        serverWorld.hasChunksAt(
                                mutable.getX() - 10,
                                mutable.getZ() - 10,
                                mutable.getX() + 10,
                                mutable.getZ() + 10
                        )
                                && serverWorld.isPositionEntityTicking(mutable)
                                && (
                                spawnLocation.isSpawnPositionOk(serverWorld, mutable, EntityType.RAVAGER)
                                        || serverWorld.getBlockState(mutable.below()).is(Blocks.SNOW)
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
    private Optional<BlockPos> preCalculateRavagerSpawnLocation(ServerLevel serverWorld, int proximity) {
        for (int i = 0; i < 3; i++) {
            BlockPos blockPos = this.getRavagerSpawnLocation(serverWorld, proximity, 1);
            if (blockPos != null) {
                return Optional.of(blockPos);
            }
        }
        return Optional.empty();
    }
}
