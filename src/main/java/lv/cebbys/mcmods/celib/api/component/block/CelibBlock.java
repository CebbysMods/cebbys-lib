package lv.cebbys.mcmods.celib.api.component.block;

import lv.cebbys.mcmods.celib.api.component.CelibItem;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.monster.piglin.PiglinAi;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;

public interface CelibBlock {
    @NotNull Block asBlock();

    @Nullable CelibItem asCelibItem();

    default @Nullable Item asItem() {
        CelibItem item;
        if ((item = asCelibItem()) == null) return null;
        return item.asItem();
    }

    CelibBlockProperties getCelibBlockProperties();

    void appendBlockStateProperties(StateDefinition.Builder<Block, BlockState> builder);

    ResourceLocation getLootTable(Level level, BlockPos blockPos, ItemStack tool, Entity entity);

    BlockState getDefaultState(BlockState defaultBlockState);

    default void animateTick(BlockState blockState, Level level, BlockPos blockPos, RandomSource randomSource) {

    }

    default void updateTick(BlockState blockState, ServerLevel serverLevel, BlockPos blockPos, RandomSource randomSource) {

    }

    default void randomTick(BlockState blockState, ServerLevel serverLevel, BlockPos blockPos, RandomSource randomSource) {

    }

    default List<ItemStack> getDrops(Level level, BlockState blockState, BlockPos blockPos, ItemStack tool, @Nullable Entity entity, LootContext.Builder builder) {
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

    default void spawnBlockBreakParticles(Level level, BlockPos blockPos, BlockState blockState, Player player) {
        level.levelEvent(player, 2001, blockPos, Block.getId(blockState));
    }

    default void onBreakBlockByPlayer(Level level, BlockPos blockPos, BlockState blockState, Player player) {
        spawnBlockBreakParticles(level, blockPos, blockState, player);
        if (blockState.is(BlockTags.GUARDED_BY_PIGLINS)) {
            PiglinAi.angerNearbyPiglins(player, false);
        }
        level.gameEvent(GameEvent.BLOCK_DESTROY, blockPos, GameEvent.Context.of(player, blockState));
    }
}
