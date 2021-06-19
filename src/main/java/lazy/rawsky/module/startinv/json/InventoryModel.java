package lazy.rawsky.module.startinv.json;

import com.google.common.collect.Maps;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import lazy.rawsky.module.startinv.utils.EnumArmorType;
import lazy.rawsky.utils.DataUtils;
import net.minecraft.util.JSONUtils;

import java.util.Map;

public class InventoryModel {

    public final ItemModel offHandStack;
    public final Map<String, ItemModel> armorStacks;
    public final Map<Integer, ItemModel> inventoryStacks;
    public final Map<Integer, ItemModel> hotbarStacks;

    public InventoryModel(ItemModel offHandStack, Map<String, ItemModel> armorStacks, Map<Integer, ItemModel> inventoryStacks, Map<Integer, ItemModel> hotbarStacks) {
        this.offHandStack = offHandStack;
        this.armorStacks = armorStacks;
        this.inventoryStacks = inventoryStacks;
        this.hotbarStacks = hotbarStacks;
    }

    public static InventoryModel empty() {
        return new InventoryModel(
                ItemModel.EMPTY,
                DataUtils.mapFromArrays(DataUtils.enumStringArr(EnumArmorType.class), DataUtils.equalArray(ItemModel.class, 4, ItemModel.EMPTY)),
                DataUtils.mapFromArrays(DataUtils.numberedArray(9, 27), DataUtils.equalArray(ItemModel.class, 27, ItemModel.EMPTY)),
                DataUtils.mapFromArrays(DataUtils.numberedArray(9), DataUtils.equalArray(ItemModel.class, 9, ItemModel.EMPTY))
        );
    }

    public static InventoryModel read(JsonObject jsonObject) {
        ItemModel offHandStack = ItemModel.EMPTY;
        Map<String, ItemModel> armorStacks = Maps.newHashMap();
        Map<Integer, ItemModel> inventoryStacks = Maps.newHashMap();
        Map<Integer, ItemModel> hotbarStacks = Maps.newHashMap();

        try {
            if (JSONUtils.isValidNode(jsonObject, "offHandStack")) {
                offHandStack = ItemModel.read(JSONUtils.getAsJsonObject(jsonObject, "offHandStack"));
            }
            if (JSONUtils.isValidNode(jsonObject, "armorStacks")) {
                JsonObject armorStacksObj = JSONUtils.getAsJsonObject(jsonObject, "armorStacks");
                for (Map.Entry<String, JsonElement> entry : armorStacksObj.entrySet()) {
                    armorStacks.put(entry.getKey(), ItemModel.read(entry.getValue().getAsJsonObject()));
                }
            }
            if (JSONUtils.isValidNode(jsonObject, "inventoryStacks")) {
                JsonObject inventoryStacksObj = JSONUtils.getAsJsonObject(jsonObject, "inventoryStacks");
                for (Map.Entry<String, JsonElement> entry : inventoryStacksObj.entrySet()) {
                    inventoryStacks.put(Integer.parseInt(entry.getKey()), ItemModel.read(entry.getValue().getAsJsonObject()));
                }
            }
            if (JSONUtils.isValidNode(jsonObject, "hotbarStacks")) {
                JsonObject hotbarStacksObj = JSONUtils.getAsJsonObject(jsonObject, "hotbarStacks");
                for (Map.Entry<String, JsonElement> entry : hotbarStacksObj.entrySet()) {
                    hotbarStacks.put(Integer.parseInt(entry.getKey()), ItemModel.read(entry.getValue().getAsJsonObject()));
                }
            }
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        }

        return new InventoryModel(offHandStack, armorStacks, inventoryStacks, hotbarStacks);
    }
}
