from celib.dsl.core.service.gradle.settings.reader import read_bridge_modules
from celib.dsl.core.service.bridge.module.writer import write_bridge_module
from celib.dsl.core.service.resource.reader import read_parsed_versioning_resource


if __name__ == "__main__":
    # Delete existing bridge module folders
    # Read bridge modules from settings.gradle
    # For each module get dependency versions
    print(f"[celib-dsl] > Loading used modules from 'settings.gradle'")
    modules = read_bridge_modules()
    print(f"[celib-dsl] > Loading module configurations from 'resources/versioning.json'")
    settings = read_parsed_versioning_resource(modules)
    for setting in settings:
        print(f"[celib-dsl] > Generating bridge module for '{setting.mod_loader}:{setting.minecraft_version}'")
        write_bridge_module(setting)