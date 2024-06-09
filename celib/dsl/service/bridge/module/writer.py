from celib.dsl.service.gradle.build.generator import generate_build_file as _generate_build_file
from celib.dsl.type.gradle import BuildGradleModLoaderSettings as _BuildGradleModLoaderSettings
from celib.dsl.model.path import Path as _Path

def write_bridge_module(settings:_BuildGradleModLoaderSettings):
    # Create directories
    (project_dir, source_dir) = _create_directories(settings)
    _write_build_gradle(project_dir, settings)
    _write_code(source_dir, settings)
    
    
    
def _create_directories(settings:_BuildGradleModLoaderSettings):
    # Create project folder
    project_dir = _Path.of("celib-bridge")\
        .child(settings.mod_loader)\
        .child(settings.minecraft_version)
    project_dir.mkdir()
    
    # Create source directory
    source_dir = project_dir.child("src/main/java")
    source_dir.mkdir()
    
    return project_dir, source_dir

def _write_build_gradle(project_dir:_Path, settings:_BuildGradleModLoaderSettings):
    build_file_content = _generate_build_file(settings)
    build_file = project_dir.child("build.gradle")
    try:
        with open(build_file, "w") as file:
            file.write(build_file_content)
    except BaseException as e:
        raise BaseException(f"Failed to write '{build_file}' content", e)
    

def _write_code(source_dir:_Path, settings:_BuildGradleModLoaderSettings):
    minecraft_package = "/".join([f"v{number}" for number in settings.minecraft_version])
    root_package = source_dir\
        .child("lv/cebbys/mcmods/celib/bridge")\
        .child(settings.mod_loader)\
        .child(minecraft_package)
    root_package.mkdir()
    
    block_package = root_package.child("component/block")
    block_package.mkdir()
    
    code =\
f"""package lv.cebbys.mcmods.celib.bridge.fabric.{minecraft_package.replace("/", ".")}.component.block;

import lv.cebbys.mcmods.celib.api.component.block.CelibBlock;
import lv.cebbys.mcmods.celib.api.component.block.properties.CelibBlockProperties;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Material;

public abstract class AbstractCelibBlock extends Block {{
    public AbstractCelibBlock(CelibBlock block) {{
        super(getProperties(block.getProperties()));
    }}

    private static Properties getProperties(CelibBlockProperties properties) {{
        return Properties.of(Material.STONE);
    }}
}}
"""
    
    block_class = block_package.child("AbstractCelibBlock.java")
    with open(block_class, "w") as file:
        file.write(code)