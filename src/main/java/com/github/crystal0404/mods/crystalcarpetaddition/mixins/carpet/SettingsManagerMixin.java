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

package com.github.crystal0404.mods.crystalcarpetaddition.mixins.carpet;

import carpet.api.settings.CarpetRule;
import carpet.api.settings.SettingsManager;
import carpet.utils.Messenger;
import carpet.utils.TranslationKeys;
import com.github.crystal0404.mods.crystalcarpetaddition.CCAExtension;
import com.github.crystal0404.mods.crystalcarpetaddition.CrystalCarpetAdditionMod;
import com.github.crystal0404.mods.crystalcarpetaddition.utils.annotation.Restriction;
import com.github.crystal0404.mods.crystalcarpetaddition.utils.annotation.impl.AnnotationProcessor;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.commands.CommandSourceStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Objects;

import static carpet.utils.Translations.tr;

@Mixin(SettingsManager.class)
public abstract class SettingsManagerMixin {
    @Shadow(remap = false)
    @Final
    private String fancyName;

    // show version
    @Inject(
            method = "listAllSettings",
            at = @At(
                    value = "INVOKE",
                    target = "Lcarpet/utils/Messenger;m(Lnet/minecraft/commands/CommandSourceStack;[Ljava/lang/Object;)V",
                    ordinal = 0,
                    shift = At.Shift.AFTER
            )
    )
    private void listAllSettingsMixin(CommandSourceStack source, CallbackInfoReturnable<Integer> cir) {
        if (Objects.equals(this.fancyName, "Carpet Mod")) {
            String msg = "g %s %s: %s".formatted(
                    CrystalCarpetAdditionMod.MOD_NAME,
                    tr(TranslationKeys.VERSION),
                    CrystalCarpetAdditionMod.version
            );
            Messenger.m(source, msg);
        }
    }

    // my custom annotation handling
    // Do not use @WrapWithCondition, it is not recommended for non-void methods
    @WrapOperation(
            method = "parseSettingsClass",
            at = @At(
                    value = "INVOKE",
                    target = "Ljava/util/Map;put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"
            )
    )
    private Object parseSettingsClassMixin(
            Map<String, CarpetRule<?>> instance,
            Object k,
            Object v,
            Operation<Object> original,
            @Local(name = "field") Field field
    ) {
        if ((Object) this == CCAExtension.CCASettingsManager) {
            Restriction restriction = field.getAnnotation(Restriction.class);
            if (restriction != null && !AnnotationProcessor.shouldRegister(restriction, (String) k)) {
                // returns to default values and does nothing
                return null;
            }
        }
        return original.call(instance, k, v);
    }
}
