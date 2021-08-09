package lv.cebbys.mcmods.celib.dataloader.builders;

import lv.cebbys.mcmods.celib.dataloader.builders.asset.BlockStateBuilder;
import lv.cebbys.mcmods.celib.dataloader.builders.asset.BlockStateBuilder.VariantKey;
import lv.cebbys.mcmods.celib.dataloader.builders.asset.MultipartBlockState;
import lv.cebbys.mcmods.celib.dataloader.builders.asset.VariantBlockStateBuilder;
import lv.cebbys.mcmods.celib.loggers.CelibLogger;
import net.minecraft.util.Identifier;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class AssetPackBuilder {

    public void addVariantBlockState(Identifier path, Consumer<VariantBlockStateBuilder> blockstate) {
        VariantBlockStateBuilder builder = new VariantBlockStateBuilder();
        blockstate.accept(builder);
    }

    public void addMultipartBlockState(Identifier path, Consumer<MultipartBlockState> blockstate) {
        MultipartBlockState builder = new MultipartBlockState();
        blockstate.accept(builder);
    }


    public static void main(String... args) {
        AssetPackBuilder b = new AssetPackBuilder();
        b.addMultipartBlockState(null, state -> {
            state.when().or().or().apply();
            CelibLogger.info(state.toJson());
        });
    }

}
