from typing import Any as _Any

class MinecraftVersion:
    def __init__(self, major:int, minor:int, patch:int = 0) -> None:
        self.major = major
        self.minor = minor
        self.patch = patch
        
    def __str__(self) -> str:
        version = f"{self.major}.{self.minor}"
        if self.patch != 0:
            version += "." + str(self.patch)
        return version
    
    def __repr__(self) -> str:
        return self.__str__()


class GradleBuildBlock:
    def __init__(self, identifier:str) -> None:
        self.__id = identifier
        self.__rows:list[str] = []
        
    def __str__(self) -> str:
        out = f"{self.__id} " + "{\n"
        if self.__rows != None: # type: ignore
            for row in self.__rows:
                out += f"\t{row}\n"
        out += "}\n\n"        
        return out
    
    def with_row(self, row:str):
        self.__rows.append(row)
        return self


class GradleBuildPluginsBlock(GradleBuildBlock):
    def __init__(self) -> None:
        super().__init__("plugins")
    
    def with_plugin(self, plugin:str, version:None|str = None):
        row = f"id \"{plugin}\""
        if version != None:
            row += f" version \"{version}\""
        return self.with_row(row)


class GradleBuildBaseBlock(GradleBuildBlock):
    def __init__(self) -> None:
        super().__init__("base")
        
    def with_string_property(self, property:str, value:_Any):
        return self.with_property(property, f"\"{value}\"")
        
    def with_property(self, property:str, value:_Any):
        return self.with_row(f"{property} = {value}")
        
     
class GradleBuildDependencies(GradleBuildBlock):
    def __init__(self) -> None:
        super().__init__("dependencies")
    
        
class GradleBuildFile:
    header_blocks:list[GradleBuildBlock]
    properties:list[str]
    footer_blocks:list[GradleBuildBlock]
    
    def __init__(self) -> None:
        self.header_blocks = []
        self.properties = []
        self.footer_blocks = []
    
    def __str__(self) -> str:
        out = ""
        if self.header_blocks != None: # type: ignore
            for header in self.header_blocks:
                out += str(header)
        
        if self.properties != None: # type: ignore
            for row in self.properties:
                out += f"{row}\n"
            out += "\n"
        
        if self.footer_blocks != None: # type: ignore
            for footer in self.footer_blocks:
                out += str(footer)
        
        return out
    
    def with_header(self, block:GradleBuildBlock):
        self.header_blocks.append(block)
        return self
    
    def with_property(self, property:str):
        self.properties.append(property)
        return self
    
    def with_footer(self, footer:GradleBuildBlock):
        self.footer_blocks.append(footer)
        return self


class GradleBuildFabricBridgeSettings:
    def __init__(self) -> None:
        self.minecraft_version:MinecraftVersion
        self.fabric_version:str
        self.loader_version:str
        self.loom_version:str
    

def generate_fabric_bridge_build_file(settings:GradleBuildFabricBridgeSettings) -> GradleBuildFile:
    bridge_suffix = str(settings.minecraft_version).replace(".", "-")
    
    return GradleBuildFile()\
        .with_header(GradleBuildPluginsBlock()\
            .with_plugin("fabric-loom", settings.loom_version)
            .with_plugin("java")
        )\
        .with_property(f"final var minecraft_version = \"{settings.minecraft_version}\"")\
        .with_property(f"final var fabric_api_version = \"{settings.fabric_version}\"")\
        .with_property(f"final var fabric_loader_version = \"{settings.loader_version}\"")\
        .with_property("final JavaVersion java_version = JavaVersion.toVersion(project.java_version)")\
        .with_property("")\
        .with_property("version = project.mod_version")\
        .with_property("group = project.maven_group")\
        .with_footer(GradleBuildBaseBlock()\
            .with_string_property("archivesName", f"fabric-bridge-{bridge_suffix}")    
        )\
        .with_footer(GradleBuildBlock("repositories")\
            .with_row("mavenCentral()")\
            .with_row("mavenLocal()")
        )\
        .with_footer(GradleBuildDependencies()\
            .with_row("minecraft \"com.mojang:minecraft:${minecraft_version}\"")\
            .with_row("mappings loom.officialMojangMappings()")\
            .with_row("")\
            .with_row("compileOnly \"net.fabricmc:fabric-loader:${fabric_loader_version}\"")\
            .with_row("compileOnly \"net.fabricmc.fabric-api:fabric-api:${fabric_api_version}\"")\
            .with_row("")\
            .with_row("implementation project(\":celib-api\")")
            .with_row("")\
            .with_row("implementation \"jakarta.annotation:jakarta.annotation-api:${project.jakarta_version}\"")\
            .with_row("compileOnly \"org.projectlombok:lombok:${project.lombok_version}\"")\
            .with_row("annotationProcessor \"org.projectlombok:lombok:${project.lombok_version}\"")\
            .with_row("testCompileOnly \"org.projectlombok:lombok:${project.lombok_version}\"")\
            .with_row("testAnnotationProcessor \"org.projectlombok:lombok:${project.lombok_version}\"")
        )\
        .with_footer(GradleBuildBlock("tasks.withType(JavaCompile).configureEach")\
            .with_row("it.options.release = java_version.ordinal() + 1")
        )\
        .with_footer(GradleBuildBlock("java")\
            .with_row("withSourcesJar()")\
            .with_row("withJavadocJar()")\
            .with_row("sourceCompatibility = java_version")\
            .with_row("targetCompatibility = java_version")
        )

if __name__ == "__main__":
    settings = GradleBuildFabricBridgeSettings()
    settings.loom_version = "1.6-SNAPSHOT"
    settings.minecraft_version = MinecraftVersion(1, 16)
    settings.fabric_version = "0.42.0+1.16"
    settings.loader_version = "0.15.11"
    
    gradle_build = generate_fabric_bridge_build_file(settings)
    print(gradle_build)