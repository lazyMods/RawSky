package lazy.rawsky.module;

import com.google.common.collect.Lists;
import lazy.rawsky.Ref;
import net.minecraft.util.ResourceLocation;

import java.util.List;

public class ModuleSystem {

    public static final List<IModule> MODULES = Lists.newArrayList();

    public static void init() {
        ModuleConfigs.init();
        MODULES.forEach(IModule::onInit);
    }

    public static <T> T getModule(Class<T> toCast, String id) {
        IModule module = MODULES.stream()
                .filter(iModule -> iModule.getResId().equals(new ResourceLocation(Ref.MOD_ID, id)))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("module with id " + id + " doesnt exist."));
        return toCast.cast(module);
    }
}
