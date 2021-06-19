package lazy.rawsky.event;

import lazy.rawsky.Ref;
import lazy.rawsky.module.ModuleConfigs;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class OnTooltipEvent {

    @SubscribeEvent
    public static void onTooltip(ItemTooltipEvent event){
        if(ModuleConfigs.isEnabled(Ref.ModuleIds.SHOW_DATA)){
            if (event.getItemStack().hasTag()) {
                for (String key : event.getItemStack().getTag().getAllKeys()) {
                    event.getToolTip().add(new StringTextComponent("key: " + key + " = " + event.getItemStack().getTag().get(key)));
                }
            }
        }
    }
}
