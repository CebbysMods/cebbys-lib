from celib.dsl.gen.code.abstract.block import generate_abstract_block as _generate_abstract_block
from celib.dsl.gen.code.registry.block import generate_block_registry as _generate_block_registry
from celib.dsl.core.type.gradle import BuildGradleModLoaderSettings as _BuildGradleModLoaderSettings
from celib.dsl.core.model.java import JavaClass as _JavaClass

def generate_code(settings:_BuildGradleModLoaderSettings) -> list[_JavaClass]:
    return [
        _generate_abstract_block(settings),
        _generate_block_registry(settings)
    ]