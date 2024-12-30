package lv.cebbys.mcmods.celib.api.v0.content.item;

import jakarta.annotation.Nonnull;
import lv.cebbys.mcmods.celib.api.v0.content.block.CelibBlock;
import lv.cebbys.mcmods.celib.api.v0.utility.lazy.MinecraftInstance;

public interface CelibItemStack<I extends CelibItem> extends MinecraftInstance {
    @Nonnull
    static <B extends CelibBlock> CelibItemStack<CelibBlockItem> ofBlock(B block) {
        return null;
    }

    @Nonnull
    static <B extends CelibBlock> CelibItemStack<CelibBlockItem> ofBlock(B block, int itemCount) {
        return null;
    }

    @Nonnull
    static <T extends CelibItem> CelibItemStack<T> of(T item) {
        return null;
    }

    @Nonnull
    static <T extends CelibItem> CelibItemStack<T> of(T item, int itemCount) {
        return null;
    }

    I getItem();

    int getItemCount();
}
