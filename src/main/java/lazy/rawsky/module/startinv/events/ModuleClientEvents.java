package lazy.rawsky.module.startinv.events;

import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ModuleClientEvents {

    @SubscribeEvent
    public static void onTooltip(ItemTooltipEvent event) {
        if (event.getItemStack().hasTag()) {
            for (String key : event.getItemStack().getTag().getAllKeys()) {
                event.getToolTip().add(new StringTextComponent("key: " + key + " = " + event.getItemStack().getTag().get(key)));
            }
        }
    }
}
