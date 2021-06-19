package lazy.rawsky.module.startinv.events;

import lazy.rawsky.Ref;
import lazy.rawsky.module.ModuleSystem;
import lazy.rawsky.module.startinv.StartInvModule;
import lazy.rawsky.module.startinv.json.InventoryModel;
import lazy.rawsky.module.startinv.utils.InvUtils;
import lazy.rawsky.utils.PlayerUtils;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ModuleEvents {

    @SubscribeEvent
    public static void onJoin(EntityJoinWorldEvent event){
        if(event.getWorld().isClientSide) return;
        if(event.getEntity() instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) event.getEntity();
            if(PlayerUtils.checkAndSetFirstWorldJoin(player)){
                InventoryModel model = ModuleSystem.getModule(StartInvModule.class, Ref.ModuleIds.START_INV).load();
                InvUtils.insertIntoPlayerInv(model, player.inventory);
            }
        }
    }
}
