package lv.cebbys.mcmods.celib.registrators;

import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.block.Block;
import net.minecraft.client.render.RenderLayer;

import java.util.HashMap;

public class BlockRenderLayerRegistry {

    private static HashMap<Block, RenderLayer> RENDER_LAYERS;

    static {
        RENDER_LAYERS = new HashMap<Block, RenderLayer>();
    }

    public static void registerLayer(Block block, RenderLayer layer) {
        RENDER_LAYERS.put(block, layer);
    }

    public static void registerLayersToInstance() {
        for (Block block : RENDER_LAYERS.keySet()) {
            BlockRenderLayerMap.INSTANCE.putBlock(block, RENDER_LAYERS.get(block));
        }
    }

    public static void clearRenderLayerRegistry() {
        RENDER_LAYERS.clear();
    }
}
