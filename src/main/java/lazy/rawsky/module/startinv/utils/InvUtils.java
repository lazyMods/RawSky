package lazy.rawsky.module.startinv.utils;

import lazy.rawsky.RawSky;
import lazy.rawsky.module.startinv.json.InventoryModel;
import lazy.rawsky.module.startinv.json.ItemModel;
import lazy.rawsky.utils.ItemUtils;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;

import java.util.Map;

public class InvUtils {

    public static ItemStack checkAndGetWithTag(ItemModel model){
        return model.data.isEmpty() ? ItemUtils.get(model.regName, model.count) : ItemUtils.getWithTag(model.regName, model.count, model.data);
    }

    public static void insertIntoPlayerInv(InventoryModel model, PlayerInventory inventory) {
        readAndInsertOffHand(model, inventory);
        readAndInsertArmor(model, inventory);
        readAndInsertMainInventory(model, inventory);
        readAndInsertHotBar(model, inventory);
    }

    public static void readAndInsertOffHand(InventoryModel inv, PlayerInventory inventory){
        inventory.offhand.set(0, checkAndGetWithTag(inv.offHandStack));
    }

    public static void readAndInsertArmor(InventoryModel inv, PlayerInventory inventory){
        for (Map.Entry<String, ItemModel> armorEntry : inv.armorStacks.entrySet()) {
            int slotID = EnumArmorType.fromString(armorEntry.getKey()).getSlotID();
            inventory.armor.set(slotID, checkAndGetWithTag(armorEntry.getValue()));
        }
    }

    public static void readAndInsertMainInventory(InventoryModel inv, PlayerInventory inventory){
        for (Map.Entry<Integer, ItemModel> invEntry : inv.inventoryStacks.entrySet()) {
            if (invEntry.getKey() > 35 || invEntry.getKey() < 9) {
                RawSky.LOGGER.error("slot can't be less than 9 and higher than 35. Slot: " + invEntry.getKey());
                return;
            }
            inventory.items.set(invEntry.getKey(), checkAndGetWithTag(invEntry.getValue()));
        }
    }

    public static void readAndInsertHotBar(InventoryModel inv, PlayerInventory inventory){
        for (Map.Entry<Integer, ItemModel> hotBarEntry : inv.hotbarStacks.entrySet()) {
            if (hotBarEntry.getKey() > 8 || hotBarEntry.getKey() < 0) {
                RawSky.LOGGER.error("hot bar slot can't be less than 0 and higher than 8. Slot: " + hotBarEntry.getKey());
                return;
            }
            inventory.items.set(hotBarEntry.getKey(), checkAndGetWithTag(hotBarEntry.getValue()));
        }
    }
}
