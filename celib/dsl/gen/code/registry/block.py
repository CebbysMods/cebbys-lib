from celib.dsl.core.service.java.type.builder import JavaTypeBuilder as _Type
import celib.dsl.gen.constant.fabric.package as _FabricPackage
import celib.dsl.gen.constant.fabric.type as _FabricType
import celib.dsl.gen.constant.celib.package as _CelibPackage
import celib.dsl.gen.constant.celib.type as _CelibType
import celib.dsl.gen.constant.java.annotation as _JavaAnnotation
from celib.dsl.core.type.gradle import BuildGradleModLoaderSettings as _BuildGradleModLoaderSettings
from celib.dsl.core.model.java import JavaFunction as _JavaFunction
from celib.dsl.core.model.java import JavaClass as _JavaClass
from celib.dsl.core.model.java import JavaType as _JavaType
from celib.dsl.core.type.enum import ModLoader as _ModLoader
from celib.dsl.core.type import MinecraftVersion as _MinecraftVersion

from celib.dsl.core.service.java.function.builder import JavaOperand as Operand
from celib.dsl.core.service.java.function.builder import JavaContext as Context
from celib.dsl.core.service.java.function.builder import JavaFunctionBuilder as Function

_1_20 = _MinecraftVersion(1, 20)
    

def generate_block_registry(settings:_BuildGradleModLoaderSettings) -> _JavaClass:
    builder:_JavaClass = _JavaClass()
    
    minecraft = ".".join([f"v{n}" for n in settings.minecraft_version])
    package = f"{settings.mod_loader}.{minecraft}"
    
    block_type = _JavaType(
        f"lv.cebbys.mcmods.celib.bridge.{package}.component.registry",
        "CelibBlockRegistry"
    )
    builder.set_type_name(block_type)
    builder.set_type("public final class")
    _set_block_extends(builder, settings)
    _set_block_functions(builder, settings)
    return builder
    

def _set_block_extends(builder:_JavaClass, settings:_BuildGradleModLoaderSettings):
    match settings.mod_loader:
        case _ModLoader.FABRIC:
            builder.set_extends([_CelibType.CelibRegistry[_CelibType.CelibBlock]()])
        case _:
            raise BaseException(f"Not implemented for '{settings.mod_loader}'")
        

def _set_block_functions(builder:_JavaClass, settings:_BuildGradleModLoaderSettings):
    match settings.mod_loader:
        case _ModLoader.FABRIC:
            _set_fabric_functions(builder, settings)
        case _:
            raise BaseException(f"Not implemented for '{settings.mod_loader}'")


def _set_fabric_functions(builder:_JavaClass, settings:_BuildGradleModLoaderSettings):
    package = f"{settings.mod_loader}.{settings.minecraft_version.to_package()}"
    AbstractBlock = _JavaType(
        f"lv.cebbys.mcmods.celib.bridge.{package}.component.block",
        "AbstractCelibBlock"
    )
    
    typevar_i = _JavaType(None, "I")
    builder.add_import(AbstractBlock)
    builder.add_import(_FabricType.ResourceLocation())
    builder.add_import(_FabricType.Registry())
    builder.add_import(_CelibType.CelibBlock())
    
    register = Function.create('register')
    # register.set_annotations([_JavaAnnotation.Override()])
    register.set_modifiers(["protected"])
    register.set_type_vars([("I", _CelibType.CelibBlock())])
    register.set_return_type("I")
    
    if settings.minecraft_version < _1_20:
        register.set_parameters({
            "id": _CelibType.CelibResourceLocation(),
            "instance": "I"
        })
        def set_register_code(ctx:Context, id:Operand, instance:Operand):
            Registry = _FabricType.Registry()
            ResourceLocation = _FabricType.ResourceLocation()
            
            return [
                ctx.call(Registry, 'register',
                    ctx.get(Registry, 'BLOCK'),
                    ctx.cast(ResourceLocation, ctx.call(id, 'as', ctx.ref(ResourceLocation, 'new'))),
                    ctx.new(AbstractBlock, instance, override=[
                        
                    ])         
                ),
                ctx.returns(instance)
            ]
        register.set_code(set_register_code)
        
        builder.set_functions([
            _JavaFunction(
                typevar_i,
                "protected <I extends CelibBlock>",
                "register",
                {
                    "id": _CelibType.CelibResourceLocation(),
                    "instance": typevar_i
                },
                [
                    "Registry.register(",
                    "        Registry.BLOCK,",
                    "        (ResourceLocation) id.as(ResourceLocation::new),",
                    "        new AbstractCelibBlock(instance) {",
                    "        }",
                    ");",
                    "return instance;"
                ]
            )
        ])
    else:
        builder.add_import(_FabricType.BuiltInRegistries())
        builder.set_functions([
            _JavaFunction(
                typevar_i,
                "protected <I extends CelibBlock>",
                "register",
                {
                    "id": _CelibType.CelibResourceLocation(),
                    "instance": typevar_i
                },
                [
                    "Registry.register(",
                    "        BuiltInRegistries.BLOCK,",
                    "        (ResourceLocation) id.as(ResourceLocation::new),",
                    "        new AbstractCelibBlock(instance) {",
                    "        }",
                    ");",
                    "return instance;"
                ]
            )
        ])
    