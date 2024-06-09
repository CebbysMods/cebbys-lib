from celib.dsl.service.gradle.settings.reader import read_bridge_modules
from celib.dsl.service.bridge.module.writer import write_bridge_module
from celib.dsl.service.resource.reader import read_parsed_versioning_resource

if __name__ == "__main__":
    # Delete existing bridge module folders
    # Read bridge modules from settings.gradle
    # For each module get dependency versions
    modules = read_bridge_modules()
    settings = read_parsed_versioning_resource(modules)
    for setting in settings:
        write_bridge_module(setting)
