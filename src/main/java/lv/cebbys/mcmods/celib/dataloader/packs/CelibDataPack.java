package lv.cebbys.mcmods.celib.dataloader.packs;

import lv.cebbys.mcmods.celib.dataloader.resources.CelibResource;
import net.minecraft.resource.ResourcePackProfile;
import net.minecraft.resource.ResourceType;
import net.minecraft.util.Identifier;

import java.util.Map;
import java.util.Set;

public class CelibDataPack extends CelibResourcePack {

    public CelibDataPack(Identifier id, String name, Set<String> namespaces, Map<Identifier, CelibResource<?>> resources) {
        super(id, name, ResourceType.SERVER_DATA, namespaces, resources);
    }

    @Override
    public ResourcePackProfile getResourcePack(ResourcePackProfile.Factory factory) {
        return ResourcePackProfile.of(
                this.id.toString(), true, () -> this, factory,
                ResourcePackProfile.InsertionPosition.BOTTOM, RESOURCE_PACK_SOURCE
        );
    }
}
