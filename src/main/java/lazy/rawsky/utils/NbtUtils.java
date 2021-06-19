package lazy.rawsky.utils;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import lazy.rawsky.RawSky;
import lazy.rawsky.utils.json.NBTEntry;
import net.minecraft.nbt.CompoundNBT;

import java.util.Map;

public class NbtUtils {

    public static CompoundNBT fromJson(JsonObject object){
        CompoundNBT data = new CompoundNBT();
        try {
            for (Map.Entry<String, JsonElement> entry : object.entrySet()) {
                NBTEntry nbtEntry = NBTEntry.read(entry.getValue().getAsJsonObject());
                if(nbtEntry != null){
                    switch (nbtEntry.type){
                        case "bool":
                            data.putBoolean(entry.getKey(), Boolean.parseBoolean(nbtEntry.value));
                            break;
                        case "string":
                            data.putString(entry.getKey(), nbtEntry.value);
                            break;
                        case "int":
                            data.putInt(entry.getKey(), Integer.parseInt(nbtEntry.value));
                            break;
                        default:
                            RawSky.LOGGER.warn("Given type isn't valid. Type: " + nbtEntry.type);
                    }
                } else {
                    RawSky.LOGGER.warn("Something is wrong with nbt: " + entry.getKey());
                }
            }
        } catch (JsonSyntaxException e){
            e.printStackTrace();
        }
        return data;
    }
}
