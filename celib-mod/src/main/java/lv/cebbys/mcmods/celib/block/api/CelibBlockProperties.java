package lv.cebbys.mcmods.celib.block.api;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

public interface CelibBlockProperties {
    BlockBehaviour.Properties asBlockProperties();

    boolean canBeTickedRandomly(BlockState blockState);

}
