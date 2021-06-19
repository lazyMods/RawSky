package lazy.rawsky;

import net.minecraftforge.fml.loading.FMLPaths;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Ref {

    public static final String MOD_ID = "rawsky";
    public static final Path RAW_SKY_PATH = Paths.get(FMLPaths.GAMEDIR.get().toString() + "/rawsky/");

    public static class ModuleIds {

        public static final String START_INV = "start_inventory";
    }
}
