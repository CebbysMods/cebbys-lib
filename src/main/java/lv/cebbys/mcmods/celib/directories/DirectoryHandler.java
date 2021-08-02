package lv.cebbys.mcmods.celib.directories;

import java.io.File;
import java.nio.file.Path;

public class DirectoryHandler {

    public static boolean initDirectory( Path directory ) {
        return directory.toFile().mkdirs();
    }

    public static Path appendToPath( Path path, String appendable ) {
        return ( new File( path + "\\" + appendable ) ).toPath();
    }

}
