from celib.dsl.core.model.java import JavaType
from enum import Enum as _Enum

class JavaAccess(_Enum):
    PROTECTED = "protected"
    PACKAGE = ""
    PRIVATE = "private"
    PUBLIC = "public"

class JavaFunctionModifier(_Enum):
    ABSTRACT = "abstract"
    STATIC = "static"
    FINAL = "final"
    

class JavaFunction:
    def __init__(self) -> None:
        self.access:JavaAccess = JavaAccess.PACKAGE
        self.modifiers:list[JavaFunctionModifier] = []
        self.returns:JavaType
        self.generics:dict[str, None|JavaType]
        self.name:str
        