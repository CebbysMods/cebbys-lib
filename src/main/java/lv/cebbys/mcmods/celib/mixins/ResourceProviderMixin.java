package lv.cebbys.mcmods.celib.mixins;

import com.google.common.collect.ImmutableSet;
import lv.cebbys.mcmods.celib.api.registries.CelibRegistries;
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
import java.util.List;
import java.util.stream.Collectors;

@Mixin(ResourcePackManager.class)
public class ResourceProviderMixin {

    @Redirect(method = "<init>", at = @At(value = "INVOKE", target = "Lcom/google/common/collect/ImmutableSet;copyOf([Ljava/lang/Object;)Lcom/google/common/collect/ImmutableSet;"))
    private <E> ImmutableSet<Object> appendCelibProviders(E[] elements) {
        Object[] providers;
        if (FabricLoader.getInstance().getEnvironmentType() == EnvType.SERVER) {
            CelibLogger.info("Registering server resource providers");
            List<Object> serverProviders = CelibRegistries.SERVER_RESOURCE_PROVIDERS.stream().collect(Collectors.toList());
            providers = new Object[serverProviders.size()];
            for (int i = 0; i < serverProviders.size(); i++) {
                providers[i] = serverProviders.get(i);
            }
        } else {
            CelibLogger.info("Registering client resource providers");
            boolean isForClient = Arrays.stream(elements).anyMatch(element -> element instanceof ClientBuiltinResourcePackProvider);
            if (isForClient) {
                CelibLogger.info("Registering for client providers");
                List<Object> serverProviders = CelibRegistries.CLIENT_RESOURCE_PROVIDERS.stream().collect(Collectors.toList());
                providers = new Object[serverProviders.size()];
                for (int i = 0; i < serverProviders.size(); i++) {
                    providers[i] = serverProviders.get(i);
                }
            } else {
                CelibLogger.info("Registering for common providers");
                List<Object> serverProviders = CelibRegistries.SERVER_RESOURCE_PROVIDERS.stream().collect(Collectors.toList());
                providers = new Object[serverProviders.size()];
                for (int i = 0; i < serverProviders.size(); i++) {
                    providers[i] = serverProviders.get(i);
                }
            }
        }
        return ImmutableSet.copyOf(ArrayUtils.addAll(elements, providers));
    }
}
