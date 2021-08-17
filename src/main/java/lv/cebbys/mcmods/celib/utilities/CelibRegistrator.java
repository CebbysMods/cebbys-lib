package lv.cebbys.mcmods.celib.utilities;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import static lv.cebbys.mcmods.celib.utilities.CelibRegistryTypes.BlockWithItem;

public class CelibRegistrator {

    private final String modid;

    public CelibRegistrator(String modid) {
        this.modid = modid;
    }

    public Block registerBlock(String name, Block block) {
        return Registry.register(Registry.BLOCK, this.id(name), block);
    }

    public Item registerItem(String name, Item item) {
        return Registry.register(Registry.ITEM, this.id(name), item);
    }

    public BlockWithItem registerBlockWithItem(String blockName, Block block, String itemName, Item.Settings itemSettings) {
        return new BlockWithItem(
                this.registerBlock(blockName, block),
                this.registerItem(itemName, new BlockItem(block, itemSettings))
        );
    }

    private Identifier id(String id) {
        return new Identifier(this.modid, id);
    }
}
