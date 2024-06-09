from enum import Enum as _Enum

class ModLoader(_Enum):
    FABRIC = "fabric"
    FORGE = "forge"
    QUILT = "quilt"
    
    @staticmethod
    def from_string(mod_loader:str) -> 'ModLoader':
        for enum in ModLoader:
            if enum.value == mod_loader:
                return enum
        raise BaseException(f"Not idedentified mod loader '{mod_loader}'")
    
    def __str__(self) -> str:
        return self.value
    
    def __repr__(self) -> str:
        return f"{type(self).__name__}[{self.name}]"
