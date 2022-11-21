package lv.cebbys.mcmods.celib.core.test;

import lv.cebbys.mcmods.celib.core.api.component.block.CelibBlockProperties;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;

public class TestBasicBlockProperties implements CelibBlockProperties {
    @Override
    public boolean canBeAnimated(BlockState state) {
        return true;
    }

    @Override
    public boolean canBeTicked(BlockState state) {
        return true;
    }

    @Override
    public boolean canBeTickedRandomly(BlockState state) {
        return true;
    }

    @Override
    public BlockBehaviour.Properties asBlockProperties() {
        return BlockBehaviour.Properties.of(Material.WOOD);
    }

    @Override
    public float getExplosionResistance() {
        return 10;
    }

    @Override
    public boolean canDropFromExplosion(Explosion explosion) {
        return true;
    }

    @Override
    public float getHardness(BlockState blockState, Player player, BlockGetter blockGetter, BlockPos blockPos) {
        return 10;
    }

    @Override
    public float getMiningSpeed(BlockState blockState, Player player, BlockGetter blockGetter, BlockPos blockPos) {
        return 1;
    }

    @Override
    public float getHarvestSpeed(BlockState blockState, Player player, BlockGetter blockGetter, BlockPos blockPos) {
        return 1;
    }
}
