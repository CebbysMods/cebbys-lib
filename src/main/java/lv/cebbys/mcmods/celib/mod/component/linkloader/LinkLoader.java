package lv.cebbys.mcmods.celib.mod.component.linkloader;

import lv.cebbys.mcmods.celib.api.component.block.CelibBlock;
import lv.cebbys.mcmods.celib.api.function.ExceptionalFunction;
import lv.cebbys.mcmods.celib.mod.exception.LinkException;
import net.minecraft.SharedConstants;
import net.minecraft.world.level.block.Block;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Constructor;
import java.util.Arrays;

public class LinkLoader {
    private static final Logger LOGGER = LoggerFactory.getLogger(LinkLoader.class);

    public static ExceptionalFunction<CelibBlock, Block, ?> CELIB_BLOCK_LINK;

    public static void load(LinkType type) {
        SharedConstants.tryDetectVersion();
        String minecraftVersion = SharedConstants.getCurrentVersion().getReleaseTarget();
        LinkVersion version = LinkVersion.toLinkVersion(minecraftVersion);
        if (LinkVersion.INVALID.equals(version)) {
            LOGGER.error("Invalid minecraft version loaded {}", minecraftVersion);
            throw new LinkException("Invalid minecraft version loaded " + minecraftVersion);
        }
        String packagePrefix = "lv.cebbys.mcmods.celib.link." + version.toPackagePart() + ".";
        switch (type) {
            case COMMON -> loadCommon(packagePrefix);
            case CLIENT -> loadClient(packagePrefix);
            case SERVER -> loadServer(packagePrefix);
        }
    }

    private static void loadCommon(String packagePrefix) {
        CELIB_BLOCK_LINK = getCelibBlockLink(packagePrefix);
    }

    private static void loadClient(String packagePrefix) {

    }

    private static void loadServer(String packagePrefix) {

    }

    private static ExceptionalFunction<CelibBlock, Block, ?> getCelibBlockLink(String packagePrefix) {
        Class<?> blockLinkClass = loadClass(packagePrefix, "CelibBlockLink");
        Constructor<?> constructor = loadConstructor(blockLinkClass, CelibBlock.class);
        return celibBlock -> (Block) constructor.newInstance(celibBlock);
    }

    private static Class<?> loadClass(String prefix, String suffix) {
        String className = prefix + suffix;
        try {
            return LinkLoader.class.getClassLoader().loadClass(className);
        } catch (Exception e) {
            LOGGER.error("Failed to load link class: " + className, e);
            throw new LinkException("Failed to load link class: " + className, e);
        }
    }

    private static Constructor<?> loadConstructor(Class<?> clazz, Class<?>... params) {
        try {
            return clazz.getConstructor(params);
        } catch (Exception e) {
            LOGGER.error("Failed to load " + clazz.getName() + " constructor " + Arrays.toString(params));
            throw new LinkException("Failed to load " + clazz.getName() + " constructor " + Arrays.toString(params));
        }
    }
}
