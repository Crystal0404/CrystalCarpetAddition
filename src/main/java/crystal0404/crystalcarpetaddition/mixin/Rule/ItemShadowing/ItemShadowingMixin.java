package crystal0404.crystalcarpetaddition.mixin.Rule.ItemShadowing;

import com.llamalad7.mixinextras.injector.WrapWithCondition;
import crystal0404.crystalcarpetaddition.CCASettings;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.screen.slot.SlotActionType;
import net.minecraft.util.collection.DefaultedList;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(ScreenHandler.class)
public abstract class ItemShadowingMixin {
    @Final
    @Shadow
    public DefaultedList<Slot> slots = DefaultedList.of();
    @Unique
    Slot slot3;
    @Unique
    net.minecraft.item.ItemStack itemStack2;
    @Unique
    PlayerInventory playerInventory;

    @WrapWithCondition(
            method = "internalOnSlotClick(IILnet/minecraft/screen/slot/SlotActionType;Lnet/minecraft/entity/player/PlayerEntity;)V",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/screen/slot/Slot;setStack(Lnet/minecraft/item/ItemStack;)V", ordinal = 4)
    )
    private boolean RemoveMixin(Slot instance, net.minecraft.item.ItemStack stack){
        return !CCASettings.ItemShadowing;
    }
    @Inject(
            method = "internalOnSlotClick(IILnet/minecraft/screen/slot/SlotActionType;Lnet/minecraft/entity/player/PlayerEntity;)V",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/PlayerInventory;setStack(ILnet/minecraft/item/ItemStack;)V", ordinal = 1)
    )
    private void internalOnSlotClickMixin(int slotIndex, int button, SlotActionType actionType, PlayerEntity player, CallbackInfo ci){
        slot3 = this.slots.get(slotIndex);
        playerInventory = player.getInventory();
        itemStack2 = playerInventory.getStack(button);
        if(CCASettings.ItemShadowing){
            slot3.setStack(itemStack2);
        }
    }
}
