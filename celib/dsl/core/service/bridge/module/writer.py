from celib.dsl.core.service.gradle.build.generator import generate_build_file as _generate_build_file
from celib.dsl.core.type.gradle import BuildGradleModLoaderSettings as _BuildGradleModLoaderSettings
from celib.dsl.core.model.path import Path as _Path
from celib.dsl.gen import generate_code as _generate_code

def write_bridge_module(settings:_BuildGradleModLoaderSettings):
    # Create directories
    print(f"[celib-dsl]     > Creating project directories")
    (project_dir, source_dir) = _create_directories(settings)
    print(f"[celib-dsl]     > Writing project 'build.gradle' file")
    _write_build_gradle(project_dir, settings)
    print(f"[celib-dsl]     > Writing source code")
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
    classes = _generate_code(settings)
    for c in classes:
        print(f"[celib-dsl]         > Writing source code for '{c.get_package()}.{c.get_name()}'")
        package_dir = source_dir.child(c.get_package().replace(".", "/"))
        package_dir.mkdir()
        
        class_file = package_dir.child(f"{c.get_name()}.java")
        with open(class_file, "w") as file:
            file.write(c.compile())