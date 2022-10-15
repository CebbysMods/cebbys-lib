package lv.cebbys.mcmods.celib.api.component.block;

import lv.cebbys.mcmods.celib.api.component.event.block.CelibBlockClientEvents;
import lv.cebbys.mcmods.celib.api.component.event.block.CelibBlockServerEvents;
import lv.cebbys.mcmods.celib.api.component.item.CelibItem;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;

public abstract class CelibBlock implements CelibBlockClientEvents, CelibBlockServerEvents {
    public static final String BRIDGE = CelibBlock.class.getSimpleName();
    public static final Logger LOGGER = LoggerFactory.getLogger(CelibBlock.class);

    public abstract @NotNull Block asBlock();

    public abstract @Nullable CelibItem asCelibItem();

    public @Nullable Item asItem() {
        CelibItem item;
        if ((item = asCelibItem()) == null) return null;
        return item.asItem();
    }

    public abstract CelibBlockProperties getCelibBlockProperties();

    public abstract void appendBlockStateProperties(StateDefinition.Builder<Block, BlockState> builder);

    public abstract ResourceLocation getLootTable(Level level, BlockPos blockPos, ItemStack tool, Entity entity);

    public abstract BlockState getDefaultState(BlockState defaultBlockState);

    public void onAnimateTick(BlockState blockState, Level level, BlockPos blockPos, RandomSource randomSource) {

    }

    public void onUpdateTick(BlockState blockState, ServerLevel serverLevel, BlockPos blockPos, RandomSource randomSource) {

    }

    public void onRandomTick(BlockState blockState, ServerLevel serverLevel, BlockPos blockPos, RandomSource randomSource) {

    }

    public List<ItemStack> getDrops(Level level, BlockState blockState, BlockPos blockPos, ItemStack tool, @Nullable Entity entity, LootContext.Builder builder) {
        ResourceLocation resourceLocation = getLootTable(level, blockPos, tool, entity);
        if (resourceLocation == BuiltInLootTables.EMPTY) {
            return Collections.emptyList();
        } else {
            LootContext lootContext = builder.withParameter(LootContextParams.BLOCK_STATE, blockState).create(LootContextParamSets.BLOCK);
            ServerLevel serverLevel = lootContext.getLevel();
            LootTable lootTable = serverLevel.getServer().getLootTables().get(resourceLocation);
            return lootTable.getRandomItems(lootContext);
        }
    }

    // #####################
    // Block breaking events
    // #####################

    public void onBrokenByExplosionEvent(Level level, BlockPos pos, BlockState state, Explosion explosion) {
        LOGGER.info("{}.onBrokenByExplosionEvent", BRIDGE);
    }

    // #############
    // Entity Events
    // #############
}
