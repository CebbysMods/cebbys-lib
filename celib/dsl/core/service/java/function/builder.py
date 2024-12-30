from celib.dsl.core.model.java import JavaFunction as _JavaFunction
from celib.dsl.core.model.java import JavaType as _JavaType
from typing import Callable as _Callable
from typing import Literal as _Literal
from typing import Any as _Any
from typing import overload as _overload

FunctionModifiers = _Literal['public', 'protected', 'private', 'static', 'final']
Annotation = _JavaType|tuple[_JavaType, dict[str, _Any]]
TypeVariable = str|tuple[str, _JavaType]|tuple[str, list[_JavaType]]
Operators = _Literal['cast', 'new']
    

class JavaOperand:
    def __init__(self, name:str) -> None:
        self.name = name
        
    def get_name(self) -> str:
        return self.name
    
    def __str__(self) -> str:
        return self.name


class JavaContext:
    def __init__(self, code_out:list[str], requires:list[_JavaType]) -> None:
        self.code = code_out
        self.requires = requires
    
    @_overload
    def call(self, type:_JavaType, name:str, *params:_Any) -> _Any:...
    @_overload
    def call(self, type:'JavaOperand', name:str, *params:_Any) -> _Any:...
    def call(self, type:'_JavaType|JavaOperand', name:str, *params:_Any) -> _Any:
        parameters = f", ".join([str(param) for param in params])
        return f"{type.get_name()}.{name}({parameters})"
        
    
    @_overload
    def get(self, type:_JavaType, name:str) -> _Any:...
    @_overload
    def get(self, type:'JavaOperand', name:str) -> _Any:...
    def get(self, type:'_JavaType|JavaOperand', name:str) -> _Any:
        return f"{type.get_name()}.{name}"
    
    @_overload
    def cast(self, type:_JavaType, castable:_Any) -> _Any:...
    @_overload
    def cast(self, type:'JavaOperand', castable:_Any) -> _Any:...
    def cast(self, type:'_JavaType|JavaOperand', castable:_Any) -> _Any:
        return f"({type.get_name()}) {castable}"
    
    @_overload
    def new(self, type:_JavaType, *params:_Any, override:list[str] = []) -> _Any:...
    @_overload
    def new(self, type:'JavaOperand', *params:_Any, override:list[str] = []) -> _Any:...
    def new(self, type:'_JavaType|JavaOperand', *params:_Any, override:list[str] = []) -> _Any:
        parameters = ", ".join([str(param) for param in params])
        return f"new {type.get_name()}({parameters})"
    
    def ref(self, type:_JavaType, name:str) -> _Any:
        return f"{type.get_name()}::{name}"
    
    def returns(self, returns:'JavaOperand') -> _Any:
        return f"return {returns.get_name()}"

class JavaFunctionBuilder:
    @staticmethod
    def create(name:str) -> 'JavaFunctionBuilder':
        return JavaFunctionBuilder(name)
        
    def __init__(self, name:str) -> None:
        self.name = name
        self.annotations:list[Annotation] = []
        self.modifiers:list[FunctionModifiers] = ["public"]
        self.type_vars:list[TypeVariable] = []
        self.returns:None|str|_JavaType = None
        self.parameters:dict[str, str|_JavaType] = {}
        
    def set_annotations(self, annotations:list[Annotation]):
        self.annotations = annotations

    def set_modifiers(self, modifiers:list[FunctionModifiers]):
        self.modifiers = modifiers
    
    def set_type_vars(self, type_vars:list[TypeVariable]):
        self.type_vars = type_vars
    
    def set_return_type(self, return_type:None|str|_JavaType):
        self.returns = return_type

    def set_parameters(self, parameters:dict[str, str|_JavaType]):
        self.parameters = parameters

    def set_code(self, builder:_Callable[..., list[_Any]]):
        code:list[str] = []
        requires:list[_JavaType] = []
        ctx = JavaContext(code, requires)
        parameters:dict[str, _Any] = {}
        for name in self.parameters.keys():
            parameters[name] = JavaOperand(name)
        built = builder(ctx, **parameters)
        for part in built:
            print(f"{part};")
    
    def build(self) -> _JavaFunction:
        return None # type: ignore