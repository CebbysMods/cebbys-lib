package lv.cebbys.mcmods.celib.core.mod.bridge.type;

import lv.cebbys.mcmods.celib.core.api.function.ExceptionalFunction;
import lv.cebbys.mcmods.celib.core.bridge.BridgeFactories;
import lv.cebbys.mcmods.celib.core.mod.bridge.BridgeVersion;
import lv.cebbys.mcmods.celib.core.mod.exception.CelibBridgeException;
import net.minecraft.SharedConstants;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Constructor;

public abstract class AbstractBridgeFactory<I, O> {
    private final String simpleBridgeClassName;
    private final Class<I> constructorParameter;
    private ExceptionalFunction<I, O, RuntimeException> factory;
    private Class<?> bridgeClass;

    protected AbstractBridgeFactory(@NotNull String simpleBridgeClassName, @NotNull Class<I> constructorParameter) {
        this.simpleBridgeClassName = simpleBridgeClassName;
        this.constructorParameter = constructorParameter;
    }

    @SuppressWarnings("all")
    public O create(I param) {
        if (factory == null) {
            Constructor<?> constructor = getBridgeConstructor();
            factory = (I p) -> {
                try {
                    return (O) constructor.newInstance(p);
                } catch (Exception e) {
                    Throwable cause = e.getCause();
                    RuntimeException out = new RuntimeException(cause.getMessage(), cause.getCause());
                    out.setStackTrace(cause.getStackTrace());
                    throw out;
                }
            };
        }
        return factory.apply(param);
    }

    public boolean isInstance(Block block) {
        return getBridgeClass().isInstance(block);
    }


    private BridgeVersion getBridgeVersion() {
        String minecraftVersion;
        try {
            SharedConstants.tryDetectVersion();
            minecraftVersion = SharedConstants.getCurrentVersion().getReleaseTarget();
        } catch (Exception e) {
            throw new CelibBridgeException("Failed to load minecraft version", e);
        }
        BridgeVersion v = BridgeVersion.toLinkVersion(minecraftVersion);
        if (BridgeVersion.INVALID.equals(v)) {
            throw new CelibBridgeException("Invalid minecraft version loaded: '" + minecraftVersion + "'");
        }
        return v;
    }

    private String getBridgeClassName() {
        String basePackage;
        String versionPart;
        String classPart;
        try {
            basePackage = BridgeFactories.class.getPackageName();
            versionPart = getBridgeVersion().toPackagePart();
            classPart = simpleBridgeClassName;
        } catch (Exception e) {
            throw new CelibBridgeException("Failed to construct bridge class name", e);
        }
        return String.format("%s.%s.%s", basePackage, versionPart, classPart);
    }

    private Class<?> getBridgeClass() {
        if (bridgeClass == null) {
            String className = getBridgeClassName();
            try {
                bridgeClass = BridgeFactories.class.getClassLoader().loadClass(className);
            } catch (Exception e) {
                throw new CelibBridgeException("Failed to load bridge class: " + className, e);
            }
        }
        return bridgeClass;
    }

    private Constructor<?> getBridgeConstructor() {
        Class<?> clazz = getBridgeClass();
        try {
            return clazz.getConstructor(constructorParameter);
        } catch (Exception e) {
            throw new CelibBridgeException(String.format("Failed to load bridge constructor: %s(%s)",
                    clazz.getSimpleName(), constructorParameter.getSimpleName()
            ));
        }
    }
}
