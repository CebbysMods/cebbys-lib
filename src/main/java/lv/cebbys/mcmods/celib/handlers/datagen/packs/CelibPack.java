package lv.cebbys.mcmods.celib.handlers.datagen.packs;

import com.google.gson.JsonObject;
import lv.cebbys.mcmods.celib.handlers.datagen.resources.common.PackIcon;
import lv.cebbys.mcmods.celib.handlers.datagen.resources.common.PackMeta;
import lv.cebbys.mcmods.celib.handlers.datagen.resources.helper.CelibResource;
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
import java.util.stream.Collectors;

public abstract class CelibPack implements ResourcePack {

    public static final ResourcePackSource RESOURCE_PACK_SOURCE = ResourcePackSource.nameAndSource("Celib (generated)");

    private final String name;
    private final PackIcon icon;
    private final PackMeta metadata;
    private final Set<String> namespaces;
    private final Map<Identifier, CelibResource<?>> resources;

    public abstract ResourcePackProfile getResourcePack(ResourcePackProfile.Factory factory);

    protected CelibPack(String packName, PackMeta meta, PackIcon image, Map<Identifier, CelibResource<?>> resources) {
        this.name = packName;
        this.metadata = meta;
        this.icon = image;
        this.resources = resources;
        this.namespaces = this.resources.keySet().stream().map(Identifier::toString).collect(Collectors.toSet());
        CelibLogger.info(this.resources);
        CelibLogger.info(this.namespaces);
    }

    @Override
    public InputStream openRoot(String fileName) {
        if(fileName.equals("pack.mcmeta")) return this.metadata.toStream();
        if(fileName.equals("pack.png")) return this.icon.toStream();
        return new NullInputStream(0);
    }

    @Override
    public InputStream open(ResourceType type, Identifier id) throws IOException {
        if(!(contains(type, id))) throw new FileNotFoundException(id.getPath());
        return this.resources.get(id).toStream();
    }

    @Override
    public Collection<Identifier> findResources(ResourceType type, String namespace, String prefix, int maxDepth, Predicate<String> pathFilter) {
        Set<Identifier> keys = new HashSet<>();
        this.resources.forEach((path, resource) -> {
            if(resource.dataType().belongsTo(type) && (path.getPath().startsWith(prefix) || pathFilter.test(path.getPath()))) {
                keys.add(path);
            }
        });
        return keys;
    }

    @Override
    public boolean contains(ResourceType type, Identifier id) {
        return this.resources.containsKey(id) && this.resources.get(id).dataType().belongsTo(type);
    }

    @Override
    public Set<String> getNamespaces(ResourceType type) {
        return this.namespaces;
    }

    @Nullable
    @Override
    public <T> T parseMetadata(ResourceMetadataReader<T> metaReader) throws IOException {
        JsonObject meta = this.metadata.toJson();
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
