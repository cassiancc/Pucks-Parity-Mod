package sirstotes.pucks_consistency_mod.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.mojang.datafixers.types.templates.Tag;

import net.fabricmc.fabric.api.event.lifecycle.v1.CommonLifecycleEvents.TagsLoaded;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.tag.TagKey;
import sirstotes.pucks_consistency_mod.ModItems;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin {
    @ModifyReturnValue(method = "isOf", at = @At("RETURN"))
    public boolean isOfShears(boolean original, Item item) {
        if (item == Items.SHEARS) {
            return original || this.getItem() == ModItems.COPPER_SHEARS;//TODO: Shears Group not just these shears
        }
        return original;
    }
    @Shadow
    public abstract boolean isIn(TagKey<Item> tag);
    @Shadow
	public abstract Item getItem();
}