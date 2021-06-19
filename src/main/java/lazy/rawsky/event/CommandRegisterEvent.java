package lazy.rawsky.event;

import lazy.rawsky.command.RawSkyCommand;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class CommandRegisterEvent {

    @SubscribeEvent
    public static void onCommandRegister(RegisterCommandsEvent event){
        RawSkyCommand.register(event.getDispatcher());
    }
}
