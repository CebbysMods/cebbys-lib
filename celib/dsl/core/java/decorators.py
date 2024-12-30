from celib.dsl.core.java.builder.package import JavaPackageBuilder as _JavaPackageBuilder
from celib.dsl.core.java.utility import TypeJava as _TypeJava
from celib.dsl.core.java.type import JavaPackage as _JavaPackage
from typing import TypeVar as _TypeVar
from typing import Type as _Type
from typing import Any as _Any

_T = _TypeVar("_T")
_JavaPackageUnion = _JavaPackage|_JavaPackageBuilder

def __build(cls:_TypeJava[_Any]):
    out = f"package {cls.__java_package__};\n\n"
    if cls.__java_extends__ != None:
        extend = cls.__java_extends__
        out += f"import {extend.__java_package__}.{extend.__name__};\n\n"
    else:
        extend = None
    
    out += f"public class {cls.__name__} "
    if extend != None:
        out += f"extends {extend.__name__} "
    
    out += "{\n\n}"
    print(out)


def is_java_class(cls:type) -> bool:
    try:
        return cls.__java_type__ == True # type: ignore
    except:
        return False


def __get_class_extend(cls:type) -> None|_TypeJava[_Any]:
    classes = [e for e in cls.__bases__]
    out:list[type] = []
    for extend in classes:
        if is_java_class(extend):
            out.append(extend)
        else:
            raise BaseException(f"Type '{extend}' is not Java type")
    count = len(out)
    if count > 1:
        raise BaseException(f"Type '{cls}' has multiple extends candidates '{out}'")
    elif count == 1:
        return out[0]
    return None

    
def dummy_java_class(package:_JavaPackageUnion):
    def decorator(type:_T) -> _T:
        cls:_TypeJava[_T] = type # type: ignore
        cls.__java_type__ = True
        
        if isinstance(package, _JavaPackageBuilder):
            cls.__java_package__ = package()
        else:
            cls.__java_package__ = package
        
        return type
    return decorator


def java_class(package:_JavaPackageUnion):
    dummy = dummy_java_class(package)
    def decorator(type:_T) -> _T:
        cls:_TypeJava[_T] = type # type: ignore
        cls = dummy(cls)
        extend = __get_class_extend(cls)
        cls.__java_extends__ = extend
        __build(cls)
        return type
    return decorator