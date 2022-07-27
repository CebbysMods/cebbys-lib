package lv.cebbys.mcmods.celib.mod.utilities;

import lombok.AllArgsConstructor;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import static lv.cebbys.mcmods.celib.mod.utilities.CelibRegistryTypes.BlockWithItem;

@AllArgsConstructor
public class CelibRegistrator {
    private final String modid;

    public Block registerBlock(String name, Block block) {
        return Registry.register(Registry.BLOCK, this.id(name), block);
    }

    public Item registerItem(String name, Item item) {
        return Registry.register(Registry.ITEM, this.id(name), item);
    }

    public BlockWithItem registerBlockWithItem(String blockName, Block block, String itemName, Item.Properties itemSettings) {
        return new BlockWithItem(
                this.registerBlock(blockName, block),
                this.registerItem(itemName, new BlockItem(block, itemSettings))
        );
    }

    private ResourceLocation id(String id) {
        return new ResourceLocation(this.modid, id);
    }
}
