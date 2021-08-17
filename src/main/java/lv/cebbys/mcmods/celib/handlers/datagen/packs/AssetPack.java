package lv.cebbys.mcmods.celib.handlers.datagen.packs;

import lv.cebbys.mcmods.celib.handlers.datagen.resources.common.PackIcon;
import lv.cebbys.mcmods.celib.handlers.datagen.resources.common.PackMeta;
import lv.cebbys.mcmods.celib.handlers.datagen.resources.helper.CelibResource;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.resource.ResourcePackProfile;
import net.minecraft.util.Identifier;

import java.util.Map;

@Environment(EnvType.CLIENT)
public class AssetPack extends CelibPack {

    public AssetPack(String packName, PackMeta meta, PackIcon image, Map<Identifier, CelibResource<?>> resources) {
        super(packName, meta, image, resources);
    }

    public ResourcePackProfile getResourcePack(ResourcePackProfile.Factory factory) {
        return ResourcePackProfile.of(
                this.getName(), false, () -> this, factory, ResourcePackProfile.InsertionPosition.BOTTOM,
                CelibPack.RESOURCE_PACK_SOURCE
        );
    }
}
