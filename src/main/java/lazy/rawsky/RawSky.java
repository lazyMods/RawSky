package lazy.rawsky;

import lazy.rawsky.module.ModuleSystem;
import lazy.rawsky.module.modules.ShowItemDataModule;
import lazy.rawsky.module.modules.startinv.StartInvModule;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(Ref.MOD_ID)
public class RawSky {

    public static final Logger LOGGER = LogManager.getLogger();

    public RawSky() {
        ModuleSystem.MODULES.add(new StartInvModule());
        ModuleSystem.MODULES.add(new ShowItemDataModule());
        ModuleSystem.init();
    }
}
