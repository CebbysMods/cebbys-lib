from typing import TypedDict as _TypedDict

class VersioningFabricResource(_TypedDict):
    fabric_version:str
    loader_version:str
    
VersioningLoaderResource = VersioningFabricResource

class VersioningResource(_TypedDict):
    loom:str
    forge_gradle:str
    fabric:dict[str, VersioningFabricResource]