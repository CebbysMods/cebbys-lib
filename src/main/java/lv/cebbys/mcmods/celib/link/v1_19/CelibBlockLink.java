package lv.cebbys.mcmods.celib.link.v1_19;

import lv.cebbys.mcmods.celib.api.component.block.CelibBlock;
import lv.cebbys.mcmods.celib.api.component.block.CelibBlockProperties;
import lv.cebbys.mcmods.celib.mod.exception.LinkException;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
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
public class CelibBlockLink extends Block {
    private static final Logger LOGGER = LoggerFactory.getLogger(CelibBlockLink.class);
    private final CelibBlock link;
    private final CelibBlockProperties properties;

    private static CelibBlock temporalLink;

    private static BlockBehaviour.Properties createTemporalLink(CelibBlock block) {
        if (temporalLink != null) {
            throw new LinkException("CelibBlockLink temporal link is not null. Concurrent modification exception");
        }
        temporalLink = block;
        return block.getCelibBlockProperties().asBlockProperties();
    }

    public CelibBlockLink(@NotNull CelibBlock block) {
        super(createTemporalLink(block));
        link = block;
        properties = link.getCelibBlockProperties();
        registerDefaultState(link.getDefaultState(this.defaultBlockState()));
    }

    @Override
    public void animateTick(BlockState blockState, Level level, BlockPos blockPos, RandomSource randomSource) {
        LOGGER.debug("CelibBlockLink.animateTick");
        if (properties.canBeAnimated()) {
            link.animateTick(blockState, level, blockPos, randomSource);
        }
    }

    @Override
    public void randomTick(BlockState blockState, ServerLevel serverLevel, BlockPos blockPos, RandomSource randomSource) {
        LOGGER.debug("CelibBlockLink.randomTick");
        if (properties.canBeTickedRandomly()) {
            link.randomTick(blockState, serverLevel, blockPos, randomSource);
        }
    }

    @Override
    public void tick(BlockState blockState, ServerLevel serverLevel, BlockPos blockPos, RandomSource randomSource) {
        LOGGER.debug("CelibBlockLink.tick");
        if (properties.canBeTicked()) {
            link.updateTick(blockState, serverLevel, blockPos, randomSource);
        }
    }

    @Override
    public float getDestroyProgress(BlockState blockState, Player player, BlockGetter blockGetter, BlockPos blockPos) {
        LOGGER.debug("CelibBlockLink.getDestroyProgress");
        return properties.getBreakingSpeed(blockState, player, blockGetter, blockPos);
    }

    @Deprecated
    @Override
    public List<ItemStack> getDrops(BlockState blockState, LootContext.Builder builder) {
        LOGGER.debug("CelibBlockLink.getDrops");
        Level level = builder.getLevel();
        BlockPos blockPos = new BlockPos(builder.getParameter(LootContextParams.ORIGIN).subtract(new Vec3(0.5, 0.5, 0.5)));
        ItemStack tool = builder.getParameter(LootContextParams.TOOL);
        Entity entity = builder.getOptionalParameter(LootContextParams.THIS_ENTITY);
        return link.getDrops(level, blockState, blockPos, tool, entity, builder);
    }

    @Override
    public void playerWillDestroy(Level level, BlockPos blockPos, BlockState blockState, Player player) {
        LOGGER.debug("CelibBlockLink.playerWillDestroy");
        link.onBreakBlockByPlayer(level, blockPos, blockState, player);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        LOGGER.debug("CelibBlockLink.createBlockStateDefinition");
        // This is very bad workaround of issue where
        temporalLink.appendBlockStateProperties(builder);
        temporalLink = null;
    }
}
