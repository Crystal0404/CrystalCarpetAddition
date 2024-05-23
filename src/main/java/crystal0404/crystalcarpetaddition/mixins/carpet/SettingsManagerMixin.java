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

import carpet.api.settings.CarpetRule;
import carpet.api.settings.RuleHelper;
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
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import static carpet.utils.Translations.tr;

@Restriction(require = @Condition("carpet"))  // Maybe something else will be stuffed in the future?
@Mixin(value = SettingsManager.class, remap = false)
public abstract class SettingsManagerMixin {
    @Shadow
    @Final
    private String fancyName;

    @Shadow
    @Final
    private String version;

    @Shadow
    protected abstract int listSettings(ServerCommandSource source, String title, Collection<CarpetRule<?>> settings_list);

    @Shadow
    protected abstract Collection<CarpetRule<?>> getNonDefault();

    @Shadow
    public abstract Iterable<String> getCategories();

    @Shadow
    public abstract String identifier();

    @Shadow
    @Final
    private String identifier;

    @Inject(
            method = "listAllSettings",
            at = @At(
                    value = "INVOKE",
                    target = "Lcarpet/utils/Messenger;m(Lnet/minecraft/server/command/ServerCommandSource;[Ljava/lang/Object;)V",
                    ordinal = 0,
                    shift = At.Shift.AFTER,
                    remap = true
            )
    )
    private void listAllSettingsMixin_sendMessage(ServerCommandSource source, CallbackInfoReturnable<Integer> cir) {
        if (Objects.equals(this.fancyName, "Carpet Mod")) {
            String msg = "g %s %s: %s".formatted(
                    CrystalCarpetAdditionMod.MOD_NAME,
                    tr(TranslationKeys.VERSION),
                    CrystalCarpetAdditionMod.version
            );
            Messenger.m(source, msg);
        }
    }

    // Redirect to my own clean "listAllSettings"
    @Inject(
            method = "listAllSettings",
            at = @At("HEAD"),
            cancellable = true,
            remap = true
    )
    private void listAllSettingsMixin(ServerCommandSource source, CallbackInfoReturnable<Integer> cir) {
        if (Objects.equals(this.fancyName, CrystalCarpetAdditionMod.MOD_NAME)) {
            cir.setReturnValue(this.CCAListAllSettings(source));
        }
    }

    @Unique
    private int CCAListAllSettings(ServerCommandSource source) {
        int count = this.listSettings(
                source, String.format(tr(TranslationKeys.CURRENT_SETTINGS_HEADER), this.fancyName),
                this.getNonDefault()
        );

        if (this.version != null)
            Messenger.m(source, "g " + this.fancyName + " " + tr(TranslationKeys.VERSION) + ": " + version);

        List<String> tags = new ArrayList<>();
        tags.add("w " + tr(TranslationKeys.BROWSE_CATEGORIES) + ":\n");
        for (String t : this.getCategories()) {
            String translated = RuleHelper.translatedCategory(this.identifier(), t);
            String translatedPlus = !translated.equals(t) ? "%s (%s)".formatted(translated, t) : t;
            tags.add("c [" + translated + "]");
            tags.add("^g " + String.format(tr(TranslationKeys.LIST_ALL_CATEGORY), translatedPlus));
            tags.add("!/" + this.identifier + " list " + t);
            tags.add("w  ");
        }
        tags.remove(tags.size() - 1);
        Messenger.m(source, tags.toArray(new Object[0]));

        return count;
    }
}
