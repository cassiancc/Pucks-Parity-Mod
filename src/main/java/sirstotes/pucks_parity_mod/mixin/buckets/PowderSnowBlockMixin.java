package sirstotes.pucks_parity_mod.mixin.buckets;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.PowderSnowBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BucketItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldEvents;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import sirstotes.pucks_parity_mod.accessors.BucketItemMixinAccessor;
import sirstotes.pucks_parity_mod.accessors.FluidDrainableMixinAccessor;

@Mixin (PowderSnowBlock.class)
public class PowderSnowBlockMixin implements FluidDrainableMixinAccessor {
    @Unique
    public ItemStack pucks_Parity_Mod$tryDrainFluid(Item item, WorldAccess world, BlockPos pos, BlockState state) {
        world.setBlockState(pos, Blocks.AIR.getDefaultState(), Block.NOTIFY_ALL_AND_REDRAW);
        if (!world.isClient()) {
            world.syncWorldEvent(WorldEvents.BLOCK_BROKEN, pos, Block.getRawIdFromState(state));
        }
        if (item instanceof BucketItem) {
            return new ItemStack(((BucketItemMixinAccessor) item).pucks_Parity_Mod$getPowderedSnow());
        }
        return new ItemStack(Items.POWDER_SNOW_BUCKET);
    }
}