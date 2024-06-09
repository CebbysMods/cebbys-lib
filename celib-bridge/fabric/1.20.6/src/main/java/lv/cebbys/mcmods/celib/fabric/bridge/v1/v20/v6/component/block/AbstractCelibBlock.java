package lv.cebbys.mcmods.celib.fabric.bridge.v1.v20.v6.component.block;

import lv.cebbys.mcmods.celib.api.component.block.CelibBlock;
import lv.cebbys.mcmods.celib.api.component.block.properties.CelibBlockProperties;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;

public abstract class AbstractCelibBlock extends Block {
    public static Block create(CelibBlock block) {
        return new AbstractCelibBlock(block) {
            @Override
            protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
            }
        };
    }

    public AbstractCelibBlock(CelibBlock block) {
        super(getProperties(block.getProperties()));
    }

    private static Properties getProperties(CelibBlockProperties properties) {
        return BlockBehaviour.Properties.of();
    }
}
