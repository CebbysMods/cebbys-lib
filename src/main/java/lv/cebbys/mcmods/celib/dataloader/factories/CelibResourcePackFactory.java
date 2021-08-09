package lv.cebbys.mcmods.celib.dataloader.factories;

import lv.cebbys.mcmods.celib.dataloader.factories.old.CelibResourceFactory;
import lv.cebbys.mcmods.celib.dataloader.packs.CelibResourcePack;
import lv.cebbys.mcmods.celib.dataloader.resources.CelibResource;
import net.minecraft.resource.ResourceType;
import net.minecraft.util.Identifier;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

public abstract class CelibResourcePackFactory {

    protected final String name;
    protected final Identifier id;
    protected final ResourceType type;
    protected final Set<String> namespaces;
    protected final Map<Identifier, CelibResource<?>> resources;

    protected CelibResourcePackFactory(Identifier id, String name, ResourceType type) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.resources = new HashMap<>();
        this.namespaces = new HashSet<>();
    }

    public void addPackIcon(Consumer<PackIconFactory> icon) {
        this.addResource(CelibResourcePack.PACK_ICON_IDENTIFIER, icon, new PackIconFactory());
    }

    public void addPackMeta(Consumer<PackMcmetaFactory> mcmeta) {
        this.addResource(CelibResourcePack.PACK_MCMETA_IDENTIFIER, mcmeta, new PackMcmetaFactory());
    }

    protected <T extends CelibResourceFactory> void addResource(Identifier id, Consumer<T> factory, T construct) {
        factory.accept(construct);
        this.addResource(id, construct.createResource());
    }

    protected <T extends CelibResourceFactory> void addResource(Identifier id, CelibResource<?> resource) {
        this.namespaces.add(id.getNamespace());
        this.resources.put(id, resource);
    }

    protected Identifier wrap(String prefix, Identifier id, String suffix) {
        return new Identifier(id.getNamespace(), String.format("%s%s%s", prefix, id.getPath(), suffix));
    }

    protected Identifier prefix(String prefix, Identifier id) {
        return new Identifier(id.getNamespace(), String.format("%s%s", prefix, id.getPath()));
    }

    protected Identifier suffix(Identifier id, String suffix) {
        return new Identifier(id.getNamespace(), String.format("%s%s", id.getPath(), suffix));
    }
}
