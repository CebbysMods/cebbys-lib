package lv.cebbys.celib.mixins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import lv.cebbys.celib.loggers.CelibLogger;

import org.spongepowered.asm.mixin.injection.At;

import net.minecraft.resource.DataPackSettings;
import net.minecraft.resource.ResourcePackManager;
import net.minecraft.server.MinecraftServer;

@Mixin(value = MinecraftServer.class, priority = 1001)
public class MinecraftServerMixin {

	@Inject(method = "loadDataPacks", at = @At(value = "HEAD"))
	private static void loadDataPacks(ResourcePackManager resourcePackManager, DataPackSettings dataPackSettings,
			boolean safeMode, CallbackInfoReturnable<DataPackSettings> info) {
		CelibLogger.flog("Loading datapack with manager %s", resourcePackManager.toString());
	}

}
