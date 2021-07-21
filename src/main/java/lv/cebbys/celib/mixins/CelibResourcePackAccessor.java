package lv.cebbys.celib.mixins;

import java.util.Set;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import net.minecraft.resource.ResourcePackManager;
import net.minecraft.resource.ResourcePackProvider;

@Mixin(ResourcePackManager.class)
public interface CelibResourcePackAccessor {
    @Accessor(value = "providers") @Final
    Set<ResourcePackProvider> getPackProviders();
    
    @Accessor(value = "providers") @Final
    void setPackProviders(Set<ResourcePackProvider> manager);
}
