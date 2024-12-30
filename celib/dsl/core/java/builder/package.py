from celib.dsl.core.java.type import JavaPackage as _JavaPackage

class JavaPackageBuilder:
    @staticmethod
    def __of(parent:'JavaPackageBuilder', name:str):
        out = JavaPackageBuilder()
        out.__parent = parent
        out.__name = name
        return out


    def __init__(self) -> None:
        self.__parent:JavaPackageBuilder
        self.__name:str
        
    
    def __getattribute__(self, name: str) -> 'JavaPackageBuilder':
        try:
            return super().__getattribute__(name)
        except AttributeError as e:
            if name.startswith(f"_{type(self).__name__}"):
                raise e
            return JavaPackageBuilder.__of(self, name)
            
            
    def __get_package_parts(self) -> list[str]:
        parts:list[str] = []
        current:None|JavaPackageBuilder = self
        while current != None and not current.__is_root():
            parts.insert(0, current.__name)
            if current.__has_parent():
                current = current.__parent
            else:
                current = None
        return parts
    
    
    def __call__(self) -> _JavaPackage:
        try:
            parts = self.__get_package_parts()
            if len(parts) == 0:
                raise BaseException(f"No package parts found")
            out:None|_JavaPackage = None
            for part in parts:
                if out == None:
                    out = _JavaPackage(part)
                else:
                    out = _JavaPackage(part, out)
            if out == None:
                raise BaseException(f"Package was null after part processing {parts}")
            return out
        except BaseException as e:
            raise BaseException(f"Failed to create package - {e}", e)
        
    
    def __is_root(self) -> bool:
        return not self.__has_name() and not self.__has_parent()
    
    
    def __has_name(self) -> bool:
        try:
            return self.__name != None # type: ignore
        except:
            return False
    
    
    def __has_parent(self) -> bool:
        try:
            return self.__parent != None # type: ignore
        except:
            return False


Builder = JavaPackageBuilder()
