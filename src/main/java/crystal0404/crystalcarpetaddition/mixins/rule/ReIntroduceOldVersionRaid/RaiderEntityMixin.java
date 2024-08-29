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

package crystal0404.crystalcarpetaddition.mixins.rule.ReIntroduceOldVersionRaid;

import crystal0404.crystalcarpetaddition.CCASettings;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.PatrolEntity;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.raid.RaiderEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.MathHelper;
import net.minecraft.village.raid.Raid;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(RaiderEntity.class)
public abstract class RaiderEntityMixin extends PatrolEntity {
    @Shadow
    @Nullable
    public abstract Raid getRaid();

    protected RaiderEntityMixin(EntityType<? extends PatrolEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(
            method = "onDeath",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/entity/mob/PatrolEntity;onDeath(Lnet/minecraft/entity/damage/DamageSource;)V"
            )
    )
    @SuppressWarnings("all")
    private void onDeathMixin(DamageSource damageSource, CallbackInfo ci) {
        if (!CCASettings.ReIntroduceOldVersionRaid) return;
        if (this.getWorld() instanceof ServerWorld) {
            Entity entity = damageSource.getAttacker();
            Raid raid = this.getRaid();
            if (this.isPatrolLeader() && raid == null && ((ServerWorld) this.getWorld()).getRaidAt(this.getBlockPos()) == null) {
                ItemStack itemStack = this.getEquippedStack(EquipmentSlot.HEAD);
                PlayerEntity playerEntity = null;
                Entity entity2 = entity;
                if (entity2 instanceof PlayerEntity) {
                    playerEntity = (PlayerEntity) entity2;
                } else if (entity2 instanceof WolfEntity) {
                    WolfEntity wolfEntity = (WolfEntity) entity2;
                    LivingEntity livingEntity = wolfEntity.getOwner();
                    if (wolfEntity.isTamed() && livingEntity instanceof PlayerEntity) {
                        playerEntity = (PlayerEntity) livingEntity;
                    }
                }
                // TODO It will be removed in the future(last mc1.21.x)
                //#if MC <= 12101
                //$$  if (!itemStack.isEmpty() && ItemStack.areEqual(itemStack, Raid.getOminousBanner(this.getRegistryManager().getWrapperOrThrow(RegistryKeys.BANNER_PATTERN))) && playerEntity != null) {
                //#else
                if (!itemStack.isEmpty() && ItemStack.areEqual(itemStack, Raid.createOminousBanner(this.getRegistryManager().getOrThrow(RegistryKeys.BANNER_PATTERN))) && playerEntity != null) {
                //#endif
                    StatusEffectInstance statusEffectInstance = playerEntity.getStatusEffect(StatusEffects.BAD_OMEN);
                    int i = 1;
                    if (statusEffectInstance != null) {
                        i += statusEffectInstance.getAmplifier();
                        playerEntity.removeStatusEffectInternal(StatusEffects.BAD_OMEN);
                    } else {
                        --i;
                    }
                    i = MathHelper.clamp(i, 0, 4);
                    StatusEffectInstance statusEffectInstance2 = new StatusEffectInstance(StatusEffects.BAD_OMEN, 120000, i, false, false, true);
                    if (!this.getWorld().getGameRules().getBoolean(GameRules.DISABLE_RAIDS)) {
                        playerEntity.addStatusEffect(statusEffectInstance2);
                    }
                }
            }
        }
    }
}
