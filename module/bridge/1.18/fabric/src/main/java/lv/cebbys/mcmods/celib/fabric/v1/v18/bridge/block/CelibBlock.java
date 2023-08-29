package lv.cebbys.mcmods.celib.fabric.v1.v18.bridge.block;

import lv.cebbys.mcmods.celib.api.block.ICelibBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Material;

public class CelibBlock extends Block {
    public CelibBlock(ICelibBlock celibBlock) {
        super(Properties.of(Material.STONE));
    }
}
