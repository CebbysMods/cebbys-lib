package lv.cebbys.mcmods.celib.mixins;

import com.google.common.collect.ImmutableSet;
import lv.cebbys.mcmods.celib.dataloader.utils.CelibAssetPackProvider;
import lv.cebbys.mcmods.celib.dataloader.utils.CelibDataPackProvider;
import lv.cebbys.mcmods.celib.loggers.CelibLogger;

import net.fabricmc.api.EnvType;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.resource.ClientBuiltinResourcePackProvider;
import net.minecraft.resource.ResourcePackManager;
import org.apache.commons.lang3.ArrayUtils;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.Arrays;

@Mixin(ResourcePackManager.class)
public class MixinResourcePackManager {
    private static final CelibAssetPackProvider CLIENT_ASSET_PROVIDER = new CelibAssetPackProvider();
    private static final CelibDataPackProvider SERVER_ASSET_PROVIDER = new CelibDataPackProvider();

    @Redirect(method = "<init>", at = @At(value = "INVOKE", target = "Lcom/google/common/collect/ImmutableSet;copyOf([Ljava/lang/Object;)Lcom/google/common/collect/ImmutableSet;"))
    private <E> ImmutableSet<Object> appendPacks(E[] elements) {
        Object addedPack;
        if (FabricLoader.getInstance().getEnvironmentType() == EnvType.SERVER) {
            addedPack = SERVER_ASSET_PROVIDER;
        } else {
            boolean isForClient = Arrays.stream(elements).anyMatch(element -> element instanceof ClientBuiltinResourcePackProvider);
            addedPack = isForClient ? CLIENT_ASSET_PROVIDER : SERVER_ASSET_PROVIDER;
        }
        return ImmutableSet.copyOf(ArrayUtils.add(elements, addedPack));
    }
}
