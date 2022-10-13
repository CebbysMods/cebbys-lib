package lv.cebbys.mcmods.celib.test;

import lv.cebbys.mcmods.celib.api.component.block.CelibBlock;
import lv.cebbys.mcmods.celib.api.component.block.CelibBlockProperties;
import lv.cebbys.mcmods.celib.api.component.item.CelibItem;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class TestBasicBlock implements CelibBlock {
    @Override
    public @NotNull Block asBlock() {
        return null;
    }

    @Override
    public @Nullable CelibItem asCelibItem() {
        return null;
    }

    @Override
    public CelibBlockProperties getCelibBlockProperties() {
        return null;
    }

    @Override
    public void appendBlockStateProperties(StateDefinition.Builder<Block, BlockState> builder) {

    }

    @Override
    public ResourceLocation getLootTable(Level level, BlockPos blockPos, ItemStack tool, Entity entity) {
        return null;
    }

    @Override
    public BlockState getDefaultState(BlockState defaultBlockState) {
        return null;
    }
}
