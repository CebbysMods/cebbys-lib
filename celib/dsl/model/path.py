from typing import Any as _Any
from os import PathLike as _PathLike
from os import makedirs as _mkdir

class Path(_PathLike[str]):
    @staticmethod
    def of(path:_Any):
        parts = str(path).replace("\\", "/").split("/")
        out:None|Path = None
        while len(parts) > 0:
            part = parts.pop(0)
            out = Path(parent=out, path=part)
        if out == None:
            raise BaseException(f"Failed to parse path '{path}'")
        return out
    
    def __init__(self, *, parent:'None|Path' = None, path:None|_Any = None) -> None:
        self.__parent = parent
        if path == None:
            self.__path = None
        else:
            self.__path = str(path)
    
    def child(self, child_name:_Any) -> 'Path':
        parts = str(child_name).replace("\\", "/").split("/")
        out:Path = self
        while len(parts) > 0:
            part = parts.pop(0)
            out = Path(parent=out, path=part)
        return out
    
    def parent(self) -> 'None|Path':
        return self.__parent
    
    def __str__(self) -> str:
        out:list[str] = []
        if self.__parent != None and self != self.__parent:
            out.append(str(self.__parent))
        if self.__path != None:
            out.append(self.__path)
        return "/".join(out)
    
    def __repr__(self) -> str:
        return self.__str__()
    
    def __fspath__(self) -> str:
        return self.__str__()
    
    def mkdir(self, exist_ok:bool=True):
        path = self.__fspath__()
        try:
            _mkdir(path, exist_ok=exist_ok)
        except BaseException as e:
            raise BaseException(f"Failed to create directory '{path}'", e)
        
        