from celib.dsl.core.type.enum import ModLoader as _ModLoader
from celib.dsl.core.type import MinecraftVersion as _MinecraftVersion

class BuildGradleModLoaderSettings:
    def __init__(self, mod_loader:_ModLoader, minecraft_version:_MinecraftVersion) -> None:
        self.minecraft_version = minecraft_version
        self.mod_loader = mod_loader
    

class BuildGradleFabricSettings(BuildGradleModLoaderSettings):
    def __init__(self, loom:str, minecraft: _MinecraftVersion, fabric:str, loader:str) -> None:
        super().__init__(_ModLoader.FABRIC, minecraft)
        self.fabric_version = fabric
        self.loader_version = loader
        self.loom_version = loom


class BuildGradleForgeSettings(BuildGradleModLoaderSettings):
    def __init__(self, minecraft_version: _MinecraftVersion) -> None:
        super().__init__(_ModLoader.FORGE, minecraft_version)


class BuildGradleQuiltSettings(BuildGradleModLoaderSettings):
    def __init__(self, minecraft_version: _MinecraftVersion) -> None:
        super().__init__(_ModLoader.QUILT, minecraft_version)

