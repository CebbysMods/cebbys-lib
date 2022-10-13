package lv.cebbys.mcmods.celib.bridge.v1_19;

import lombok.NonNull;
import lv.cebbys.mcmods.celib.api.component.block.CelibBlock;
import lv.cebbys.mcmods.celib.api.component.block.CelibBlockProperties;
import lv.cebbys.mcmods.celib.mod.utility.lock.ConcurrentLock;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class CelibBridgeBlock extends Block {
    private static final String BRIDGE = CelibBridgeBlock.class.getSimpleName();
    private static final Logger LOGGER = LoggerFactory.getLogger(CelibBridgeBlock.class);

    private static final ConcurrentLock<CelibBlock> LOCK = new ConcurrentLock<>();
    private final CelibBlock block;

    public CelibBridgeBlock(@NotNull CelibBlock block) {
        super(lockTemporalBridge(block));
        this.block = block;
        unlockTemporalBridge();
    }

    // #############
    // Animate ticks
    // #############

    @Override
    public void animateTick(BlockState blockState, Level level, BlockPos blockPos, RandomSource randomSource) {
//        LOGGER.info("{}.animateTick", BRIDGE);
        if (getProperties().canBeAnimated(blockState)) {
            getBridge().onAnimateTick(blockState, level, blockPos, randomSource);
        }
    }

    // ############
    // Random ticks
    // ############

    @Override
    public void randomTick(BlockState blockState, ServerLevel serverLevel, BlockPos blockPos, RandomSource randomSource) {
        LOGGER.info("{}.randomTick", BRIDGE);
        if (getProperties().canBeTickedRandomly(blockState)) {
            getBridge().onRandomTick(blockState, serverLevel, blockPos, randomSource);
        }
    }

    @Override
    public boolean isRandomlyTicking(BlockState blockState) {
//        LOGGER.info("{}.isRandomlyTicking", BRIDGE);
        return getProperties().canBeTickedRandomly(blockState);
    }

    // ############
    // Update ticks
    // ############

    @Override
    public void tick(BlockState blockState, ServerLevel serverLevel, BlockPos blockPos, RandomSource randomSource) {
//        LOGGER.info("{}.tick", BRIDGE);
        if (getProperties().canBeTicked(blockState)) {
            getBridge().onUpdateTick(blockState, serverLevel, blockPos, randomSource);
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        LOGGER.info("{}.createBlockStateDefinition", BRIDGE);
        getBridge().appendBlockStateProperties(builder);
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
        getBridge().afterBreakByPlayerEvent(levelAccessor, blockPos, blockState);
    }

    @Deprecated
    @Override
    public List<ItemStack> getDrops(BlockState blockState, LootContext.Builder builder) {
        LOGGER.info("{}.getDrops", BRIDGE);
        Level level = builder.getLevel();
        BlockPos blockPos = new BlockPos(builder.getParameter(LootContextParams.ORIGIN).subtract(new Vec3(0.5, 0.5, 0.5)));
        ItemStack tool = builder.getParameter(LootContextParams.TOOL);
        Entity entity = builder.getOptionalParameter(LootContextParams.THIS_ENTITY);
        return getBridge().getDrops(level, blockState, blockPos, tool, entity, builder);
    }

    @Override
    public float getDestroyProgress(BlockState blockState, Player player, BlockGetter blockGetter, BlockPos blockPos) {
        getBridge().whileBreakingEvent(player.getLevel(), blockPos, blockState, player);
        return getProperties().getBreakingSpeed(blockState, player, blockGetter, blockPos);
    }

    @Override
    public void playerWillDestroy(Level level, BlockPos blockPos, BlockState blockState, Player player) {
        getBridge().onBreakByPlayerEvent(level, blockPos, blockState, player);
    }

    @Override
    protected void popExperience(ServerLevel serverLevel, BlockPos blockPos, int i) {
        LOGGER.info("{}.popExperience", BRIDGE);
        super.popExperience(serverLevel, blockPos, i);
    }

    @Override
    public void wasExploded(Level level, BlockPos blockPos, Explosion explosion) {
        getBridge().onBrokenByExplosionEvent(level, blockPos, level.getBlockState(blockPos), explosion);
    }

    @Override
    public void stepOn(Level level, BlockPos blockPos, BlockState blockState, Entity entity) {
        LOGGER.info("{}.stepOn", BRIDGE);
    }

    @Override
    public void playerDestroy(Level level, Player player, BlockPos blockPos, BlockState blockState, @Nullable BlockEntity blockEntity, ItemStack itemStack) {
        LOGGER.info("{}.playerDestroy", BRIDGE);
        super.playerDestroy(level, player, blockPos, blockState, blockEntity, itemStack);
    }

    @Override
    public void onRemove(BlockState blockState, Level level, BlockPos blockPos, BlockState blockState2, boolean bl) {
        LOGGER.info("{}.onRemove", BRIDGE);
        super.onRemove(blockState, level, blockPos, blockState2, bl);
    }

    @Override
    public void spawnAfterBreak(BlockState blockState, ServerLevel serverLevel, BlockPos blockPos, ItemStack itemStack, boolean bl) {
        getBridge().afterBreakEvent(serverLevel, blockPos, blockState, itemStack, bl);
    }

    // ##############
    // Helper methods
    // ##############

    private @NonNull CelibBlock getBridge() {
        return block == null ? LOCK.get() : block;
    }

    private CelibBlockProperties getProperties() {
        return getBridge().getCelibBlockProperties();
    }

    private static Properties lockTemporalBridge(CelibBlock block) {
        LOGGER.info("{}.constructor", BRIDGE);
        LOCK.setAndLock(block);
        return block.getCelibBlockProperties().asBlockProperties();
    }

    private static void unlockTemporalBridge() {
        LOCK.clearAndUnlock();
    }
}
