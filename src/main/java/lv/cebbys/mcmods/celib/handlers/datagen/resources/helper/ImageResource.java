package lv.cebbys.mcmods.celib.handlers.datagen.resources.helper;

import lv.cebbys.mcmods.celib.handlers.datagen.DataType;
import lv.cebbys.mcmods.celib.loggers.CelibLogger;
import net.minecraft.util.Identifier;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public abstract class ImageResource<T> extends CelibResource<T> {

    protected abstract Identifier resourcePath();

    @Override
    public DataType dataType() {
        return DataType.ASSETS;
    }

    public InputStream toImageStream() {
        Identifier path = this.resourcePath();
        String ri = "assets/" + path.getNamespace() + "/" + path.getPath();
        byte[] data = new byte[0];
        try(InputStream in = ImageResource.class.getResourceAsStream(ri);
            ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            if(in != null) {
                byte[] buffer = new byte[1024];
                int length;
                while ((length = in.read(buffer)) != -1) {
                    out.write(buffer, 0, length);
                }
                data = out.toByteArray();
            }
        } catch (Exception e) {
            CelibLogger.error(e,"Image stream parsing failed");
        }
        return new ByteArrayInputStream(data);
    }

    @Override
    public InputStream toStream() {
        return this.toImageStream();
    }
}
