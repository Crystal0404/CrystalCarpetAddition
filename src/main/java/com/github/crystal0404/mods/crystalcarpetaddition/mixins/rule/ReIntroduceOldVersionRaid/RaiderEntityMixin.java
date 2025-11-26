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
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.wolf.Wolf;
import net.minecraft.world.entity.monster.PatrollingMonster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.raid.Raid;
import net.minecraft.world.entity.raid.Raider;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gamerules.GameRules;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Raider.class)
public abstract class RaiderEntityMixin extends PatrollingMonster {
    protected RaiderEntityMixin(EntityType<? extends @NotNull PatrollingMonster> entityType, Level world) {
        super(entityType, world);
    }

    @Shadow
    @Nullable
    public abstract Raid getCurrentRaid();

    @Inject(
            method = "die",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/monster/PatrollingMonster;die(" +
                            "Lnet/minecraft/world/damagesource/DamageSource;" +
                            ")V"
            )
    )
    @SuppressWarnings("resource")
    private void onDeathMixin(DamageSource damageSource, CallbackInfo ci) {
        // The injection point is not exact, so check the world again
        if (!CCASettings.ReIntroduceOldVersionRaid || !(this.level() instanceof ServerLevel)) return;

        if (
                this.isPatrolLeader()
                        && this.getCurrentRaid() == null
                        && ((ServerLevel) this.level()).getRaidAt(this.blockPosition()) == null
        ) {
            ItemStack itemStack = this.getItemBySlot(EquipmentSlot.HEAD);
            Player playerEntity = this.getPlayerEntity(damageSource.getEntity());
            if (
                    !itemStack.isEmpty()
                            && this.hasBanner(itemStack)
                            && playerEntity != null
            ) {
                MobEffectInstance statusEffectInstance = playerEntity.getEffect(MobEffects.BAD_OMEN);
                int i = 1;
                if (statusEffectInstance != null) {
                    i += statusEffectInstance.getAmplifier();
                    playerEntity.removeEffectNoUpdate(MobEffects.BAD_OMEN);
                } else {
                    i--;
                }
                MobEffectInstance statusEffectInstance2 = new MobEffectInstance(
                        MobEffects.BAD_OMEN,
                        120000,
                        Mth.clamp(i, 0, 4),
                        false,
                        false,
                        true
                );
                if (((ServerLevel) this.level()).getGameRules().get(GameRules.RAIDS)) {
                    playerEntity.addEffect(statusEffectInstance2);
                }
            }
        }
    }

    @Unique
    @Nullable
    private Player getPlayerEntity(Entity entity) {
        if (entity instanceof Player) {
            return (Player) entity;
        } else if (entity instanceof Wolf wolfEntity) {
            LivingEntity livingEntity = wolfEntity.getOwner();
            if (wolfEntity.isTame() && livingEntity instanceof Player) {
                return (Player) livingEntity;
            }
        }
        return null;
    }

    @Unique
    private boolean hasBanner(ItemStack itemStack) {
        return ItemStack.matches(
                itemStack,
                Raid.getOminousBannerInstance(this.registryAccess().lookupOrThrow(Registries.BANNER_PATTERN))
        );
    }
}
