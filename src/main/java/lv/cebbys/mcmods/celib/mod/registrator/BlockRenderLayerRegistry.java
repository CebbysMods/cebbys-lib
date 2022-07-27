package lv.cebbys.mcmods.celib.mod.registrator;

import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.block.Block;

import java.util.HashMap;

public class BlockRenderLayerRegistry {

    private static final HashMap<Block, RenderType> RENDER_LAYERS;

    static {
        RENDER_LAYERS = new HashMap<>();
    }

    public static void registerLayer(Block block, RenderType layer) {
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
