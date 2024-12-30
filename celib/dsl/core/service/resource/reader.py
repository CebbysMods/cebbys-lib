from celib.dsl.core.type.resource import VersioningResource as _VersioningResource
from celib.dsl.core.type.gradle import BuildGradleModLoaderSettings as _BuildGradleModLoaderSettings
from celib.dsl.core.type.gradle import BuildGradleFabricSettings as _BuildGradleFabricSettings
from celib.dsl.core.type.enum import ModLoader as _ModLoader
from celib.dsl.core.type import MinecraftVersion as _MinecraftVersion
from typing import Any as _Any
from json import load as _load

def read_versioning_resource() -> _VersioningResource:
    with open("resources/versioning.json", "r") as file:
        data:dict[str, _Any] = _load(file)
        return _VersioningResource(data) # type: ignore
    
def read_parsed_versioning_resource(modules:list[tuple[_ModLoader, _MinecraftVersion]], resource:None|_VersioningResource = None):
    if resource == None:
        resource = read_versioning_resource()
    return [ _read_parsed_versioning_resource_entry(m[0], m[1], resource) for m in modules ]

def _read_parsed_versioning_resource_entry(loader:_ModLoader, minecraft:_MinecraftVersion, versioning:_VersioningResource) -> _BuildGradleModLoaderSettings:
    try:
        if _ModLoader.FABRIC == loader:
            fabric = versioning['fabric']
            version = str(minecraft)
            if version not in fabric:
                raise BaseException(f"Version '{minecraft}' config for '{loader}' not found in 'resources/versioning'")
            data = fabric[version]
            return _BuildGradleFabricSettings(
                versioning['loom'],
                minecraft,
                data['fabric_version'],
                data['loader_version']
            )
        else:
            raise BaseException(f"Generation not implemented for '{loader}'")
    except BaseException as e:
        raise BaseException(f"Failed to generate gradle 'build.gradle' file for '{loader}:{minecraft}'", e)