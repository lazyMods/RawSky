package lazy.rawsky.module.startinv.json;

import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import net.minecraft.util.JSONUtils;

public class EnchantmentModel {

    public final String regName;
    public final int level;

    public static EnchantmentModel EMPTY = new EnchantmentModel("", 0);

    public EnchantmentModel(String regName, int level) {
        this.regName = regName;
        this.level = level;
    }

    public static EnchantmentModel read(JsonObject object){
        try {
            return new EnchantmentModel(JSONUtils.getAsString(object, "regName"), JSONUtils.getAsInt(object, "level"));
        }catch (JsonSyntaxException e){
            e.printStackTrace();
        }
        return EMPTY;
    }
}
