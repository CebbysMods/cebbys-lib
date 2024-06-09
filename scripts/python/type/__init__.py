
class MinecraftVersion:
    def __init__(self, major:int, minor:int, patch:int) -> None:
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