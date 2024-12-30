class JavaElement:
    # @_abstractmethod
    def compile(self) -> str:...


class JavaType(JavaElement):
    def __init__(self, package:None|str, name:str) -> None:
        super().__init__()
        self.__package = package
        self.__name = name
        
    def get_simple(self) -> str:
        return self.__name
        
    def get_name(self) -> str:
        return self.__name
    
    def get_package(self) -> None|str:
        return self.__package
        
    def is_native(self) -> bool:
        return self.__package == None 

    def is_generic(self) -> bool:
        return False
    
    def __str__(self) -> str:
        if self.__package != None:
            return f"{self.__package}.{self.__name}"
        return f"{self.__name}"
    
    def __repr__(self) -> str:
        return self.__str__()
    

class JavaTypeGeneric(JavaType):
    def __init__(self, package:None|str, name:str, generics:list[JavaType]) -> None:
        super().__init__(package, name)
        self.__generics = generics

    def get_simple(self) -> str:
        generics = ", ".join([g.get_simple() for g in self.__generics])
        return f"{super().get_simple()}<{generics}>"

    def get_generics(self) -> list[JavaType]:
        return self.__generics
    
    def is_generic(self) -> bool:
        return True


class JavaFunction(JavaElement):
    def __init__(self, return_type:JavaType, type:str, name:str, parameters:dict[str, JavaType], code:list[str]) -> None:
        super().__init__()
        self.__return = return_type
        self.__type = type
        self.__name = name
        self.__parameters = parameters
        self.__code = code
        
    def get_name(self) -> str:
        return self.__name
    
    def get_parameters(self) -> dict[str, JavaType]:
        return self.__parameters
    
    def get_return(self) -> JavaType:
        return self.__return
    
    def get_code(self) -> list[str]:
        return self.__code
    
    def get_imports(self) -> set[JavaType]:
        out:set[JavaType] = set()
        out.add(self.__return)
        for p in self.__parameters.values():
            out.add(p)
        return out
    
    def compile(self) -> str:
        params = ", ".join([f"{type.get_simple()} {name}" for (name, type) in self.__parameters.items()])
        out = f"{self.__type} {self.__return.get_simple()} {self.__name}({params}) "
        out += "{\n"
        for line in self.__code:
            out += f"\t{line}\n"
        out += "}"
        return out


class JavaConstructor(JavaElement):
    def __init__(self, type: JavaType, parameters: dict[str, JavaType], code: list[str]) -> None:
        super().__init__()
        self.__name = type.get_name()
        self.__parameters = parameters
        self.__code = code
        
    def get_name(self) -> str:
        return self.__name
    
    def get_parameters(self) -> dict[str, JavaType]:
        return self.__parameters
    
    def get_code(self) -> list[str]:
        return self.__code
        
    def compile(self) -> str:
        params = ", ".join([f"{type.get_simple()} {name}" for (name, type) in self.__parameters.items()])
        out = f"public {self.__name}({params}) "
        out += "{\n"
        for line in self.__code:
            out += f"\t{line}\n"
        out += "}"
        return out


class JavaClass(JavaElement):
    def __init__(self) -> None:
        super().__init__()
        self.__type_name:JavaType
        self.__type:str
        self.__extends:set[JavaType] = set()
        self.__imports:set[JavaType] = set()
        self.__constructors:list[JavaConstructor] = []
        self.__functions:list[JavaFunction] = []
    
    
    def get_package(self) -> str:
        return self.__type_name.get_package() # type: ignore
    
    def get_name(self) -> str:
        return self.__type_name.get_name()
    
    
    def set_type_name(self, type_name:JavaType):
        self.__type_name = type_name
    
    
    def add_import(self, type:JavaType):
        self.__imports.add(type)
    
    
    def set_type(self, type:str):
        self.__type = type
    
    
    def set_extends(self, types:list[JavaType]):
        for e in self.__extends:
            self.__imports.remove(e)
            
        self.__extends = set(types)
        
        for e in self.__extends:
            if e not in self.__imports:
                self.__imports.add(e)

    
    def set_constructors(self, constructors:list[JavaConstructor]):
        for c in self.__constructors:
            for t in c.get_parameters().values():
                self.__imports.remove(t)
                
        self.__constructors = constructors
        
        for c in self.__constructors:
            for t in c.get_parameters().values():
                self.__imports.add(t)
    
    
    def set_functions(self, functions:list[JavaFunction]):
        for c in self.__functions:
            for t in c.get_imports():
                self.__imports.remove(t)
                
        self.__functions = functions
        
        for c in self.__functions:
            for t in c.get_imports():
                self.__imports.add(t)
    
    
    def compile(self):
        out = f"package {self.__type_name.get_package()};\n\n"
        
        imports = [i for i in self.__imports if not i.is_native()]
        if len(imports) > 0:
            for i in imports:
                if not i.is_native():
                    out += f"import {i};\n"
            out += "\n"
        
        out += f"{self.__type} {self.__type_name.get_name()} "
        
        if len(self.__extends) > 0:
            extends = ", ".join([e.get_simple() for e in self.__extends])
            out += f"extends {extends} "
        
        out += "{\n\n"
            
        if len(self.__constructors) > 0:
            for f in self.__constructors:
                code = f.compile().split("\n")
                for line in code:
                    out += f"\t{line}\n"
                out += "\n"
            
        if len(self.__functions) > 0:
            for f in self.__functions:
                code = f.compile().split("\n")
                for line in code:
                    out += f"\t{line}\n"
                out += "\n"
        
        out += "}\n"
        
        return out
    