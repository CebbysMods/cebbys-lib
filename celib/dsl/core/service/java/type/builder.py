from celib.dsl.core.model.java import JavaTypeGeneric as _JavaTypeGeneric
from celib.dsl.core.model.java import JavaType as _JavaType
from typing import Any as _Any

class _JavaTypeBuilder:
    def __init__(self, name:None|str = None, parent:'None|_JavaTypeBuilder' = None) -> None:
        if parent != None:
            self._parent = parent
        if name != None:
            self._name = name
    
    def __getattribute__(self, name: str) -> '_JavaTypeBuilder':
        try:
            return super().__getattribute__(name)
        except AttributeError:
            try:
                return super().__getattribute__(f"_JavaTypeBuilder{name}")
            except AttributeError as e:
                if name in ["_name", "_parent", "_generics"]:
                    raise e
                return _JavaTypeBuilder(name, self)
        
    
    def __getitem__(self, keys:_Any) -> '_JavaGenericTypeBuilder':
        try:
            return super().__getitem__(keys) # type: ignore
        except:
            if not isinstance(keys, tuple):
                keys = [keys]
            generics:list[_JavaTypeBuilder] = [g for g in keys if isinstance(g, _JavaTypeBuilder)] # type: ignore
            return _JavaGenericTypeBuilder(self._name, self._parent, generics)
    
    def __call__(self) -> _JavaType:
        package:list[str] = []
        parent = self
        while hasattr(parent, "_parent"):
            parent = parent._parent
            if hasattr(parent, "_name"):
                package.insert(0, parent._name)
            else:
                break
        return _JavaType(".".join(package), self._name)


class _JavaGenericTypeBuilder(_JavaTypeBuilder):
    def __init__(self, name:None|str = None, parent:'None|_JavaTypeBuilder' = None, generics:None|list['_JavaTypeBuilder'] = None) -> None:
        super().__init__(name, parent)
        if generics != None:
            self._generics = generics
        
    def get_generics(self):
        return self._generics
    
    def __call__(self) -> _JavaTypeGeneric:
        package:list[str] = []
        parent = self
        while hasattr(parent, "_parent"):
            parent = parent._parent
            if hasattr(parent, "_name"):
                package.insert(0, parent._name)
            else:
                break
        generics = [g() for g in self._generics]
        return _JavaTypeGeneric(".".join(package), self._name, generics)
    

JavaTypeBuilder = _JavaTypeBuilder()