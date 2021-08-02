package lv.cebbys.mcmods.celib.dataloader.packs;

import lv.cebbys.mcmods.celib.dataloader.resources.CelibResource;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.resource.ResourcePackProfile;
import net.minecraft.resource.ResourceType;
import net.minecraft.util.Identifier;

import java.util.Map;
import java.util.Set;

@Environment(EnvType.CLIENT)
public class CelibAssetPack extends CelibResourcePack {
    public CelibAssetPack(Identifier id, String name, Set<String> namespaces, Map<Identifier, CelibResource<?>> resources) {
        super(id, name, ResourceType.CLIENT_RESOURCES, namespaces, resources);
    }

    @Override
    public ResourcePackProfile getResourcePack(ResourcePackProfile.Factory factory) {
        return ResourcePackProfile.of(
                this.id.toString(), false, () -> this, factory,
                ResourcePackProfile.InsertionPosition.BOTTOM, RESOURCE_PACK_SOURCE
        );
    }
}
