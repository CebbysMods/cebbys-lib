from celib.dsl.core.service.java.type.builder import JavaTypeBuilder as _Type
import celib.dsl.gen.constant.fabric.package as _FabricPackage
import celib.dsl.gen.constant.fabric.type as _FabricType
import celib.dsl.gen.constant.celib.package as _CelibPackage
import celib.dsl.gen.constant.celib.type as _CelibType
from celib.dsl.core.type.gradle import BuildGradleModLoaderSettings as _BuildGradleModLoaderSettings
from celib.dsl.core.model.java import JavaConstructor as _JavaConstructor
from celib.dsl.core.model.java import JavaFunction as _JavaFunction
from celib.dsl.core.model.java import JavaClass as _JavaClass
from celib.dsl.core.model.java import JavaType as _JavaType
from celib.dsl.core.type.enum import ModLoader as _ModLoader
from celib.dsl.core.type import MinecraftVersion as _MinecraftVersion

_1_20 = _MinecraftVersion(1, 20)
    

def generate_abstract_block(settings:_BuildGradleModLoaderSettings) -> _JavaClass:
    builder:_JavaClass = _JavaClass()
    
    minecraft = ".".join([f"v{n}" for n in settings.minecraft_version])
    package = f"{settings.mod_loader}.{minecraft}"
    
    block_type = _JavaType(
        f"lv.cebbys.mcmods.celib.bridge.{package}.component.block",
        "AbstractCelibBlock"
    )
    builder.set_type_name(block_type)
    builder.set_type("public abstract class")
    _set_block_extends(builder, settings)
    
    _set_block_constructors(builder, block_type, settings)
    _set_block_functions(builder, settings)
    
    return builder


def _set_block_extends(builder:_JavaClass, settings:_BuildGradleModLoaderSettings):
    match settings.mod_loader:
        case _ModLoader.FABRIC:
            builder.set_extends([_FabricType.Block()])
        case _:
            raise BaseException(f"Not implemented for '{settings.mod_loader}'")
        

def _set_block_constructors(builder:_JavaClass, block_type:_JavaType, settings:_BuildGradleModLoaderSettings):
    match settings.mod_loader:
        case _ModLoader.FABRIC:
            builder.set_constructors([
                _JavaConstructor(block_type, { "block": _CelibType.CelibBlock() }, [
                    "super(getProperties(block.getProperties()));"
                ])
            ])
        case _:
            raise BaseException(f"Not implemented for '{settings.mod_loader}'")
        
        
def _set_block_functions(builder:_JavaClass, settings:_BuildGradleModLoaderSettings):
    match settings.mod_loader:
        case _ModLoader.FABRIC:
            _set_fabric_functions(builder, settings)
        case _:
            raise BaseException(f"Not implemented for '{settings.mod_loader}'")

def _set_fabric_functions(builder:_JavaClass, settings:_BuildGradleModLoaderSettings):
    type_properties = _JavaType(None, "Properties")
    if settings.minecraft_version < _1_20:
        builder.add_import(_FabricType.MaterialColor())
        builder.set_functions([
            _JavaFunction(
                type_properties,
                "private static",
                "getProperties",
                {
                    "properties": _CelibType.CelibBlockProperties()
                },
                [
                    "var material = getMaterial(properties.getMaterial());",
                    "return Properties.of(material);"
                ]
            ),
            _JavaFunction(
                _FabricType.Material(),
                "private static",
                "getMaterial",
                {
                    "material": _CelibType.CelibMaterial() 
                },
                [
                    "var builder = new Material.Builder(MaterialColor.NONE);",
                    "if (material.isLiquidMatter()) {",
                    "    builder.liquid();",
                    "}",
                    "if (!material.isSolidMatter()) {",
                    "    builder.nonSolid();",
                    "}",
                    "return builder.build();"
                ]
            )
        ])
    else:
        builder.set_functions([
            _JavaFunction(
                type_properties,
                "private static",
                "getProperties",
                {
                    "properties": _CelibType.CelibBlockProperties() 
                },
                [
                    "return Properties.of();"
                ]
            )
        ])
    