package crystal0404.crystalcarpetaddition.mixins.rule.ReIntroduceOldVersionWitchLootTable;

import crystal0404.crystalcarpetaddition.utils.EmptyClass;
import me.fallenbreath.conditionalmixin.api.annotation.Condition;
import me.fallenbreath.conditionalmixin.api.annotation.Restriction;
import org.spongepowered.asm.mixin.Mixin;

@Restriction(require = @Condition(value = "minecraft", versionPredicates = ">=1.21"))
@Mixin(EmptyClass.class)
public class LivingEntityMixin {
}
