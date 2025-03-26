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

package crystal0404.crystalcarpetaddition.mixins.rule.MagicBox;

import crystal0404.crystalcarpetaddition.utils.ModIds;
import me.fallenbreath.conditionalmixin.api.annotation.Condition;
import me.fallenbreath.conditionalmixin.api.annotation.Restriction;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.LecternBlock;
import org.spongepowered.asm.mixin.Mixin;

@Restriction(
        require = @Condition(value = ModIds.MC, versionPredicates = ">=1.21.1"),
        conflict = @Condition(value = ModIds.MC, versionPredicates = ">=1.21.5")
)
@Mixin(LecternBlock.class)
public abstract class LecternBlockMixin extends BlockWithEntity {
    protected LecternBlockMixin(Settings settings) {
        super(settings);
    }
    // I tried my best, but the relevant features are not available for the time being
}
