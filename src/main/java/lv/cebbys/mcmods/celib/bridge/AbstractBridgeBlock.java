package lv.cebbys.mcmods.celib.bridge;

import lombok.NonNull;
import lv.cebbys.mcmods.celib.api.component.block.CelibBlock;
import lv.cebbys.mcmods.celib.api.component.block.CelibBlockProperties;
import lv.cebbys.mcmods.celib.mod.utility.lock.ConcurrentLock;
import net.minecraft.world.level.block.Block;

public class AbstractBridgeBlock extends Block {
    private static final ConcurrentLock<CelibBlock> LOCK = new ConcurrentLock<>();
    private final CelibBlock block;

    public AbstractBridgeBlock(@NonNull CelibBlock celibBlock) {
        super(lockTemporalBridge(celibBlock));
        block = celibBlock;
        unlockTemporalBridge();
    }

    protected final @NonNull CelibBlock getBridge() {
        return block == null ? LOCK.get() : block;
    }

    protected final CelibBlockProperties getProperties() {
        return getBridge().getCelibBlockProperties();
    }

    private static Properties lockTemporalBridge(CelibBlock block) {
        LOCK.setAndLock(block);
        return block.getCelibBlockProperties().asBlockProperties();
    }

    private static void unlockTemporalBridge() {
        LOCK.clearAndUnlock();
    }
}
