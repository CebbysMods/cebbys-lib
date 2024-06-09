from typing import Iterable, Iterator

class MinecraftVersion(Iterable[int]):
    @staticmethod
    def from_string(version:str) -> 'MinecraftVersion':
        try:
            parts = version.split(".")
            for part in parts:
                if not part.isnumeric():
                    raise BaseException(f"Version part '{part}' is not a number")
                if part.startswith("+") or part.startswith("-"):
                    raise BaseException(f"Version part '{part}' cannot be signed")
            parts = [int(part) for part in parts]
            if len(parts) == 2:
                return MinecraftVersion(parts[0], parts[1])
            else:
                return MinecraftVersion(parts[0], parts[1], parts[2])
        except BaseException as e:
            raise BaseException(f"Failed to parse version string '{version}' to 'MinecraftVersion' object - {e}", e)
    
    def __init__(self, major:int, minor:int, patch:int = 0) -> None:
        self.major = major
        self.minor = minor
        self.patch = patch
        
    def __str__(self) -> str:
        version = f"{self.major}.{self.minor}"
        if self.patch != 0:
            version += "." + str(self.patch)
        return version
    
    def __repr__(self) -> str:
        return self.__str__()
    
    def __iter__(self) -> Iterator[int]:
        data:list[int] = [self.major, self.minor]
        if self.patch != 0:
            data.append(self.patch)
        return data.__iter__()