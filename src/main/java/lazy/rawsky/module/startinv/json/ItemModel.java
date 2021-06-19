package lazy.rawsky.module.startinv.json;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import lazy.rawsky.utils.NbtUtils;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

public class ItemModel {

    public final String regName;
    public final int count;
    public final CompoundNBT data;

    public static final ItemModel EMPTY = new ItemModel("minecraft:air", 0, new CompoundNBT());

    public ItemModel(String regName, int count, CompoundNBT data) {
        this.regName = regName;
        this.count = count;
        this.data = data;
    }

    public static ItemModel read(JsonObject object) {
        try {
            String item = JSONUtils.getAsString(object, "regName");
            CompoundNBT data = new CompoundNBT();

            if (JSONUtils.isValidNode(object, "name")) {
                CompoundNBT display = new CompoundNBT();
                display.putString("Name", ITextComponent.Serializer.toJson(new StringTextComponent(JSONUtils.getAsString(object, "name"))));
                data.put("display", display);
            }

            if (JSONUtils.isValidNode(object, "data")) {
                JsonObject dataObj = JSONUtils.getAsJsonObject(object, "data");
                data = NbtUtils.fromJson(dataObj);
            }

            if (JSONUtils.isValidNode(object, "enchantments")) {
                JsonArray enchantmentsObj = JSONUtils.getAsJsonArray(object, "enchantments");

                ListNBT nbt = new ListNBT();
                for (JsonElement jsonElement : enchantmentsObj) {
                    EnchantmentModel enchantmentModel = EnchantmentModel.read(jsonElement.getAsJsonObject());
                    CompoundNBT enchantment = new CompoundNBT();
                    enchantment.putInt("lvl", enchantmentModel.level);
                    enchantment.putString("id", enchantmentModel.regName);
                    nbt.add(enchantment);
                }

                data.put("Enchantments", nbt);
            }

            if (item.equals("minecraft:air")) {
                return EMPTY;
            }

            int count = JSONUtils.isValidNode(object, "count") ? JSONUtils.getAsInt(object, "count") : 1;
            return new ItemModel(item, count, data);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        }
        return EMPTY;
    }
}