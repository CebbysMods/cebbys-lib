package lv.cebbys.mcmods.celib.block.api;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;

public interface CelibBlock {
    void onUpdateTick();

    void onRandomTick();

    void onAnimateTick();

    CelibBlockProperties getProperties();

    void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder);
}
