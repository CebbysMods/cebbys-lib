package lv.cebbys.mcmods.celib.utilities;

import net.minecraft.block.Block;
import net.minecraft.item.Item;

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
