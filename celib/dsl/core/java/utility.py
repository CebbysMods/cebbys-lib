from celib.dsl.core.java.type import JavaPackage as _JavaPackage
from typing import TypeVar as _TypeVar
from typing import Type as _Type
from typing import Any as _Any

_T = _TypeVar("_T")

class TypeJava(_Type[_T]):
    __java_type__:bool
    __java_package__:_JavaPackage
    __java_extends__:'None|TypeJava[_Any]'
    