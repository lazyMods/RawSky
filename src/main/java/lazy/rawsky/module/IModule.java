package lazy.rawsky.module;

import net.minecraft.util.ResourceLocation;

public interface IModule {

    void onInit();

    ResourceLocation getResId();
}
