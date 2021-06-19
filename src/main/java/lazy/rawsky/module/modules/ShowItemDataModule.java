package lazy.rawsky.module.modules;

import lazy.rawsky.RawSky;
import lazy.rawsky.Ref;
import lazy.rawsky.module.IModule;
import net.minecraft.util.ResourceLocation;

public class ShowItemDataModule implements IModule {

    @Override
    public void onInit() {
        RawSky.LOGGER.info("Init Show Item Data...");
    }

    @Override
    public ResourceLocation getResId() {
        return new ResourceLocation(Ref.MOD_ID, Ref.ModuleIds.SHOW_DATA);
    }
}
