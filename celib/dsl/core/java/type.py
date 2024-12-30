
class JavaPackage:
    def __init__(self, name:str, parent:'None|JavaPackage' = None) -> None:
        if parent != None:
            self.__parent = parent
        self.__name = name
        
    def has_parent(self) -> bool:
        try:
            return self.__parent != None # type: ignore
        except:
            return False
    
    def __str__(self) -> str:
        if self.has_parent():
            return f"{self.__parent}.{self.__name}"
        else:
            return self.__name
        
    def __repr__(self) -> str:
        return f"{type(self).__name__}[{self.__str__()}]"