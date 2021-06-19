package lazy.rawsky.module.modules.startinv;

import com.google.gson.JsonObject;
import lazy.rawsky.RawSky;
import lazy.rawsky.Ref;
import lazy.rawsky.module.IModule;
import lazy.rawsky.module.modules.startinv.json.InventoryModel;
import lazy.rawsky.utils.FileUtils;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class StartInvModule implements IModule {

    private File configFile;

    @Override
    public void onInit() {
        RawSky.LOGGER.info("Loading start inventory module.");

        FileUtils.checkOrCreatePath(Ref.RAW_SKY_PATH);
        this.configFile = FileUtils.createOrGetFile(Ref.RAW_SKY_PATH + "/" + getResId().getPath() + ".json");
    }

    @Override
    public ResourceLocation getResId() {
        return new ResourceLocation(Ref.MOD_ID, Ref.ModuleIds.START_INV);
    }

    public InventoryModel load() {
        if (!FileUtils.isEmpty(this.configFile)) {
            try {
                JsonObject object = JSONUtils.parse(new FileReader(this.configFile));
                return InventoryModel.read(object);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        return InventoryModel.empty();
    }
}
