package lv.cebbys.mcmods.celib.handlers.datapack;

import java.io.File;
import java.util.Set;

import lv.cebbys.mcmods.celib.mixins.CelibResourcePackAccessor;
import net.minecraft.client.MinecraftClient;
import net.minecraft.resource.FileResourcePackProvider;
import net.minecraft.resource.ResourcePackProvider;
import net.minecraft.resource.ResourcePackSource;

public class DatapackHandler {
	
	public static void addDatapackProvider(File packsFolder, ResourcePackSource source) {
		CelibResourcePackAccessor manager = (CelibResourcePackAccessor) MinecraftClient.getInstance().getResourcePackManager();
		Set<ResourcePackProvider> providers = manager.getPackProviders();
		providers.add(new FileResourcePackProvider(packsFolder, source));
		manager.setPackProviders(providers);
	}
	
}
