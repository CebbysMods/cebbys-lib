package lv.cebbys.mcmods.celib.dataloader.factories;

import lv.cebbys.mcmods.celib.dataloader.factories.old.CelibResourceFactory;
import lv.cebbys.mcmods.celib.dataloader.resources.InputStreamResource;
import lv.cebbys.mcmods.celib.loggers.CelibLogger;
import net.minecraft.util.Identifier;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class PackIconFactory implements CelibResourceFactory<InputStreamResource> {

    private Identifier id = null;

    public void image(Identifier pathToResource) {
        this.id = pathToResource;
    }

    @Override
    public InputStreamResource createResource() {
        byte[] image = new byte[0];
        if (id != null) {
            String path = String.format("assets/%s/%s", this.id.getNamespace(), this.id.getPath());
            try (InputStream in = this.getClass().getClassLoader().getResourceAsStream(path);
                 ByteArrayOutputStream out = new ByteArrayOutputStream()) {
                if(in != null) {
                    byte[] buffer = new byte[1024];
                    int length;
                    while ((length = in.read(buffer)) > -1) {
                        out.write(buffer, 0, length);
                    }
                    out.flush();
                    image = out.toByteArray();
                }
            } catch (IOException e) {
                CelibLogger.info(e, "Failed to load resource pack icon");
            }
        }
        final byte[] ret = image;
        return () -> new ByteArrayInputStream(ret);
    }
}
