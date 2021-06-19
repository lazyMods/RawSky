package lazy.rawsky.utils.json;

import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import net.minecraft.util.JSONUtils;

public class NBTEntry {

        public String type;
        public String value;

        public NBTEntry(String type, String value) {
            this.type = type;
            this.value = value;
        }

        public static NBTEntry read(JsonObject object){
            try {
                return new NBTEntry(JSONUtils.getAsString(object, "type"), JSONUtils.getAsString(object, "value"));
            } catch (JsonSyntaxException e){
                e.printStackTrace();
            }
            return null;
        }
    }