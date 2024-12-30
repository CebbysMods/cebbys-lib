from celib.dsl.core.type.enum import ModLoader as _ModLoader
from celib.dsl.core.type import MinecraftVersion as _MinecraftVersion


def read_bridge_modules() -> list[tuple[_ModLoader, _MinecraftVersion]]:
    with open("settings.gradle") as file:
        text = file.read()
    
    wrapper = "/** <-- Bridges --> */"
    start = text.find(wrapper)    
    end = text.rfind(wrapper)
    if start == -1:
        raise BaseException(f"Bad 'settings.gradle' formatting - Missing bridge header comment '{wrapper}'")
    if end == -1:
        raise BaseException(f"Bad 'settings.gradle' formatting - Missing bridge footer comment '{wrapper}'")
    
    try:
        bridges = text[start + len(wrapper):end].split("\n")
        bridges = [bridge for bridge in bridges if len(bridge) > 0 and not bridge.isspace()]
        bridges = [bridge.replace("include(\":celib-bridge:", "") for bridge in bridges]
        bridges = [bridge.replace("\")", "") for bridge in bridges]
        bridges = [bridge.split(":") for bridge in bridges]
        bridges = [
            (
                _ModLoader.from_string(bridge[0]),
                _MinecraftVersion.from_string(bridge[1])
            )
            for bridge in bridges if len(bridge) == 2
        ]
    except BaseException as e:
        raise BaseException(f"Failed to parse '{wrapper}' content - {e}", e)
    return bridges