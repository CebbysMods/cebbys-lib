package lv.cebbys.mcmods.celib.block.bridge.v1_19;

import lv.cebbys.mcmods.celib.block.api.CelibBlock;
import lv.cebbys.mcmods.celib.core.api.accessor.FieldAccessor;
import lv.cebbys.mcmods.celib.core.bridge.BridgeBlockEventHandler;
import lv.cebbys.mcmods.celib.core.bridge.BridgeBlockInterface;
import lv.cebbys.mcmods.celib.core.mod.utility.lock.ConcurrentLock;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public final class CelibBridgeBlock extends Block implements BridgeBlockInterface {
    private static final String BRIDGE = CelibBridgeBlock.class.getSimpleName();
    private static final Logger LOGGER = LoggerFactory.getLogger(CelibBridgeBlock.class);

    private static final ConcurrentLock<CelibBlock> LOCK = new ConcurrentLock<>();
    private final CelibBlock celibBlock;

    private final BridgeBlockEventHandler eventHandler;
    private final FieldAccessor<Block, StateDefinition<Block, BlockState>> stateDefinitionAccessor;

    private CelibBridgeBlock(@NotNull CelibBlock block) {
        super(block.getProperties().asBlockProperties());

        celibBlock = block;
        stateDefinitionAccessor = new FieldAccessor<>(Block.class, "stateDefinition");
        eventHandler = BridgeBlockEventHandler.createDefault(celibBlock);

        createBlockStateDefinition();
    }

    private void createBlockStateDefinition() {
        Builder<Block, BlockState> builder = new Builder<>(this);
        celibBlock.createBlockStateDefinition(builder);
        stateDefinitionAccessor.set(this, builder.create(Block::defaultBlockState, BlockState::new));
        this.registerDefaultState(stateDefinitionAccessor.get(this).any());
    }


    // #############
    // Animate ticks
    // #############

    @Override
    public void animateTick(BlockState blockState, Level level, BlockPos blockPos, RandomSource randomSource) {
//        LOGGER.info("{}.animateTick", BRIDGE);
        if (getProperties().canBeAnimated(blockState)) {
            getEndpoint().onAnimateTick(blockState, level, blockPos, randomSource);
        }
    }

    // ############
    // Random ticks
    // ############

    @Override
    public void randomTick(BlockState blockState, ServerLevel serverLevel, BlockPos blockPos, RandomSource randomSource) {
//        LOGGER.info("{}.randomTick", BRIDGE);
        if (getProperties().canBeTickedRandomly(blockState)) {
            getEndpoint().onRandomTick(blockState, serverLevel, blockPos, randomSource);
        }
    }

    @Override
    public boolean isRandomlyTicking(BlockState blockState) {
//        LOGGER.info("{}.isRandomlyTicking", BRIDGE);
        return celibBlock.getProperties().canBeTickedRandomly(blockState);
    }

    // ############
    // Update ticks
    // ############

    @Override
    public void tick(BlockState blockState, ServerLevel serverLevel, BlockPos blockPos, RandomSource randomSource) {
//        LOGGER.info("{}.tick", BRIDGE);
        if (getProperties().canBeTicked(blockState)) {
            getEndpoint().onUpdateTick(blockState, serverLevel, blockPos, randomSource);
        }
    }

    @Override
    protected void createBlockStateDefinition(Builder<Block, BlockState> builder) {
        LOGGER.info("{}.createBlockStateDefinition", BRIDGE);
        getEndpoint().appendBlockStateProperties(builder);
    }

    // #######################
    // Explosion event methods
    // #######################

    /**
     * Called after explosion. Method determines if on block broken by explosion there could be drops
     *
     * @param explosion explosion type
     * @return true if block can drop
     */
    @Override
    public boolean dropFromExplosion(Explosion explosion) {
        return getProperties().canDropFromExplosion(explosion);
    }


    // #####################
    // Block breaking events
    // #####################

    @Override
    public void destroy(LevelAccessor levelAccessor, BlockPos blockPos, BlockState blockState) {
//        getEndpoint().afterBreakByPlayerEvent(levelAccessor, blockPos, blockState);
    }

    @Override
    public List<ItemStack> getDrops(BlockState blockState, LootContext.Builder builder) {
        LOGGER.info("{}.getDrops", BRIDGE);
        Level level = builder.getLevel();
        BlockPos blockPos = new BlockPos(builder.getParameter(LootContextParams.ORIGIN).subtract(new Vec3(0.5, 0.5, 0.5)));
        ItemStack tool = builder.getParameter(LootContextParams.TOOL);
        Entity entity = builder.getOptionalParameter(LootContextParams.THIS_ENTITY);
        return getEndpoint().getDrops(level, blockState, blockPos, tool, entity, builder);
    }

    // ##############
    // Helper methods
    // ##############

    @Override
    public BridgeBlockEventHandler getBlockEventHandler() {
        return eventHandler;
    }
}
