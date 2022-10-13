package lv.cebbys.mcmods.celib.bridge.v1_19;

import lv.cebbys.mcmods.celib.api.component.block.CelibBlock;
import lv.cebbys.mcmods.celib.bridge.AbstractBridgeBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

@ParametersAreNonnullByDefault
public class CelibBridgeBlock extends AbstractBridgeBlock {
    private static final Logger LOGGER = LoggerFactory.getLogger(CelibBridgeBlock.class);

    public CelibBridgeBlock(@NotNull CelibBlock block) {
        super(block);
    }

    @Override
    public void animateTick(BlockState blockState, Level level, BlockPos blockPos, RandomSource randomSource) {
        LOGGER.debug("CelibBlockLink.animateTick");
        if (getProperties().canBeAnimated()) {
            getBridge().animateTick(blockState, level, blockPos, randomSource);
        }
    }

    @Override
    public void randomTick(BlockState blockState, ServerLevel serverLevel, BlockPos blockPos, RandomSource randomSource) {
        LOGGER.debug("CelibBlockLink.randomTick");
        if (getProperties().canBeTickedRandomly()) {
            getBridge().randomTick(blockState, serverLevel, blockPos, randomSource);
        }
    }

    @Override
    public void tick(BlockState blockState, ServerLevel serverLevel, BlockPos blockPos, RandomSource randomSource) {
        LOGGER.debug("CelibBlockLink.tick");
        if (getProperties().canBeTicked()) {
            getBridge().updateTick(blockState, serverLevel, blockPos, randomSource);
        }
    }

    @Override
    public float getDestroyProgress(BlockState blockState, Player player, BlockGetter blockGetter, BlockPos blockPos) {
        LOGGER.debug("CelibBlockLink.getDestroyProgress");
        return getProperties().getBreakingSpeed(blockState, player, blockGetter, blockPos);
    }

    @Deprecated
    @Override
    public List<ItemStack> getDrops(BlockState blockState, LootContext.Builder builder) {
        LOGGER.debug("CelibBlockLink.getDrops");
        Level level = builder.getLevel();
        BlockPos blockPos = new BlockPos(builder.getParameter(LootContextParams.ORIGIN).subtract(new Vec3(0.5, 0.5, 0.5)));
        ItemStack tool = builder.getParameter(LootContextParams.TOOL);
        Entity entity = builder.getOptionalParameter(LootContextParams.THIS_ENTITY);
        return getBridge().getDrops(level, blockState, blockPos, tool, entity, builder);
    }

    @Override
    public void playerWillDestroy(Level level, BlockPos blockPos, BlockState blockState, Player player) {
        LOGGER.debug("CelibBlockLink.playerWillDestroy");
        getBridge().onBreakBlockByPlayer(level, blockPos, blockState, player);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        LOGGER.debug("CelibBlockLink.createBlockStateDefinition");
        getBridge().appendBlockStateProperties(builder);
    }
}
