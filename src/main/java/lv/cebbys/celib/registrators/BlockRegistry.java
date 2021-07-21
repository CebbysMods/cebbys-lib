package lv.cebbys.celib.registrators;

import net.minecraft.block.Block;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class BlockRegistry {

	@Deprecated
    public static void registerBlock(Block block, String modId, String blockId) {
        Registry.register(
                Registry.BLOCK,
                new Identifier(modId, blockId + "_block"),
                block
        );
    }

	@Deprecated
    public static void registerBlockWithItem(Block block, String modId, String blockId, Item.Settings itemSettings) {
        registerBlock(block, modId, blockId);
        Registry.register(
                Registry.ITEM,
                new Identifier(modId, blockId + "_block"),
                new BlockItem(block, itemSettings)
        );
    }

	@Deprecated
    public static void registerBlockWithItem(Block block, String modId, String blockId, RenderLayer layer, Item.Settings itemSettings) {
        registerBlockWithItem(block, modId, blockId, itemSettings);
        BlockRenderLayerRegistry.registerLayer(block, layer);
    }

	@Deprecated
    public static void registerBlock(Block block, String modId, String blockId, RenderLayer layer) {
        registerBlock(block, modId, blockId);
        BlockRenderLayerRegistry.registerLayer(block, layer);
    }
}
