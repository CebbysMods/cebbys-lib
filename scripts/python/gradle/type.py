from typing import Any as _Any

class GradleBuildBlock:
    def __init__(self) -> None:
        self.identifier:str
        self.rows:list[str]
        
    def __str__(self) -> str:
        out = f"{self.identifier} " + "{\n"
        if self.rows != None: # type: ignore
            for row in self.rows:
                out += f"\t{row}\n"
        out += "}\n\n"        
        return out  


class GradleBuildPluginsBlock(GradleBuildBlock):
    def __init__(self) -> None:
        super().__init__()
        self.identifier = "plugins"
        self.rows = []
    
    def with_plugin(self, plugin:str, version:None|str = None):
        row = f"id \"{plugin}\""
        if version != None:
            row += f" version \"{version}\""
        self.rows.append(row)
        return self


class GradleBuildBaseBlock(GradleBuildBlock):
    def __init__(self) -> None:
        super().__init__()
        self.identifier = "base"
        self.rows = []
        
    def with_string_property(self, property:str, value:_Any):
        return self.with_property(property, f"\"{value}\"")
        
    def with_property(self, property:str, value:_Any):
        self.rows.append(f"{property} = {value}")
        return self
        
        
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