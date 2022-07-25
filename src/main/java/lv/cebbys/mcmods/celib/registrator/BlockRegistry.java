package lv.cebbys.mcmods.celib.registrator;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class BlockRegistry {

    @Deprecated
    public static void registerBlock(Block block, String modId, String blockId) {
        Registry.register(
                Registry.BLOCK,
                new ResourceLocation(modId, blockId + "_block"),
                block
        );
    }

    @Deprecated
    public static void registerBlockWithItem(Block block, String modId, String blockId, Item.Properties properties) {
        registerBlock(block, modId, blockId);
        Registry.register(
                Registry.ITEM,
                new ResourceLocation(modId, blockId + "_block"),
                new BlockItem(block, properties)
        );
    }

    @Deprecated
    public static void registerBlockWithItem(Block block, String modId, String blockId, RenderType layer, Item.Properties itemSettings) {
        registerBlockWithItem(block, modId, blockId, itemSettings);
        BlockRenderLayerRegistry.registerLayer(block, layer);
    }

    @Deprecated
    public static void registerBlock(Block block, String modId, String blockId, RenderType layer) {
        registerBlock(block, modId, blockId);
        BlockRenderLayerRegistry.registerLayer(block, layer);
    }
}
