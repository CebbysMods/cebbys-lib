package lv.cebbys.mcmods.celib.dataloader.packs;

import com.google.gson.JsonObject;
import lv.cebbys.mcmods.celib.Celib;
import lv.cebbys.mcmods.celib.dataloader.resources.CelibResource;
import lv.cebbys.mcmods.celib.dataloader.resources.InputStreamResource;
import lv.cebbys.mcmods.celib.dataloader.resources.JsonObjectResource;
import lv.cebbys.mcmods.celib.loggers.CelibLogger;
import net.minecraft.resource.ResourcePack;
import net.minecraft.resource.ResourcePackProfile;
import net.minecraft.resource.ResourcePackSource;
import net.minecraft.resource.ResourceType;
import net.minecraft.resource.metadata.ResourceMetadataReader;
import net.minecraft.util.Identifier;
import org.apache.commons.io.input.NullInputStream;
import org.jetbrains.annotations.Nullable;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;

@SuppressWarnings("unchecked")
public abstract class CelibResourcePack implements ResourcePack {
    public static final Identifier PACK_MCMETA_IDENTIFIER = new Identifier(Celib.MODID, "celib_resource_pack_meta");
    public static final Identifier PACK_ICON_IDENTIFIER = new Identifier(Celib.MODID, "celib_resource_pack_icon");
    public static final ResourcePackSource RESOURCE_PACK_SOURCE = ResourcePackSource.nameAndSource("Celib (generated)");

    public abstract ResourcePackProfile getResourcePack(ResourcePackProfile.Factory factory);

    protected final String name;
    protected final Identifier id;
    protected final ResourceType type;
    protected final Set<String> namespaces;
    protected final InputStreamResource image;
    protected final CelibResource<JsonObject> metadata;
    protected final Map<Identifier, CelibResource<?>> resources;

    protected CelibResourcePack(Identifier id, String name, ResourceType type, Set<String> namespaces, Map<Identifier, CelibResource<?>> resources) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.resources = resources;
        this.namespaces = namespaces;
        this.image = (InputStreamResource) this.resources.get(PACK_ICON_IDENTIFIER);
        this.metadata = (JsonObjectResource) this.resources.get(PACK_MCMETA_IDENTIFIER);
    }

    @Nullable
    @Override
    public InputStream openRoot(String fileName) throws IOException {
        CelibLogger.info("Opening in root " + fileName);
        if (fileName.equals("pack.mcmeta")) return this.metadata.getInputStream();
        if (fileName.equals(("pack.png"))) return this.image.getInputStream();
        return new NullInputStream(0);
    }

    @Override
    public InputStream open(ResourceType type, Identifier id) throws IOException {
        if (!(contains(type, id))) throw new FileNotFoundException(id.getPath());
        return this.resources.get(id).getInputStream();
    }

    @Override
    public Collection<Identifier> findResources(ResourceType type, String namespace, String prefix, int maxDepth, Predicate<String> pathFilter) {
        if (this.type != type) return new HashSet<>();
        Set<Identifier> keys = new HashSet<>(this.resources.keySet());
        keys.removeIf(id -> !id.getPath().startsWith(prefix) || !pathFilter.test(id.getPath()));
        return keys;
    }

    @Override
    public boolean contains(ResourceType type, Identifier id) {
        return this.type == type && this.resources.containsKey(id);
    }

    @Override
    public Set<String> getNamespaces(ResourceType type) {
        return this.namespaces;
    }

    @Nullable
    @Override
    public <R> R parseMetadata(ResourceMetadataReader<R> metaReader) throws IOException {
        JsonObject meta = metadata.getData();
        String key = metaReader.getKey();
        return meta.has(key)
                ? metaReader.fromJson(meta.getAsJsonObject(key))
                : null;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void close() {
    }
}
