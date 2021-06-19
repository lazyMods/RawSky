package lazy.rawsky.utils;

import lazy.rawsky.RawSky;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemUtils {

    public static ItemStack get(String resName, int count) {
        if (resName.equals("minecraft:air")) return ItemStack.EMPTY;
        Item fromRes = ForgeRegistries.ITEMS.getValue(new ResourceLocation(resName));
        if (fromRes == Items.AIR) RawSky.LOGGER.error("The given resource name is not valid. Given: " + resName);
        return new ItemStack(fromRes, count);
    }

    public static ItemStack get(String resName) {
        return get(resName, 1);
    }

    public static ItemStack getWithTag(String resName, int count, CompoundNBT data) {
        ItemStack itemStack = get(resName, count);
        itemStack.setTag(data);
        return itemStack;
    }
}
