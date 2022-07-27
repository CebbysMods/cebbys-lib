package lv.cebbys.mcmods.celib.mod.utilities;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class CelibRegistryTypes {

    public static class BlockWithItem {
        public final Block BLOCK;
        public final Item ITEM;

        public BlockWithItem(Block b, Item i) {
            this.BLOCK = b;
            this.ITEM = i;
        }
    }

}
