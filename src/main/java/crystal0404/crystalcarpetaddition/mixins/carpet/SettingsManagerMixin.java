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

package crystal0404.crystalcarpetaddition.mixins.carpet;

import carpet.CarpetSettings;
import carpet.api.settings.SettingsManager;
import carpet.utils.Messenger;
import carpet.utils.TranslationKeys;
import crystal0404.crystalcarpetaddition.CrystalCarpetAdditionMod;
import me.fallenbreath.conditionalmixin.api.annotation.Condition;
import me.fallenbreath.conditionalmixin.api.annotation.Restriction;
import net.minecraft.server.command.ServerCommandSource;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Objects;

import static carpet.utils.Translations.tr;

@Restriction(require = @Condition("carpet"))  // Maybe something else will be stuffed in the future?
@Mixin(value = SettingsManager.class, remap = false)
public abstract class SettingsManagerMixin {
    @Shadow @Final private String fancyName;
    @Inject(
            method = "listAllSettings",
            at = @At(
                    value = "INVOKE",
                    target = "Lcarpet/utils/Messenger;m(Lnet/minecraft/server/command/ServerCommandSource;[Ljava/lang/Object;)V",
                    ordinal = 0,
                    shift = At.Shift.BY,
                    by = 1,
                    remap = true
            )
    )
    private void listAllSettingsMixin(ServerCommandSource source, CallbackInfoReturnable<Integer> cir) {
        if (!Objects.equals(this.fancyName, CrystalCarpetAdditionMod.MOD_NAME)) {
            String msg = "g %s %s: %s".formatted(
                 CrystalCarpetAdditionMod.MOD_NAME,
                 tr(TranslationKeys.VERSION),
                 CrystalCarpetAdditionMod.version
            );
            Messenger.m(source, msg);
        }
    }

    // This should only be added to "/cca" to prevent someone from repeating it
    @Inject(
            method = "listAllSettings",
            at = @At(
                    value = "INVOKE",
                    target = "Lcarpet/utils/Messenger;m(Lnet/minecraft/server/command/ServerCommandSource;[Ljava/lang/Object;)V",
                    ordinal = 0
            )
    )
    private void listAllSettingsMixin_carpet(ServerCommandSource source, CallbackInfoReturnable<Integer> cir) {
        if (Objects.equals(this.fancyName, CrystalCarpetAdditionMod.MOD_NAME)) {
            String msg = "g %s %s: %s".formatted(
                    "Carpet Mod",
                    tr(TranslationKeys.VERSION),
                    CarpetSettings.carpetVersion
            );
            Messenger.m(source, msg);
        }
    }
}
