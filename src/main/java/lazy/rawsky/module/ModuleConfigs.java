package lazy.rawsky.module;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import lazy.rawsky.RawSky;
import lazy.rawsky.Ref;
import lazy.rawsky.utils.FileUtils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class ModuleConfigs {

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    private static Map<String, Boolean> fallback = createConfigMap();
    private static Map<String, Boolean> configMap;

    public static void init(){
        FileUtils.checkOrCreatePath(Ref.RAW_SKY_PATH);
        read();
    }

    public static boolean isEnabled(String id){
        return configMap.get(id);
    }

    public static void reload(){
        RawSky.LOGGER.info("Reloading module configs.");
        read();
    }

    private static Map<String, Boolean> createConfigMap(){
        Map<String, Boolean> configMap = new HashMap<>();
        ModuleSystem.MODULES.forEach(module -> configMap.put(module.getResId().getPath(), true));
        return configMap;
    }

    private static void read(){
        File configFile = FileUtils.createOrGetFile(Ref.RAW_SKY_PATH + "/config.json");
        if(FileUtils.isEmpty(configFile)) FileUtils.gsonWrite(GSON, configFile, fallback);
        configMap = FileUtils.gsonRead(GSON, new TypeToken<Map<String, Boolean>>(){}.getType(), configFile, createConfigMap());
        if(!isValid(configMap)) {
            RawSky.LOGGER.warn("Something is wrong on the module config file.");
            configMap = fallback;
        }
    }

    private static boolean isValid(Map<String, Boolean> configMap){
        return configMap.keySet().containsAll(fallback.keySet());
    }
}
